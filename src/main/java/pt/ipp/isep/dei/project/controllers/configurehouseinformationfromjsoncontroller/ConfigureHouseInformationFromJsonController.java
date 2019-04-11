package pt.ipp.isep.dei.project.controllers.configurehouseinformationfromjsoncontroller;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.utils.Utils;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class ConfigureHouseInformationFromJsonController {

    @Autowired
    private HouseService houseService;
    private House house;
    private List<Object> houseObjects;
    private int numberOfNotImportedRooms;
    private int numberOfNotImportedGrids;
    private ProjectFileReader reader;


    public ConfigureHouseInformationFromJsonController(House house, HouseService houseService) {
        this.house = house;
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

    public boolean importHouseInformation() {
        configLogFile();
        boolean imported = false;
        HouseDTO houseDTO = (HouseDTO) houseObjects.get(0);
        for (RoomDTO roomDTO : houseDTO.getRoomDTOList()) {
            RoomId roomId = new RoomId(roomDTO.getRoomId());
            double height = roomDTO.getHeight();
            double length = roomDTO.getLength();
            double width = roomDTO.getWidth();
            if (Objects.isNull(roomId.getId())) {
                numberOfNotImportedRooms++;
                LOGGER.log(Level.WARNING, "Room was not imported because it has a null id.");
                houseDTO.getRoomDTOList().remove(roomDTO);
                continue;
            }
            if (this.houseService.roomExists(roomId)) {
                numberOfNotImportedRooms++;
                String invalidInfo = "id: " + roomId + ".";
                LOGGER.log(Level.WARNING, "Room was not imported because" + roomId + " already exists: " + invalidInfo);
                houseDTO.getRoomDTOList().remove(roomDTO);
                continue;
            }
            if (Double.isNaN(length) || Utils.isFirstDoubleSmallerThanOrEqualToSecondOne(length, 0.0) ||
                    Double.isNaN(width) || Utils.isFirstDoubleSmallerThanOrEqualToSecondOne(width, 0.0) ||
                    Double.isNaN(height) || Utils.isFirstDoubleSmallerThanOrEqualToSecondOne(height, 0.0)) {
                numberOfNotImportedRooms++;
                String invalidInfo = "id: " + roomDTO.getRoomId() + ".";
                LOGGER.log(Level.WARNING, "Room was not imported because" + roomId + " does not have valid dimensions " + invalidInfo);
                houseDTO.getRoomDTOList().remove(roomDTO);
            }
        }
        for (HouseGridDTO houseGridDTO : houseDTO.getHouseGridDTOList()) {
            HouseGridId houseGridId = new HouseGridId(houseGridDTO.getName());
            if (Objects.isNull(houseGridId.getHousegridId())) {
                numberOfNotImportedGrids++;
                LOGGER.log(Level.WARNING, "House grid was not imported because it has a null id.");
                houseDTO.getHouseGridDTOList().remove(houseGridDTO);
                continue;
            }
            if (this.houseService.gridExists(houseGridId)) {
                numberOfNotImportedGrids++;
                String invalidInfo = "id: " + houseGridId + ".";
                LOGGER.log(Level.WARNING, "House grid was not imported because" + houseGridId + " already exists: " + invalidInfo);
                houseDTO.getHouseGridDTOList().remove(houseGridDTO);
                continue;

            }
        }
        if (!(numberOfNotImportedGrids == houseDTO.getHouseGridDTOList().size()
                && numberOfNotImportedRooms == houseDTO.getRoomDTOList().size())) {
            houseService.updateHouseWithRoomsAndGrids(houseDTO, house);
            imported = true;
        }
        writeAddressToFile(house.getAddress());
        return imported;
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
}


