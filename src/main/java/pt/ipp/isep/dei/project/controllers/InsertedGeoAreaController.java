package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaService;

public class InsertedGeoAreaController {
    private GeographicalAreaService geographicalAreaService;

    public InsertedGeoAreaController(GeographicalAreaService geographicalAreaService) {
        this.geographicalAreaService = geographicalAreaService;
    }

    public String getConteudoLista (boolean usarCriterio){
        return geographicalAreaService.getGeoAreaListToString(usarCriterio);
    }

    public GeographicalArea getAGNaListaApresentada(int opcaoSelecionada) {
        return geographicalAreaService.getGeographicalAreaInTheList(opcaoSelecionada);
    }

    public boolean verSeAGTemAreaInseridaVazia(GeographicalArea area) {
        return geographicalAreaService.checkIfGeoAreaDoesntHaveAnInsertedArea(area);
    }

    public boolean verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(int opcaoSelecionada1, int opcaoSelecionada2){
        return geographicalAreaService.checkIfGeoAreaIsInsertedInAnother(opcaoSelecionada1, opcaoSelecionada2);
    }
}
