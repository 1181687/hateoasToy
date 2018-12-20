package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.AreaGeografica;
import pt.ipp.isep.dei.project.model.ListaAG;

public class US8Controller {
    private ListaAG mLista;

    public US8Controller(ListaAG listaAG) {
        this.mLista = listaAG;
    }

    public String getConteudoLista (boolean usarCriterio){
        return mLista.conteudoLista(usarCriterio);
    }

    public AreaGeografica getAGNaListaApresentada(int opcaoSelecionada) {
        return mLista.getAreaGeograficaNaListaApresentada(opcaoSelecionada);
    }

    public boolean verSeAGTemAreaInseridaVazia(AreaGeografica area) {
        return mLista.verificarSeAGNaoTemAreaInserida (area);
    }

    public boolean verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(int opcaoSelecionada1, int opcaoSelecionada2){
        return mLista.verificarSeAGEstaContidaNoutra(opcaoSelecionada1,opcaoSelecionada2);
    }
}
