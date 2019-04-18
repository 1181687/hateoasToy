package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaSensorReadingsRepository;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReading;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReadingId;

@Service
public class GeoAreaSensorReadingsService {

    @Autowired
    GeoAreaSensorReadingsRepository geoAreaSensorReadingsRepository;

    public GeoAreaSensorReadingsService() {
    }

    public boolean isReadingDuplicated(GeoAreaReadingId geoAreaReadingId){
        return geoAreaSensorReadingsRepository.existsById(geoAreaReadingId);
    }

    public boolean addReading(GeoAreaReading geoAreaReading) {
        GeoAreaReadingId geoAreaReadingId = new GeoAreaReadingId(geoAreaReading.getSensorId(), geoAreaReading.getDateTime());
        if (isReadingDuplicated(geoAreaReadingId)) {
            return false;
        }
        geoAreaSensorReadingsRepository.save(geoAreaReading);
        return true;
    }
}
