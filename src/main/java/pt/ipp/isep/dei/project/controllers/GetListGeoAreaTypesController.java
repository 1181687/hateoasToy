package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicalAreaTypeList;

import java.util.List;

public class GetListGeoAreaTypesController {

    private GeographicalAreaTypeList mListaTAG;


    public GetListGeoAreaTypesController(GeographicalAreaTypeList listaTAG) {
        this.mListaTAG = listaTAG;
    }


    public List<String> getListaTiposDeAG (){
        return mListaTAG.getListOfGeoAreaTypes();
    }
}
