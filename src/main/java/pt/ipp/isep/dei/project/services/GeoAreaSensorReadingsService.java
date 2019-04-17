package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaSensorReadingsRepository;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReading;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;

import java.util.List;
import java.util.Objects;

@Service
public class GeoAreaSensorReadingsService {

    @Autowired
    GeoAreaSensorReadingsRepository geoAreaSensorReadingsRepository;

    /**
     * Method that returns the list of readings from a sensor by its id.
     *
     * @param id Id of the sensor.
     * @return List of GeoAreaReading.
     */
    public List<GeoAreaReading> getGeoAreaReadingsBySensorId(GeoAreaSensorId id) {
        return geoAreaSensorReadingsRepository.findByGeoAreaReadingId_GeoAreaSensorId(id);
    }

    /**
     * Method that returns the most recent valid reading from a set of readings of a sensor. If the most recent
     * doesn't have a valid value (for example, NaN), it is not accepted as a valid result and, therefore, doesn't get
     * stored as the most recent reading.
     *
     * @param id Id of the sensor.
     * @return Most recent (valid) GeoAreaReading.
     */
    public GeoAreaReading getMostRecentValidReading(GeoAreaSensorId id) {
        List<GeoAreaReading> readings = getGeoAreaReadingsBySensorId(id);
        GeoAreaReading mostRecentReading = null;
        for (GeoAreaReading reading : readings) {
            if (Objects.isNull(mostRecentReading)
                    || reading.getDateTime().isAfter(mostRecentReading.getDateTime()) && !Double.isNaN(reading.getValue())) {
                mostRecentReading = reading;
            }
        }
        return mostRecentReading;
    }
}
