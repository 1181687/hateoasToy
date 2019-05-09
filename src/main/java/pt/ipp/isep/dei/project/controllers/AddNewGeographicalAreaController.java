package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.services.GeoAreaTypeService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.util.ArrayList;
import java.util.List;

public class AddNewGeographicalAreaController {

    private final GeographicalAreaService geographicalAreaService;
    private final GeoAreaTypeService geoAreaTypeService;

    public AddNewGeographicalAreaController(GeographicalAreaService geographicalAreaService, GeoAreaTypeService geoAreaTypeService) {
        this.geographicalAreaService = geographicalAreaService;
        this.geoAreaTypeService = geoAreaTypeService;
    }

    public boolean addGeographicalArea(GeographicalAreaDTO geographicalAreaDTO) {
        return geographicalAreaService.addGeoArea(GeographicalAreaMapper.mapToEntity(geographicalAreaDTO));
    }

    /**
     * method that checks if a geo area exists.
     *
     * @param
     */
    public boolean isGeoAreaExistant(String geoAreaId, double latitude, double longitude, double elevation, String geoAreaTypeId) {
        return this.geographicalAreaService.isGeoAreaExistant(geoAreaId, latitude, longitude, elevation, geoAreaTypeId);
    }

    public List<GeographicalAreaTypeDTO> getGeoAreaTypeList() {
        List<GeographicalAreaType> geoAreaTypes = this.geoAreaTypeService.getListOfGeoAreaTypes();
        List<GeographicalAreaTypeDTO> geoAreaTypesDTOS = new ArrayList<>();
        for (GeographicalAreaType geoAreaType : geoAreaTypes) {
            GeographicalAreaTypeDTO geographicalAreaTypeDTO = GeographicalAreaTypeMapper.mapToDTO(geoAreaType);
            geoAreaTypesDTOS.add(geographicalAreaTypeDTO);
        }
        return geoAreaTypesDTOS;
    }
}
