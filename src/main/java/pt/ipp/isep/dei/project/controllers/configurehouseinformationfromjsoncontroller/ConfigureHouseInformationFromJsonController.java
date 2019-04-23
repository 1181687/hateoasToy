package pt.ipp.isep.dei.project.controllers.configurehouseinformationfromjsoncontroller;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.utils.Utils;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConfigureHouseInformationFromJsonController {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    private HouseService houseService;
    private List<Object> houseObjects;
    private int numberOfNotImportedRooms;
    private int numberOfNotImportedGrids;
    private ProjectFileReader reader;


    public ConfigureHouseInformationFromJsonController(HouseService houseService) {
        this.houseService = houseService;
    }


    private static void configLogFile() {
        FileHandler fh;
        try {
            fh = new FileHandler("log/outputErrors.log");
        } catch (IOException e) {
            fh = null;
        }
        LOGGER.addHandler(fh);
        LOGGER.setUseParentHandlers(false);
    }

    public int getNumberOfNotImportedRooms() {
        return this.numberOfNotImportedRooms;
    }

    public int getNumberOfNotImportedGrids() {
        return numberOfNotImportedGrids;
    }

    /**
     * receives the String Path (json) and creates the respective reader (json)
     * and saves it in controller private attribute reader
     *
     * @param path String path of the file to import
     */
    public void createReader(String path) {
        this.reader = Utils.createReader(path);
    }

    /**
     * receives a FileReader and reads
     *
     * @param file
     * @return
     */
    public List<Object> readFile(File file, String path) throws FileNotFoundException {
        createReader(path);
        this.houseObjects = this.reader.readFile(file);
        return houseObjects;
    }

    public boolean importHouseInformation() {
        configLogFile();
        boolean imported = false;
        HouseDTO houseDTO = (HouseDTO) houseObjects.get(0);
        checkForInvalidRoomInfo(houseDTO.getRoomDTOList());
        checkForInvalidGridInfo(houseDTO);
        if (!(numberOfNotImportedGrids == houseDTO.getHouseGridDTOList().size()
                && numberOfNotImportedRooms == houseDTO.getRoomDTOList().size())) {
            updateHouseWithRoomsAndGrids(houseDTO);
            imported = true;
        }
        Address address = AddressMapper.mapToEntity(houseDTO.getAddressDTO());
        writeAddressToFile(address);
        return imported;
    }

    private void checkForInvalidRoomInfo(List<RoomDTO> dtos) {
        for (Iterator<RoomDTO> roomDTOIterator = dtos.iterator(); roomDTOIterator.hasNext(); ) {
            RoomDTO roomDTO = roomDTOIterator.next();
            String roomId = roomDTO.getId();
            double height = roomDTO.getHeight();
            double length = roomDTO.getLength();
            double width = roomDTO.getWidth();
            if (Objects.isNull(roomId)) {
                numberOfNotImportedRooms++;
                LOGGER.log(Level.WARNING, "Room was not imported because it has a null id.");
                roomDTOIterator.remove();
                continue;
            }
            if (this.houseService.roomExists(roomId)) {
                numberOfNotImportedRooms++;
                String invalidInfo = "id: " + roomId + ".";
                LOGGER.log(Level.WARNING, "Room was not imported because " + roomId + " already exists: " + invalidInfo);
                roomDTOIterator.remove();
                continue;
            }
            if (Double.isNaN(length) || Utils.isFirstDoubleSmallerThanOrEqualToSecondOne(length, 0.0) ||
                    Double.isNaN(width) || Utils.isFirstDoubleSmallerThanOrEqualToSecondOne(width, 0.0) ||
                    Double.isNaN(height) || Utils.isFirstDoubleSmallerThanOrEqualToSecondOne(height, 0.0)) {
                numberOfNotImportedRooms++;
                String invalidInfo = "id: " + roomDTO.getId() + ".";
                LOGGER.log(Level.WARNING, "Room was not imported because " + roomId + " does not have valid dimensions " + invalidInfo);
                roomDTOIterator.remove();
            }
        }
    }

    private void checkForInvalidGridInfo(HouseDTO houseDTO) {
        List<HouseGridDTO> houseGridDTOS = houseDTO.getHouseGridDTOList();
        //List<RoomDTO> roomDTOS = houseDTO.getRoomDTOList();
        for (Iterator<HouseGridDTO> houseGridDTOIterator = houseGridDTOS.iterator(); houseGridDTOIterator.hasNext(); ) {
            HouseGridDTO houseGridDTO = houseGridDTOIterator.next();
            String houseGridId = houseGridDTO.getId();
            if (Objects.isNull(houseGridId)) {
                numberOfNotImportedGrids++;
                LOGGER.log(Level.WARNING, "House grid was not imported because it has a null id.");
                houseGridDTOIterator.remove();
                continue;
            }
            if (this.houseService.gridExists(houseGridId)) {
                numberOfNotImportedGrids++;
                String invalidInfo = "id: " + houseGridId + ".";
                LOGGER.log(Level.WARNING, "House grid was not imported because " + houseGridId + " already exists: " + invalidInfo);
                houseGridDTOIterator.remove();
            }
            //houseGridDTO.getRoomDTOS().removeIf(roomDTO -> !roomDTOS.contains(roomDTO));
        }
    }

    public void writeAddressToFile(Address address) {
        Properties prop = new Properties();
        FileOutputStream fileOut = null;
        FileInputStream fileIn = null;

        try {
            fileIn = new FileInputStream("Configuration.properties");
            prop.load(fileIn);

            prop.setProperty("completeAddress", address.getCompleteAddress());

            Location houseLocation = address.getLocation();
            if (Objects.nonNull(houseLocation)) {
                prop.setProperty("latitude", houseLocation.getLatitude().toString());
                prop.setProperty("longitude", houseLocation.getLongitude().toString());
                prop.setProperty("altitude", houseLocation.getElevation().toString());
            }

            GeographicalArea insertedGeoArea = address.getInsertedGeoArea();
            if (Objects.nonNull(insertedGeoArea)) {
                prop.setProperty("id", insertedGeoArea.getId().toString());
            }

            fileOut = new FileOutputStream("Configuration.properties");

            prop.store(fileOut, null);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(fileOut)) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateHouseWithRoomsAndGrids(HouseDTO houseDTO) {
        Address houseAddress = AddressMapper.mapToEntity(houseDTO.getAddressDTO());
        if (Objects.isNull(houseAddress.getLocation())&& Objects.nonNull(this.houseService.getAddress())) {
            houseAddress.setLocation(this.houseService.getLocation());
        }
        if (Objects.isNull(houseAddress.getInsertedGeoArea())&& Objects.nonNull(this.houseService.getAddress())) {
            houseAddress.setInsertedGeoArea(this.houseService.getInsertedGeoArea());
        }
        this.houseService.setAddress(houseAddress);

        for (RoomDTO roomDTO : houseDTO.getRoomDTOList()) {
            //Room room = RoomMapper.mapToEntity(roomDTO);

            this.houseService.createRoom(roomDTO.getId(),roomDTO.getDescription(),roomDTO.getHouseFloor(),roomDTO.getLength(),roomDTO.getWidth(),roomDTO.getHeight());

        }
        for (HouseGridDTO houseGridDTO : houseDTO.getHouseGridDTOList()) {
            this.houseService.addGrid(houseGridDTO.getId());

        }

    }
}


