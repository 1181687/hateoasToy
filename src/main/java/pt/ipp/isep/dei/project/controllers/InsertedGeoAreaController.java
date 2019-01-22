package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeoAreaList;
import pt.ipp.isep.dei.project.model.GeographicalArea;

public class InsertedGeoAreaController {
    private GeoAreaList mLista;

    public InsertedGeoAreaController(GeoAreaList geoAreaList) {
        this.mLista = geoAreaList;
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
