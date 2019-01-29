package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.GeographicalAreaTypeList;

import java.util.List;

public class GetListOfTypeOfGeoAreaController {

    private GeographicalAreaList mGeographicalAreaList;
    private GeographicalAreaTypeList mListaTAG;

    public GetListOfTypeOfGeoAreaController(GeographicalAreaList geographicalAreaList, GeographicalAreaTypeList listaTAG) {
        this.mGeographicalAreaList = geographicalAreaList;
        this.mListaTAG = listaTAG;
    }

    public List<String> getListaAGPorTipo(String tipo) {
        return this.mGeographicalAreaList.getListOfGeographicalAreasByType(tipo);
    }

    public List<String> getListaDosTiposDeAG(){
        return mListaTAG.getListOfGeoAreaTypes();
    }



}