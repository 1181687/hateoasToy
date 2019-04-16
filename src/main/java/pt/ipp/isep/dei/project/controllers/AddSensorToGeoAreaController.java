package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AddSensorToGeoAreaController {

    GeoAreaSensorService geoAreaSensorService;


    public AddSensorToGeoAreaController(GeoAreaSensorService geoAreaSensorService) {
        this.geoAreaSensorService = geoAreaSensorService;
    }

    public List<GeographicalAreaDTO> getGeographicalAreaList() {

        List<GeographicalAreaDTO> geographicalAreaDTOList = new ArrayList<>();



    }


    public void addSensor(String id, String sensorName, LocalDateTime startingDate, SensorTypeId sensorTypeId, Location location, String units, GeoAreaId geoAreaId) {
        geoAreaSensorService.addSensor(id, sensorName, startingDate, sensorTypeId, location, units, geoAreaId);
    }

    public boolean isNameExistant(String name) {
        return this.geoAreaSensorService.isNameExistant(name);
    }

}
