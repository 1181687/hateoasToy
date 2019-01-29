package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicalArea;
import pt.ipp.isep.dei.project.model.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.GeographicalAreaTypeList;

import java.util.List;

public class AddGeoAreaController {
    private GeographicalAreaList mGeographicalAreaList;
    private GeographicalAreaTypeList mListaTAG;

    public AddGeoAreaController(GeographicalAreaList geographicalAreaList, GeographicalAreaTypeList listaTAG) {
        this.mGeographicalAreaList = geographicalAreaList;
        this.mListaTAG = listaTAG;
    }

    public boolean adicionarNovaAG(GeographicalArea novaAG) {
        return mGeographicalAreaList.addGeoArea(novaAG);
    }

    public GeographicalAreaList getListaAG() {
        return mGeographicalAreaList;
    }

    public List<String> getListaTAG() {
        return mListaTAG.getListOfGeoAreaTypes();
    }

    public GeographicalArea criarNovaAG(String nomeAG, String nomeTipoAG, double altitude, double longitude, double latitude, double largura, double comprimento) {
        return mGeographicalAreaList.newGeographicalArea(nomeAG, nomeTipoAG, altitude, longitude, latitude, largura, comprimento);

    }
}
