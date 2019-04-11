package pt.ipp.isep.dei.project.controllers.importroomsensors;

import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.house.HouseService;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorMapper;
import pt.ipp.isep.dei.project.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class ImportRoomSensorsController {
    private List<Object> DTOList;
    private int numberOfNotImportedReadings;
    private HouseService houseService;

    public ImportRoomSensorsController(HouseService houseService) {
        this.houseService = houseService;
    }

    /**
     * Method that configures the log file, using a FileHandler object to send log information to the specified log file.
     * The last line is responsible for not letting the information show up in the console.
     */
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

    public boolean isValidFormat(String fileName) {
        return fileName.endsWith(".csv") || fileName.endsWith(".json") || fileName.endsWith(".xml");
    }

    public ProjectFileReader createReader(String path) {
        return Utils.createReader(path);
    }

    public List<Object> readFile(File file, String path) throws FileNotFoundException {
        ProjectFileReader fileReader = createReader(path);
        DTOList = fileReader.readFile(file);
        return DTOList;
    }

    public int getNumberOfNotImportedReadings() {
        return this.numberOfNotImportedReadings;
    }

    public boolean importSensorsToRooms() {
        configLogFile();
        boolean imported = false;
        for (Object object : this.DTOList) {
            RoomSensorDTO sensorDTO = (RoomSensorDTO) object;
            RoomId roomId = new RoomId(sensorDTO.getRoomId());
            if (!this.houseService.roomExists(roomId)) {
                numberOfNotImportedReadings++;
                String invalidInfo = "id: " + sensorDTO.getId() + ".";
                LOGGER.log(Level.WARNING, "Sensor was not imported because " + roomId.getId() + " doesn't exist: " + invalidInfo);
                continue;
            } else if (this.houseService.addSensorToRoom(RoomSensorMapper.mapToEntity(sensorDTO), roomId)) {
                imported = true;
            } else {
                LOGGER.log(Level.WARNING, "Sensor was not imported because room " + roomId.getId() + " already has a sensor with the same id: Sensor id" + sensorDTO.getId() + ".");
                numberOfNotImportedReadings++;
            }
        }
        return imported;
    }

    public String getInformation() {

        int numberOfSensors = this.DTOList.size();
        String header = "\n The chosen file contains: \n";

        StringBuilder content = new StringBuilder();

        content.append(header);
        content.append("\n");
        content.append(" > ");
        content.append(numberOfSensors);
        content.append(" sensors to import to the house.");
        content.append("\n");

        return content.toString();
    }

}


