package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicalArea;
import pt.ipp.isep.dei.project.model.GeographicalAreaList;

public class InsertedGeoAreaController {
    private GeographicalAreaList mLista;

    public InsertedGeoAreaController(GeographicalAreaList geographicalAreaList) {
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

    public boolean verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(int opcaoSelecionada1, int opcaoSelecionada2){
        return mLista.checkIfGeoAreaIsinsertedInAnother(opcaoSelecionada1, opcaoSelecionada2);
    }
}
