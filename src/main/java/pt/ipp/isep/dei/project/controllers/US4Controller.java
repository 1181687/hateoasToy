package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeoAreaList;
import pt.ipp.isep.dei.project.model.GeoAreaTypeList;

import java.util.List;

public class US4Controller {

    private GeoAreaList mGeoAreaList;
    private GeoAreaTypeList mListaTAG;

    public US4Controller(GeoAreaList geoAreaList, GeoAreaTypeList listaTAG) {
        this.mGeoAreaList = geoAreaList;
        this.mListaTAG = listaTAG;
    }

    public List<String> getListaAGPorTipo(String tipo) {
        return this.mGeoAreaList.getListaAGPorTipo(tipo);
    }

    public List<String> getListaDosTiposDeAG(){
        return mListaTAG.getListaDosTiposDeAG();
    }



}