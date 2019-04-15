package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaService;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeList;

import java.util.List;

public class GetListOfTypeOfGeoAreaController {

    private GeographicalAreaService geographicalAreaService;
    private GeographicalAreaTypeList geographicalAreaTypeList;

    public GetListOfTypeOfGeoAreaController(GeographicalAreaService geographicalAreaService, GeographicalAreaTypeList listaTAG) {
        this.geographicalAreaService = geographicalAreaService;
        this.geographicalAreaTypeList = listaTAG;
    }

    public List<String> getListaAGPorTipo(String tipo) {
        return this.geographicalAreaService.getListOfGeographicalAreasByType(tipo);
    }

    public List<String> getListaDosTiposDeAG() {
        return geographicalAreaTypeList.getListOfGeoAreaTypes();
    }


}