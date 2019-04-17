package pt.ipp.isep.dei.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoAreaAggregateRepository {

    @Autowired
    private GeoAreaRepository geoAreaRepo;

    @Autowired
    private GeoAreaSensorRepository geoAreaSensorRepo;

    @Autowired
    private GeoAreaSensorReadingsRepository geoAreaReadingRepo;

    /**
     * Constructor.
     */
    public GeoAreaAggregateRepository() {
        // empty
    }

    /**
     * Get method.
     *
     * @return GeoAreaRepository.
     */
    public GeoAreaRepository getGeoAreaRepo() {
        return geoAreaRepo;
    }

    /**
     * Get method.
     *
     * @return GeoAreaSensorRepository.
     */
    public GeoAreaSensorRepository getGeoAreaSensorRepo() {
        return geoAreaSensorRepo;
    }

    /**
     * Get method.
     *
     * @return GeoAreaSensorReadingsRepository.
     */
    public GeoAreaSensorReadingsRepository getGeoAreaReadingRepo() {
        return geoAreaReadingRepo;
    }
}
