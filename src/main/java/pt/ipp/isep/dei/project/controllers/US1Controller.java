package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.ListaTiposAG;
import pt.ipp.isep.dei.project.model.TipoAreaGeo;


public class US1Controller {
    private ListaTiposAG mListaTAG;

    public US1Controller(ListaTiposAG listaTAG) {
        this.mListaTAG = listaTAG;
    }

    public boolean adicionaNovoTipoAreaGeografica(String tipoTAG){
        TipoAreaGeo novoTAG = mListaTAG.novoTipoAG(tipoTAG);
        return mListaTAG.adicionarElementoALista(novoTAG);
    }

    public ListaTiposAG getListaTAG() {
        return mListaTAG;
    }
}

