package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;

public class InsertedGeoAreaController {
    private GeographicalAreaList geographicalAreaList;

    public InsertedGeoAreaController(GeographicalAreaList geographicalAreaList) {
        this.geographicalAreaList = geographicalAreaList;
    }

    public String getConteudoLista (boolean usarCriterio){
        return geographicalAreaList.getGeoAreaListToString(usarCriterio);
    }

    public GeographicalArea getAGNaListaApresentada(int opcaoSelecionada) {
        return geographicalAreaList.getGeographicalAreaInTheList(opcaoSelecionada);
    }

    public boolean verSeAGTemAreaInseridaVazia(GeographicalArea area) {
        return geographicalAreaList.checkIfGeoAreaDoesntHaveAnInsertedArea(area);
    }

    public boolean verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(int opcaoSelecionada1, int opcaoSelecionada2){
        return geographicalAreaList.checkIfGeoAreaIsInsertedInAnother(opcaoSelecionada1, opcaoSelecionada2);
    }
}
