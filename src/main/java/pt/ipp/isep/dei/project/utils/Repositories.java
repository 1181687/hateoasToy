package pt.ipp.isep.dei.project.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.SensorReadingsRepository;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaRepository;
import pt.ipp.isep.dei.project.model.sensor.SensorListRepository;
import pt.ipp.isep.dei.project.model.sensor.SensorRepository;

@Service
public class Repositories {

    @Autowired
    private static SensorRepository sensorRepository;

    @Autowired
    private static SensorReadingsRepository sensorReadingsRepository;

    @Autowired
    private static GeoAreaRepository geoAreaRepository;

    @Autowired
    private static SensorListRepository sensorListRepository;



    private Repositories() {
        //intentionally empty
    }

    public static SensorRepository getSensorRepository (){
        return sensorRepository;
    }

    public static SensorReadingsRepository getSensorReadingsRepository (){
        return sensorReadingsRepository;
    }

    public static GeoAreaRepository getGeoAreaRepository(){
        return geoAreaRepository;
    }

    public static SensorListRepository getSensorListRepository(){
        return sensorListRepository;
    }
}
