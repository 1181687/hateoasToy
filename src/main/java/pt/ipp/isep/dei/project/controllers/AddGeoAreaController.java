package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeoAreaList;
import pt.ipp.isep.dei.project.model.GeoAreaTypeList;
import pt.ipp.isep.dei.project.model.GeographicalArea;

import java.util.List;

public class AddGeoAreaController {
    private GeoAreaList mGeoAreaList;
    private GeoAreaTypeList mListaTAG;

    public AddGeoAreaController(GeoAreaList geoAreaList, GeoAreaTypeList listaTAG) {
        this.mGeoAreaList = geoAreaList;
        this.mListaTAG = listaTAG;
    }

    public boolean adicionarNovaAG(GeographicalArea novaAG) {
        return mGeoAreaList.addGeoArea(novaAG);
    }

    public GeoAreaList getListaAG() {
        return mGeoAreaList;
    }

    public List<String> getListaTAG() {
        return mListaTAG.getListOfGeoAreaTypes();
    }

    public GeographicalArea criarNovaAG(String nomeAG, String nomeTipoAG, double altitude, double longitude, double latitude, double largura, double comprimento) {
        return mGeoAreaList.newGeographicalArea(nomeAG, nomeTipoAG, altitude, longitude, latitude, largura, comprimento);

    }
}