package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.AreaGeografica;
import pt.ipp.isep.dei.project.model.ListaAG;
import pt.ipp.isep.dei.project.model.ListaTiposAG;

import java.util.List;

public class US3Controller {
    private ListaAG mListaAG;
    private ListaTiposAG mListaTAG;

    public US3Controller(ListaAG listaAG, ListaTiposAG listaTAG) {
        this.mListaAG = listaAG;
        this.mListaTAG = listaTAG;
    }

    public boolean adicionarNovaAG(AreaGeografica novaAG) {
        if ((!(this.mListaAG.getmListaAG().contains(novaAG)))) {
            mListaAG.adicionarAreaGeoALista(novaAG);
            return true;
        }
        return false;
    }

    public ListaAG getListaAG() {
        return mListaAG;
    }

    public List<String> getListaTAG () {
        return mListaTAG.getListaDosTiposDeAG();
    }

}
