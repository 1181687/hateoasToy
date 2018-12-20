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
        return mListaAG.adicionarAreaGeoALista(novaAG);
    }

    public ListaAG getListaAG() {
        return mListaAG;
    }

    public List<String> getListaTAG() {
        return mListaTAG.getListaDosTiposDeAG();
    }

    public AreaGeografica criarNovaAG(String nomeAG, String nomeTipoAG, double altitude, double longitude, double latitude, double largura, double comprimento) {
       return mListaAG.novaAreaGeografica(nomeAG, nomeTipoAG, altitude, longitude, latitude, largura, comprimento);

    }
}
