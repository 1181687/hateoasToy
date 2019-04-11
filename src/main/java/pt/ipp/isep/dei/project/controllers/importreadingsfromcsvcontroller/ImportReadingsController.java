package pt.ipp.isep.dei.project.controllers.importreadingsfromcsvcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaService;
import pt.ipp.isep.dei.project.model.house.HouseService;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorList;
import pt.ipp.isep.dei.project.model.sensor.RoomSensor;
import pt.ipp.isep.dei.project.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportReadingsController {
    private static final Logger LOGGER = Logger.getLogger(ImportReadingsController.class.getName());
    @Autowired
    private GeographicalAreaService geographicalAreaService;
    private GeoAreaSensorList allSensorInTheGeoAreas;
    private GeoAreaSensor geoAreaSensor;
    private RoomSensor roomSensor;
    private Room room;
    private List<Object> readingDTOList;
    private int numberOfNotImportedReadings;
    @Autowired
    private HouseService houseService;

    /**
     * Constructor.
     *
     * @param geographicalAreaService GeographicalAreaService to be used.
     * @param houseService
     */
    public ImportReadingsController(GeographicalAreaService geographicalAreaService, HouseService houseService) {
        this.geographicalAreaService = geographicalAreaService;
        this.allSensorInTheGeoAreas = this.geographicalAreaService.getAllSensors();
        this.houseService = houseService;
    }

    /**
     * Method that checks if a given date time is before the starting date of the sensor.
     *
     * @param localDateTime Given date time to be compared.
     * @return True or False.
     */
    public boolean isDateTimeBeforeSensorStartingDate(LocalDateTime localDateTime) {
        return localDateTime.isBefore(geoAreaSensor.getStartingDate());
    }

    public boolean isDateTimeBeforeRoomSensorStartingDate(LocalDateTime localDateTime) {
        return localDateTime.isBefore(roomSensor.getStartingDate());
    }

    /**
     * Method that adds a reading to the sensor.
     *
     * @param readingDTO ReadingDTO to be transformed into a Reading and to be used.
     */
    public void addReadingToSensor(ReadingDTO readingDTO) {
        Reading reading = ReadingMapper.mapToEntity(readingDTO);
        geoAreaSensor.addReadingsToList(reading);
    }

    public int getNumberOfNotImportedReadings() {
        return this.numberOfNotImportedReadings;
    }

    public boolean addReadingToSensorById(int option) {
        configLogFile();
        boolean imported = false;
        for (Object object : this.readingDTOList) {
            ReadingDTO reading = (ReadingDTO) object;
            if (option == 1) {
                geoAreaSensor = allSensorInTheGeoAreas.getSensorById(reading.getId());
                if (Objects.isNull(geoAreaSensor) || !geoAreaSensor.isActive()) {
                    numberOfNotImportedReadings++;
                    continue;
                }
                if (isDateTimeBeforeSensorStartingDate(reading.getDateTime())) {
                    numberOfNotImportedReadings++;
                    String invalidInfo = "id: " + reading.getId() + ", value: " + reading.getValue() + ", timestamp/date: " + reading.getDateTime() + ", unit: " + reading.getUnits() + ".";
                    LOGGER.log(Level.WARNING, "Reading not imported due to timestamp/date of reading being before starting date of sensor: " + invalidInfo);
                    continue;
                }
            }
            if (option == 2) {
                roomSensor = houseService.getSensorById(reading.getId());
                room = houseService.getRoomWithRightSensor(reading.getId());
                if (Objects.isNull(roomSensor) || !roomSensor.isActive()) {
                    numberOfNotImportedReadings++;
                    String invalidInfo = "id: " + reading.getId() + ", value: " + reading.getValue() + ", timestamp/date: " + reading.getDateTime() + ", unit: " + reading.getUnits() + ".";
                    LOGGER.log(Level.WARNING, "Reading not imported due to something: " + invalidInfo);
                    continue;
                }
                if (isDateTimeBeforeRoomSensorStartingDate(reading.getDateTime())) {
                    numberOfNotImportedReadings++;
                    String invalidInfo = "id: " + reading.getId() + ", value: " + reading.getValue() + ", timestamp/date: " + reading.getDateTime() + ", unit: " + reading.getUnits() + ".";
                    LOGGER.log(Level.WARNING, "Reading not imported due to timestamp/date of reading being before starting date of sensor: " + invalidInfo);
                    continue;
                }
            }
            if (reading.getUnits().equals("F")) {
                double celsiusValue = Utils.convertFahrenheitToCelsius(reading.getValue());
                reading.setValue(Utils.round(celsiusValue, 2));
                reading.setUnits("C");
            }
            if (option == 1 && geoAreaSensor.addReading(ReadingMapper.mapToEntity(reading))) {
                imported = true;
                this.geographicalAreaService.updateRepository();
            }
            if (option == 2 && room.getSensorById(reading.getId()).addRoomReading(new RoomReading(ReadingMapper.mapToEntity(reading).getValue(),ReadingMapper.mapToEntity(reading).getDateTime()))) {

                imported = true;
                this.houseService.updateRepository(room);
            }
        }

        return imported;
    }

    public boolean isValidFormat(String fileName) {
        return fileName.endsWith(".csv") || fileName.endsWith(".json") || fileName.endsWith(".xml");
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

    public ProjectFileReader createReader(String path) {
        return Utils.createReader(path);
    }

    public List<Object> readFile(File file, String path) throws FileNotFoundException {
        ProjectFileReader fileReader = createReader(path);
        readingDTOList = fileReader.readFile(file);
        return readingDTOList;
    }
}