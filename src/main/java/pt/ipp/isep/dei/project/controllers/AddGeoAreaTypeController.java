package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeoAreaType;
import pt.ipp.isep.dei.project.model.GeoAreaTypeList;


public class AddGeoAreaTypeController {
    private GeoAreaTypeList mListaTAG;

    public AddGeoAreaTypeController(GeoAreaTypeList listaTAG) {
        this.mListaTAG = listaTAG;
    }

    public boolean adicionaNovoTipoAreaGeografica(String tipoTAG){
        GeoAreaType novoTAG = mListaTAG.newTypeOfGeoArea(tipoTAG);
        return mListaTAG.addTypeOfGeoAreaToTheList(novoTAG);
    }

    public GeoAreaTypeList getListaTAG() {
        return mListaTAG;
    }
}

