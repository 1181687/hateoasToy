package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.services.GeoAreaTypeService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.util.List;

public class GetListOfTypeOfGeoAreaController {

    private GeographicalAreaService geographicalAreaService;
    private GeoAreaTypeService geoAreaTypeService;

    public GetListOfTypeOfGeoAreaController(GeographicalAreaService geographicalAreaService, GeoAreaTypeService geoTypeService) {
        this.geographicalAreaService = geographicalAreaService;
        this.geoAreaTypeService = geoTypeService;
    }

    public List<String> getListaAGPorTipo(String tipo) {
        return this.geographicalAreaService.getListOfGeographicalAreasByType(tipo);
    }

    public List<String> getListaDosTiposDeAG() {
        return geoAreaTypeService.getListOfGeoAreaTypesToString();
    }
}