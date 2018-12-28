package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.SensorType;
import pt.ipp.isep.dei.project.model.SensorTypeList;

public class US5Controller {
    private SensorTypeList mListaTipoSensores;


    public US5Controller(SensorTypeList listaTipoSensores) {
        this.mListaTipoSensores = listaTipoSensores;
    }

    public boolean criarEAdicionarTipoDeSensor (String tipoDeSensor){
        SensorType novoSensorType = this.mListaTipoSensores.novoTipoSensor(tipoDeSensor);
        return this.mListaTipoSensores.adicionarTipoSensorALista(novoSensorType);
    }
}
