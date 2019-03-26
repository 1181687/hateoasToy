package pt.ipp.isep.dei.project.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Reference;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.SensorReadingsRepository;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaRepository;
import pt.ipp.isep.dei.project.model.sensor.SensorListRepository;
import pt.ipp.isep.dei.project.SensorRepository;
/*
@Service
public class Repositories {


    @Autowired
    private static Repositories single_instance = null;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private  SensorReadingsRepository sensorReadingsRepository;

    @Autowired
    private GeoAreaRepository geoAreaRepository;

    @Autowired
    private SensorListRepository sensorListRepository;




    private Repositories() {
        //intentionally empty
    }

    // static method to create instance of Singleton class
    public static Repositories getInstance()
    {
        if (single_instance == null)
            single_instance = new Repositories();

        return single_instance;
    }


    public SensorRepository getSensorRepository (){
        return sensorRepository;
    }


    public SensorReadingsRepository getSensorReadingsRepository (){
        return sensorReadingsRepository;
    }

    public GeoAreaRepository getGeoAreaRepository(){
        return geoAreaRepository;
    }


    public SensorListRepository getSensorListRepository(){
        return sensorListRepository;
    }
}
*/