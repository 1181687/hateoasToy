package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicalArea;
import pt.ipp.isep.dei.project.model.GeographicalAreaList;

public class AddGeoAreaToAnotherGeoAreaController {
    private GeographicalAreaList mLista;

    public AddGeoAreaToAnotherGeoAreaController(GeographicalAreaList geographicalAreaList) {
        this.mLista = geographicalAreaList;
    }

    public String getConteudoLista (boolean usarCriterio){
        return mLista.getGeoAreaListToString(usarCriterio);
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

    public int getListSize(){
        return mLista.getSize();
    }
}

