package sprint0.Controllers;

import sprint0.Model.ListaTiposSensores;
import sprint0.Model.TipoSensor;

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
