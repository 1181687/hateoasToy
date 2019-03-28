package pt.ipp.isep.dei.project.controllers.importreadingsfromcsvcontroller;

import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;
import pt.ipp.isep.dei.project.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class ImportReadingsFromCSVXMLJSONController {
    private static final Logger LOGGER = Logger.getLogger(ImportReadingsFromCSVXMLJSONController.class.getName());
    private GeographicalAreaList geographicalAreaList;
    private SensorList allSensorInTheGeoAreas;
    private Sensor sensor;
    private List<Object> readingDTOList;

    /**
     * Constructor.
     *
     * @param geographicalAreaList GeographicalAreaList to be used.
     */
    public ImportReadingsFromCSVXMLJSONController(GeographicalAreaList geographicalAreaList) {
        this.geographicalAreaList = geographicalAreaList;
        this.allSensorInTheGeoAreas = this.geographicalAreaList.getAllSensors();
    }

    /**
     * Method that checks if a sensor exists in the list by its id and, if so, stores it.
     *
     * @param sensorId Id of the sensor.
     * @return True or false.
     */
    public boolean checkIfSensorExistsById(String sensorId) {
        if (allSensorInTheGeoAreas.checkIfSensorExistsById(sensorId)) {
            sensor = allSensorInTheGeoAreas.getSensorById(sensorId);
            return true;
        }
        return false;
    }

    /**
     * Method that checks if a given date time is before the starting date of the sensor.
     *
     * @param localDateTime Given date time to be compared.
     * @return True or False.
     */
    public boolean isDateTimeBeforeSensorStartingDate(LocalDateTime localDateTime) {
        return localDateTime.isBefore(sensor.getStartingDate());
    }

    /**
     * Method that adds a reading to the sensor.
     *
     * @param readingDTO ReadingDTO to be transformed into a Reading and to be used.
     */
    public void addReadingToSensor(ReadingDTO readingDTO) {
        Reading reading = ReadingMapper.mapToEntity(readingDTO);
        sensor.addReadingsToList(reading);
    }

    public boolean addReadingToSensorById() {
        configLogFile();
        boolean imported = false;
        for (Object object : this.readingDTOList) {
            ReadingDTO reading = (ReadingDTO) object;
            sensor = allSensorInTheGeoAreas.getSensorById(reading.getID());
            if (reading.getUnits().equals("F")) {
                double celsiusValue = Utils.convertFahrenheitToCelsius(reading.getValue());
                reading.setValue(Utils.round(celsiusValue, 2));
                reading.setUnits("C");
            }
            if (sensor.addReading(ReadingMapper.mapToEntity(reading))) {
                imported = true;
            }
        }
        return imported;
    }

    public boolean isValidFormat(String fileName) {
        return fileName.endsWith(".csv") || fileName.endsWith(".json") || fileName.endsWith(".xml");
    }

    public void updateGeoAreaRepository() {
        geographicalAreaList.updateRepository();
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