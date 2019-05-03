package pt.ipp.isep.dei.project.controllers.importreadingsfromcsvcontroller;

import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorIdDTO;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;
import pt.ipp.isep.dei.project.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportGeoAreaReadingsController {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private GeoAreaSensorService geoAreaSensorService;
    private GeoAreaSensorDTO sensorDTO;
    private ProjectFileReader reader;
    private List<Object> readingDTOs;
    private int numberOfNotImportedReadings;

    /**
     * Constructor.
     *
     * @param geoAreaSensorService Service to be used.
     */
    public ImportGeoAreaReadingsController(GeoAreaSensorService geoAreaSensorService) {
        this.geoAreaSensorService = geoAreaSensorService;
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
        readingDTOs = this.reader.readFile(file);
        return readingDTOs;
    }

    /**
     * Method that imports the readings.
     *
     * @return True or false.
     */
    public boolean importReadings() {
        double startTime = System.nanoTime();
        boolean imported = false;
        boolean save = false;
        List<GeoAreaSensorDTO> sensorDTOs = new ArrayList<>();
        for (int i = 0; i < readingDTOs.size(); i++) {
            ReadingDTO readingDTO = (ReadingDTO) readingDTOs.get(i);
            SensorIdDTO sensorIdDTO = new SensorIdDTO();
            sensorIdDTO.setId(readingDTO.getId());
            if (Objects.isNull(sensorDTO)) {
                sensorDTO = geoAreaSensorService.getSensorById(sensorIdDTO);
            }
            if (Objects.nonNull(sensorDTO) && !sensorIdDTO.getId().equals(sensorDTO.getId())) {
                sensorDTOs.add(sensorDTO);
                save = true;
                sensorDTO = geoAreaSensorService.getSensorById(sensorIdDTO);
            }
            if (!isReadingValid(readingDTO)) {
                if (i == (readingDTOs.size() - 1)) {
                    sensorDTOs.add(sensorDTO);
                }
                continue;
            } else {
                if (i == (readingDTOs.size() - 1)) {
                    sensorDTOs.add(sensorDTO);
                }
                imported = true;
            }
            if (sensorDTO.addReadingDTO(readingDTO) && save) {
                save = false;
            }
        }
        geoAreaSensorService.saveSensors(sensorDTOs);
        double stopTime = System.nanoTime();
        System.out.println("Total time = " + ((stopTime - startTime) / 1000000000));
        return imported;
    }

    /**
     * Method that validates the information of the ReadingDTO.
     *
     * @return True or false.
     */
    private boolean isReadingValid(ReadingDTO readingDTO) {
        if (Objects.isNull(sensorDTO)) {
            numberOfNotImportedReadings++;
            String invalidInfo = "id: " + readingDTO.getId() + ".";
            LOGGER.log(Level.WARNING, "GeoAreaReading was not imported because the following sensor id doesn't exist: " + invalidInfo);
            return false;
        }
        if (Objects.isNull(readingDTO.getDateTime())) {
            numberOfNotImportedReadings++;
            String invalidInfo = "id: " + readingDTO.getId() + ", value: " + readingDTO.getValue() + ", timestamp/date: " + readingDTO.getDateTime() + ", unit: " + readingDTO.getUnits() + ".";
            LOGGER.log(Level.WARNING, "GeoAreaReading not imported due to invalid timestamp/date " + invalidInfo);
            return false;
        }
        if (readingDTO.getDateTime().toLocalDate().isBefore(sensorDTO.getStartingDate())) {
            numberOfNotImportedReadings++;
            String invalidInfo = "id: " + readingDTO.getId() + ", value: " + readingDTO.getValue() + ", timestamp/date: " + readingDTO.getDateTime() + ", unit: " + readingDTO.getUnits() + ".";
            LOGGER.log(Level.WARNING, "GeoAreaReading not imported due to timestamp/date of reading being before starting date of sensor: " + invalidInfo);
            return false;
        }
        if (readingDTO.getUnits().equals("F")) {
            double celsiusValue = Utils.convertFahrenheitToCelsius(readingDTO.getValue());
            readingDTO.setValue(Utils.round(celsiusValue, 2));
            readingDTO.setUnits("C");
            return true;
        }
        return true;
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