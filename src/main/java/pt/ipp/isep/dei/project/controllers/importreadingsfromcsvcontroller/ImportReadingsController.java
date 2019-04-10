package pt.ipp.isep.dei.project.controllers.importreadingsfromcsvcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorList;
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
    private GeographicalAreaList geographicalAreaList;
    private GeoAreaSensorList allSensorInTheGeoAreas;
    private GeoAreaSensor sensor;
    private List<Object> readingDTOList;
    private int numberOfNotImportedReadings;

    /**
     * Constructor.
     *
     * @param geographicalAreaList GeographicalAreaList to be used.
     */
    public ImportReadingsController(GeographicalAreaList geographicalAreaList) {
        this.geographicalAreaList = geographicalAreaList;
        this.allSensorInTheGeoAreas = this.geographicalAreaList.getAllSensors();
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

    public int getNumberOfNotImportedReadings() {
        return this.numberOfNotImportedReadings;
    }

    public boolean addReadingToSensorById() {
        configLogFile();
        boolean imported = false;
        for (Object object : this.readingDTOList) {
            ReadingDTO reading = (ReadingDTO) object;
            sensor = allSensorInTheGeoAreas.getSensorById(reading.getId());
            if (Objects.isNull(sensor)) {
                numberOfNotImportedReadings++;
                continue;
            }
            if (isDateTimeBeforeSensorStartingDate(reading.getDateTime())) {
                numberOfNotImportedReadings++;
                String invalidInfo = "id: " + reading.getId() + ", value: " + reading.getValue() + ", timestamp/date: " + reading.getDateTime() + ", unit: " + reading.getUnits() + ".";
                LOGGER.log(Level.WARNING, "Reading not imported due to timestamp/date of reading being before starting date of sensor: " + invalidInfo);
                continue;
            }
            if (reading.getUnits().equals("F")) {
                double celsiusValue = Utils.convertFahrenheitToCelsius(reading.getValue());
                reading.setValue(Utils.round(celsiusValue, 2));
                reading.setUnits("C");
            }
            if (sensor.addReading(ReadingMapper.mapToEntity(reading))) {
                imported = true;
            }
        }
        /*if(imported){

            this.geographicalAreaList.updateRepository();
        }*/
        this.geographicalAreaList.updateRepository();
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