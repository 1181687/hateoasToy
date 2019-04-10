package pt.ipp.isep.dei.project.controllers.configurehouseinformationfromjsoncontroller;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.utils.Utils;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class ConfigureHouseInformationFromJsonController {

    private House house;
    private List<Object> houseObjects;
    private int numberOfNotImportedRooms;
    private ProjectFileReader reader;


    public ConfigureHouseInformationFromJsonController(House house) {
        this.house = house;
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

    public boolean addRoomsToGrid() {
        configLogFile();
        boolean imported = false;
        for (Object object : this.houseObjects) {
            RoomDTO roomDTO = (RoomDTO) object;
            String roomId = roomDTO.getRoomId();
            double height = roomDTO.getHeight();
            double length = roomDTO.getLength();
            double width = roomDTO.getWidth();

            if (this.houseService.roomExists(roomId)) {
                numberOfNotImportedRooms++;
                String invalidInfo = "id: " + roomDTO.getRoomId() + ".";
                LOGGER.log(Level.WARNING, "Room was not imported because" + roomId + " already exist: " + invalidInfo);
                continue;
            }
            if (Double.isNaN(length) || Utils.isFirstDoubleSmallerThanOrEqualToSecondOne(length, 0.0) ||
                    Double.isNaN(width) || Utils.isFirstDoubleSmallerThanOrEqualToSecondOne(width, 0.0) ||
                    Double.isNaN(height) || Utils.isFirstDoubleSmallerThanOrEqualToSecondOne(height, 0.0)) {
                numberOfNotImportedRooms++;
                String invalidInfo = "id: " + roomDTO.getRoomId() + ".";
                LOGGER.log(Level.WARNING, "Room was not imported because" + roomId + " does not have valid dimensions " + invalidInfo);
                continue;

            } else if (houseService.saveRoom(RoomMapper.mapToEntity(roomDTO))) {
                imported = true;
            }
        }
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

    /**
     * This method imports the house object to be imported
     *
     * @param
     * @return boolean
     */
    public boolean importHouseInformation() {
        boolean imported = false;
        HouseDTO houseDTO = (HouseDTO) houseObjects.get(0);
        house = HouseMapper.mapToEntity(houseDTO, house);
        writeAddressToFile(house.getAddress());
        if (Objects.nonNull(house)) {
            imported = true;
        }
        return imported;
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


