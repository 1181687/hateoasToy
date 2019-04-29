package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;

public class SensorsService {

    @Autowired
    private GeoAreaSensorService geoAreaSensorService;

    @Autowired
    private RoomSensorService roomSensorService;


}
