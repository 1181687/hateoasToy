package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeoAreaList;
import pt.ipp.isep.dei.project.model.GeographicalArea;

public class US7Controller {
    private GeoAreaList mLista;

    public US7Controller(GeoAreaList geoAreaList) {
        this.mLista = geoAreaList;
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