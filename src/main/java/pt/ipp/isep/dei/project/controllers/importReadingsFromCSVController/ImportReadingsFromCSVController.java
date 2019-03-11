package pt.ipp.isep.dei.project.controllers.importReadingsFromCSVController;

import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;

public class ImportReadingsFromCSVController {
    private GeographicalAreaList geographicalAreaList;
    private SensorList allSensorInTheGeoAreas;
    private Sensor sensor;

    /**
     * Constructor.
     *
     * @param geographicalAreaList GeographicalAreaList to be used.
     */
    public ImportReadingsFromCSVController(GeographicalAreaList geographicalAreaList) {
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
     * Method that adds a reading to the sensor.
     *
     * @param readingDTO ReadingDTO to be transformed into a Reading and to be used.
     */
    public void addReadingToSensor(ReadingDTO readingDTO) {
        Reading reading = ReadingMapper.mapToEntity(readingDTO);
        sensor.addReadingsToList(reading);
    }
}
