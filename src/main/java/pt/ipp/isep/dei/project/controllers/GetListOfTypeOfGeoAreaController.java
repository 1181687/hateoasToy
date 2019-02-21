package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.GeographicalAreaTypeList;

import java.util.List;

public class GetListOfTypeOfGeoAreaController {

    private GeographicalAreaList geographicalAreaList;
    private GeographicalAreaTypeList geographicalAreaTypeList;

    public GetListOfTypeOfGeoAreaController(GeographicalAreaList geographicalAreaList, GeographicalAreaTypeList listaTAG) {
        this.geographicalAreaList = geographicalAreaList;
        this.geographicalAreaTypeList = listaTAG;
    }

    public List<String> getListaAGPorTipo(String tipo) {
        return this.geographicalAreaList.getListOfGeographicalAreasByType(tipo);
    }

    public List<String> getListaDosTiposDeAG(){
        return geographicalAreaTypeList.getListOfGeoAreaTypes();
    }



}