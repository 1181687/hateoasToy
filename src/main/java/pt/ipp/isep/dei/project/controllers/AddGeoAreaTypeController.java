package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.GeographicalAreaTypeList;


public class AddGeoAreaTypeController {
    private GeographicalAreaTypeList mListaTAG;

    public AddGeoAreaTypeController(GeographicalAreaTypeList listaTAG) {
        this.mListaTAG = listaTAG;
    }

    public boolean adicionaNovoTipoAreaGeografica(String tipoTAG){
        GeographicalAreaType novoTAG = mListaTAG.newTypeOfGeoArea(tipoTAG);
        return mListaTAG.addTypeOfGeoAreaToTheList(novoTAG);
    }

    public GeographicalAreaTypeList getListaTAG() {
        return mListaTAG;
    }
}

