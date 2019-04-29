package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;
import pt.ipp.isep.dei.project.services.SensorTypeService;

import java.util.ArrayList;
import java.util.List;

public class AddSensorToGeoAreaController {

    private GeographicalAreaService geographicalAreaService;
    private SensorTypeService sensorTypeService;
    private GeoAreaSensorService geoAreaSensorService;


    public AddSensorToGeoAreaController(GeographicalAreaService geographicalAreaService, SensorTypeService sensorTypeService, GeoAreaSensorService geoAreaSensorService) {
        this.geographicalAreaService = geographicalAreaService;
        this.sensorTypeService = sensorTypeService;
        this.geoAreaSensorService = geoAreaSensorService;
    }

    public boolean isGeoAreaRepositoryEmpty() {
        return this.geographicalAreaService.isGeoAreaRepositoryEmpty();
    }

    public List<GeographicalAreaDTO> getGeographicalAreaDTOList() {
        List<GeographicalArea> geoAreaList = this.geographicalAreaService.getGeoAreaList();
        List<GeographicalAreaDTO> geographicalAreaDTOList = new ArrayList<>();
        for (GeographicalArea geoArea : geoAreaList) {
            GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTOwithSensors(geoArea);
            geographicalAreaDTOList.add(geographicalAreaDTO);
        }
        return geographicalAreaDTOList;
    }

    public List<SensorTypeDTO> getSensorTypeDTOList() {
        List<SensorType> sensorTypeList = this.sensorTypeService.getSensorTypeList();
        List<SensorTypeDTO> sensorTypeDTOList = new ArrayList<>();
        for (SensorType sensorType : sensorTypeList) {
            SensorTypeDTO sensorTypeDTO = SensorTypeMapper.mapToDto(sensorType);
            sensorTypeDTOList.add(sensorTypeDTO);
        }
        return sensorTypeDTOList;
    }

    public boolean addGeoAreaSensor(GeoAreaSensorDTO geoAreaSensorDTO) {
        return this.geoAreaSensorService.addGeoAreaSensor(GeoAreaSensorMapper.mapToEntity(geoAreaSensorDTO));
    }


}
