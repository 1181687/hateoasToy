package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeoAreaList;
import pt.ipp.isep.dei.project.model.GeographicalArea;

public class US7Controller {
    private GeoAreaList mLista;

    public US7Controller(GeoAreaList geoAreaList) {
        this.mLista = geoAreaList;
    }

    public String getConteudoLista (boolean usarCriterio){
        return mLista.listContent(usarCriterio);
    }

    public GeographicalArea getAGNaListaApresentada(int opcaoSelecionada) {
        return mLista.getGeographicalAreaInTheList(opcaoSelecionada);
    }

    public boolean verSeAGTemAreaInseridaVazia(GeographicalArea area) {
        return mLista.checkIfGeoAreaDoesntHaveAnInsertedArea(area);
    }

    public void adicionarAGListaPosicaoEspecifica(int posicao, GeographicalArea area) {
        mLista.addGeoAreaInASpecificPosition(posicao, area);
    }

    public void removerAGLista(GeographicalArea area) {
        mLista.removeGeoArea(area);
    }
}