package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeDTO;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeMapper;
import pt.ipp.isep.dei.project.services.GeoAreaService;

import java.util.ArrayList;
import java.util.List;

public class AddSensorToGeoAreaController {

    GeoAreaService geoAreaService;


    public AddSensorToGeoAreaController(GeoAreaService geoAreaService) {
        this.geoAreaService = geoAreaService;
    }

    public boolean isGeoAreaRepositoryEmpty() {
        return this.geoAreaService.isGridRepositoryEmpty();
    }

    public List<GeographicalAreaDTO> getGeographicalAreaDTOList() {
        List<GeographicalArea> geoAreaList = this.geoAreaService.getAllGeoAreas();
        List<GeographicalAreaDTO> geographicalAreaDTOList = new ArrayList<>();
        for (GeographicalArea geoArea : geoAreaList) {
            GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTO(geoArea);
            geographicalAreaDTOList.add(geographicalAreaDTO);
        }
        return geographicalAreaDTOList;
    }

    public List<SensorTypeDTO> getSensorTypeDTOList() {
        List<SensorType> sensorTypeList = this.geoAreaService.getSensorTypeList();
        List<SensorTypeDTO> sensorTypeDTOList = new ArrayList<>();
        for (SensorType sensorType : sensorTypeList) {
            SensorTypeDTO sensorTypeDTO = SensorTypeMapper.mapToDto(sensorType);
            sensorTypeDTOList.add(sensorTypeDTO);
        }
        return sensorTypeDTOList;
    }

    public void addSensor(String id, String sensorName, SensorTypeId sensorTypeId, Location location, String units, GeoAreaId geoAreaId) {
        geoAreaService.addSensor(id, sensorName, sensorTypeId, location, units, geoAreaId);
    }

    public boolean isNameExistant(String name) {
        return this.geoAreaService.isNameExistant(name);
    }

}
