package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.ListaTiposSensores;
import pt.ipp.isep.dei.project.model.TipoSensor;

public class US5Controller {
    private ListaTiposSensores mListaTipoSensores;

    public US5Controller(ListaTiposSensores listaTipoSensores) {
        this.mListaTipoSensores = listaTipoSensores;
    }

    public boolean criarEAdicionarTipoDeSensor (String tipoDeSensor){
        TipoSensor novoTipoSensor = this.mListaTipoSensores.novoTipoSensor(tipoDeSensor);
        return this.mListaTipoSensores.adicionarTipoSensorALista(novoTipoSensor);
    }
}
