package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeoAreaList;
import pt.ipp.isep.dei.project.model.GeoAreaTypeList;
import pt.ipp.isep.dei.project.model.GeographicalArea;

import java.util.List;

public class US3Controller {
    private GeoAreaList mGeoAreaList;
    private GeoAreaTypeList mListaTAG;

    public US3Controller(GeoAreaList geoAreaList, GeoAreaTypeList listaTAG) {
        this.mGeoAreaList = geoAreaList;
        this.mListaTAG = listaTAG;
    }

    public boolean adicionarNovaAG(GeographicalArea novaAG) {
        return mGeoAreaList.adicionarAreaGeoALista(novaAG);
    }

    public GeoAreaList getListaAG() {
        return mGeoAreaList;
    }

    public List<String> getListaTAG() {
        return mListaTAG.getListaDosTiposDeAG();
    }

    public GeographicalArea criarNovaAG(String nomeAG, String nomeTipoAG, double altitude, double longitude, double latitude, double largura, double comprimento) {
        return mGeoAreaList.novaAreaGeografica(nomeAG, nomeTipoAG, altitude, longitude, latitude, largura, comprimento);

    }
}
