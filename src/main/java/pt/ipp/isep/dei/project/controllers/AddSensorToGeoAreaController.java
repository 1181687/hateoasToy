package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
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

    public List<GeographicalAreaDTO> getGeographicalAreaList() {
        List<GeographicalArea> geoAreaList = this.geoAreaService.getAllGeoAreas();
        List<GeographicalAreaDTO> geographicalAreaDTOList = new ArrayList<>();
        for (GeographicalArea geoArea : geoAreaList) {
            GeographicalAreaDTO geographicalAreaDTODTO = GeographicalAreaMapper.mapToDTO(geoArea);
            geographicalAreaDTOList.add(geographicalAreaDTODTO);
        }
        return geographicalAreaDTOList;
    }




    /*public void addSensor(String id, String sensorName, LocalDateTime startingDate, SensorTypeId sensorTypeId, Location location, String units, GeoAreaId geoAreaId) {
        geoAreaSensorService.addSensor(id, sensorName, startingDate, sensorTypeId, location, units, geoAreaId);
    }

    public boolean isNameExistant(String name) {
        return this.geoAreaSensorService.isNameExistant(name);
    }*/

}
