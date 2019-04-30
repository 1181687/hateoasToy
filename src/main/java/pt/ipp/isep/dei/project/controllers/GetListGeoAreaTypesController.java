package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeList;
import pt.ipp.isep.dei.project.services.GeoAreaTypeService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

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
