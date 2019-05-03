package pt.ipp.isep.dei.project.controllers.importroomsensors;

import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.house.RoomIdDTO;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorIdDTO;
import pt.ipp.isep.dei.project.services.RoomSensorService;
import pt.ipp.isep.dei.project.services.RoomService;
import pt.ipp.isep.dei.project.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ImportRoomSensorsController {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private RoomService roomService;
    private RoomSensorService roomSensorService;
    private ProjectFileReader reader;
    private List<Object> DTOs;
    private int numberOfNotImportedReadings;

    /**
     * Constructor.
     *
     * @param roomSensorService Service to be used.
     */
    public ImportRoomSensorsController(RoomService roomService, RoomSensorService roomSensorService) {
        this.roomService = roomService;
        this.roomSensorService = roomSensorService;
    }

    /**
     * Method that creates a reader based on the path.
     *
     * @param path Path of the file.
     */
    public void createReader(String path) {
        this.reader = Utils.createReader(path);
    }

    /**
     * Method that reads the information of the file.
     *
     * @param file File to be analyzed.
     * @param path Path of the file.
     * @return List of GeoAreaDTOs.
     */
    public List<Object> readFile(File file, String path) throws FileNotFoundException {
        createReader(path);
        DTOs = this.reader.readFile(file);
        return DTOs;
    }

    /**
     * Method that imports the sensors.
     *
     * @return True or false.
     */
    public boolean importSensors() {
        boolean imported = false;
        List<RoomSensorDTO> sensorDTOs = new ArrayList<>();
        for (Object object : this.DTOs) {
            RoomSensorDTO sensorDTO = (RoomSensorDTO) object;
            RoomIdDTO roomId = new RoomIdDTO();
            roomId.setId(sensorDTO.getId());
            SensorIdDTO sensorIdDTO = new SensorIdDTO();
            sensorIdDTO.setId(sensorDTO.getId());
            if (!this.roomService.roomExists(roomId)) {
                numberOfNotImportedReadings++;
                String invalidInfo = "id: " + sensorDTO.getId() + ".";
                LOGGER.log(Level.WARNING, "Sensor was not imported because room " + roomId.getId() + " doesn't exist: " + invalidInfo);
            } else if (!this.roomSensorService.sensorExists(sensorIdDTO)) {
                sensorDTOs.add(sensorDTO);
                imported = true;
            } else {
                LOGGER.log(Level.WARNING, "Sensor was not imported because there's already has a sensor with the same id: Sensor id" + sensorDTO.getId() + ".");
                numberOfNotImportedReadings++;
            }
        }
        roomSensorService.saveSensors(sensorDTOs);
        return imported;
    }

    /**
     * Get method.
     *
     * @return Integer.
     */
    public int getNumberOfNotImportedReadings() {
        return this.numberOfNotImportedReadings;
    }
}


