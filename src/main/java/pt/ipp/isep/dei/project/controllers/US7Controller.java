package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.ListaAG;

public class US7Controller {
    private ListaAG mLista;

    public US7Controller(ListaAG listaAG) {
        this.mLista = listaAG;
    }

    public String getConteudoLista (boolean usarCriterio){
        return mLista.conteudoLista(usarCriterio);
    }

    public GeographicArea getAGNaListaApresentada(int opcaoSelecionada) {
        return mLista.getAreaGeograficaNaListaApresentada(opcaoSelecionada);
    }

    public boolean verSeAGTemAreaInseridaVazia(GeographicArea area) {
        return mLista.verificarSeAGNaoTemAreaInserida (area);
    }

    public void adicionarAGListaPosicaoEspecifica(int posicao, GeographicArea area) {
        mLista.adicionarAreaGeoAListaNumaPosicaoEspecifica(posicao,area);
    }

    public void removerAGLista(GeographicArea area) {
        mLista.removerAreaGeoALista(area);
    }
}