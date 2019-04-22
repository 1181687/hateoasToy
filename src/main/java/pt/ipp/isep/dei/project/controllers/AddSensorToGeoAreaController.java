package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.services.GeoAreaAggregateService;

import java.util.ArrayList;
import java.util.List;

public class AddSensorToGeoAreaController {

    GeoAreaAggregateService geoAreaAggregateService;


    public AddSensorToGeoAreaController(GeoAreaAggregateService geoAreaAggregateService) {
        this.geoAreaAggregateService = geoAreaAggregateService;
    }

    public boolean isGeoAreaRepositoryEmpty() {
        return this.geoAreaAggregateService.isGeoAreaRepositoryEmpty();
    }

    public List<GeographicalAreaDTO> getGeographicalAreaDTOList() {
        List<GeographicalArea> geoAreaList = this.geoAreaAggregateService.getAllGeoAreas();
        List<GeographicalAreaDTO> geographicalAreaDTOList = new ArrayList<>();
        for (GeographicalArea geoArea : geoAreaList) {
            GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTO(geoArea);
            geographicalAreaDTOList.add(geographicalAreaDTO);
        }
        return geographicalAreaDTOList;
    }

    public List<SensorTypeDTO> getSensorTypeDTOList() {
        List<SensorType> sensorTypeList = this.geoAreaAggregateService.getSensorTypeList();
        List<SensorTypeDTO> sensorTypeDTOList = new ArrayList<>();
        for (SensorType sensorType : sensorTypeList) {
            SensorTypeDTO sensorTypeDTO = SensorTypeMapper.mapToDto(sensorType);
            sensorTypeDTOList.add(sensorTypeDTO);
        }
        return sensorTypeDTOList;
    }

    public boolean addGeoAreaSensor(GeoAreaSensorDTO geoAreaSensorDTO) {
        return geoAreaAggregateService.addGeoAreaSensor(GeoAreaSensorMapper.mapToEntity(geoAreaSensorDTO));
    }


}
