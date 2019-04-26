package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeList;

import java.util.List;

public class GetListGeoAreaTypesController {

    private GeographicalAreaTypeList geographicalAreaTypeList;


    public GetListGeoAreaTypesController(GeographicalAreaTypeList listaTAG) {
        this.geographicalAreaTypeList = listaTAG;
    }


    public List<String> getListaTiposDeAG() {
        return geographicalAreaTypeList.getListOfGeoAreaTypes();
    }
}
