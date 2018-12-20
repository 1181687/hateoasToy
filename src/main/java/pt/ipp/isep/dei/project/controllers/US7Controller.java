package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicalArea;
import pt.ipp.isep.dei.project.model.ListaAG;

public class US7Controller {
    private ListaAG mLista;

    public US7Controller(ListaAG listaAG) {
        this.mLista = listaAG;
    }

    public String getConteudoLista (boolean usarCriterio){
        return mLista.conteudoLista(usarCriterio);
    }

    public GeographicalArea getAGNaListaApresentada(int opcaoSelecionada) {
        return mLista.getAreaGeograficaNaListaApresentada(opcaoSelecionada);
    }

    public boolean verSeAGTemAreaInseridaVazia(GeographicalArea area) {
        return mLista.verificarSeAGNaoTemAreaInserida (area);
    }

    public void adicionarAGListaPosicaoEspecifica(int posicao, GeographicalArea area) {
        mLista.adicionarAreaGeoAListaNumaPosicaoEspecifica(posicao,area);
    }

    public void removerAGLista(GeographicalArea area) {
        mLista.removerAreaGeoALista(area);
    }
}