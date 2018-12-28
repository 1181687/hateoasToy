package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeoAreaType;
import pt.ipp.isep.dei.project.model.GeoAreaTypeList;


public class US1Controller {
    private GeoAreaTypeList mListaTAG;

    public US1Controller(GeoAreaTypeList listaTAG) {
        this.mListaTAG = listaTAG;
    }

    public boolean adicionaNovoTipoAreaGeografica(String tipoTAG){
        GeoAreaType novoTAG = mListaTAG.novoTipoAG(tipoTAG);
        return mListaTAG.adicionarElementoALista(novoTAG);
    }

    public GeoAreaTypeList getListaTAG() {
        return mListaTAG;
    }
}

