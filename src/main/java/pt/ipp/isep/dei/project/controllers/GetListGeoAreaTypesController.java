package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.services.GeoAreaTypeService;

import java.util.List;

public class GetListGeoAreaTypesController {

    private GeoAreaTypeService geoAreaTypeService;


    public GetListGeoAreaTypesController(GeoAreaTypeService geoTypeService) {
        this.geoAreaTypeService = geoTypeService;
    }


    public List<String> getListaDosTiposDeAG() {
        return geoAreaTypeService.getListOfGeoAreaTypesToString();
    }
}
