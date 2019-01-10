package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeoAreaTypeList;

import java.util.List;

public class GetListGeoAreaTypesController {

    private GeoAreaTypeList mListaTAG;


    public GetListGeoAreaTypesController(GeoAreaTypeList listaTAG) {
        this.mListaTAG = listaTAG;
    }


    public List<String> getListaTiposDeAG (){
        return mListaTAG.getListOfGeoAreaTypes();
    }
}
