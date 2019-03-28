package pt.ipp.isep.dei.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoAreaService {

    @Autowired
    private static GeoAreaService single_instance = null;


    @Autowired
    private GeoAreaRepository geoAreaRepository;


    private GeoAreaService() {
        //intentionally empty
    }

    // static method to create instance of Singleton class
    public static GeoAreaService getInstance() {
        if (single_instance == null)
            single_instance = new GeoAreaService();

        return single_instance;
    }

    public GeoAreaRepository getGeoAreaRepository() {
        return geoAreaRepository;
    }

    public void setGeoAreaRepository(GeoAreaRepository geoAreaRepository) {
        this.geoAreaRepository = geoAreaRepository;
    }
}


