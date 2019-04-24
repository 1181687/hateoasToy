package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.services.GeoAreaAggregateService;

import java.util.ArrayList;
import java.util.List;

public class AddNewGeographicalAreaController {

    private GeoAreaAggregateService geoAreaAggregateService;

    public AddNewGeographicalAreaController(GeoAreaAggregateService geoAreaAggregateService) {
        this.geoAreaAggregateService = geoAreaAggregateService;
    }

    public void addGeographicalArea(GeographicalAreaDTO geographicalAreaDTO) {
        geoAreaAggregateService.addGeographicalArea(GeographicalAreaMapper.mapToEntity(geographicalAreaDTO));
    }


    /**
     * method that checks if a geo area exists.
     *
     * @param
     */
    public boolean isGeoAreaExistant(String geoAreaId, double latitude, double longitude, double elevation, String geoAreaTypeId) {
        return this.geoAreaAggregateService.isGeoAreaExistant(geoAreaId, latitude, longitude, elevation, geoAreaTypeId);
    }

    public List<GeographicalAreaTypeDTO> getGeoAreaTypeList() {
        List<GeographicalAreaType> geoAreaTypes = this.geoAreaAggregateService.listOfGeoAreaTypes();
        List<GeographicalAreaTypeDTO> geoAreaTypesDTOS = new ArrayList<>();
        for (GeographicalAreaType geoAreaType : geoAreaTypes) {
            GeographicalAreaTypeDTO geographicalAreaTypeDTO = GeographicalAreaTypeMapper.mapToDTO(geoAreaType);
            geoAreaTypesDTOS.add(geographicalAreaTypeDTO);
        }
        return geoAreaTypesDTOS;
    }
}
