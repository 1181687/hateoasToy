package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeoAreaTypeList;

import java.util.List;

public class US2Controller {

    private GeoAreaTypeList mListaTAG;


    public US2Controller(GeoAreaTypeList listaTAG) {
        this.mListaTAG = listaTAG;
    }


    public List<String> getListaTiposDeAG (){
        return mListaTAG.getListaDosTiposDeAG();
    }
}
