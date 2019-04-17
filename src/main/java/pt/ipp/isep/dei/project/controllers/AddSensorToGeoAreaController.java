package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.sensor.*;
import pt.ipp.isep.dei.project.services.GeoAreaService;

import java.util.ArrayList;
import java.util.List;

public class AddSensorToGeoAreaController {

    GeoAreaService geoAreaService;


    public AddSensorToGeoAreaController(GeoAreaService geoAreaService) {
        this.geoAreaService = geoAreaService;
    }

    public boolean isGeoAreaRepositoryEmpty() {
        return this.geoAreaService.isGeoAreaRepositoryEmpty();
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

    public boolean isNameExistant(String name) {
        return this.geoAreaService.isNameExistant(name);
    }


    public boolean addGeoAreaSensor(GeoAreaSensorDTO geoAreaSensorDTO) {

        return geoAreaService.addGeoAreaSensor(GeoAreaSensorMapper.)


        this.roomService.addRoomSensor(RoomSensorMapper.mapToEntity(sensorDTO))
    }


}
