package sprint0.Controllers;

import sprint0.Model.*;

import java.util.Date;

public class US6Controller {

        private Sensor mNovoSensor;
        private ListaTiposSensores mListaTipoSensores;
        private ListaAG mListaAreaGeografica;

        public US6Controller(Sensor novoSensor) {
            this.mNovoSensor = novoSensor;
        }

        public boolean novoSensor (String nomeSensor) {
            this.mNovoSensor = new Sensor();
            mNovoSensor.setmNomeSensor(nomeSensor);
            if (mNovoSensor.getmNomeSensor().equals(nomeSensor)) {
                return true;
            }
            return false;
        }

        public boolean atribuirData (Date novaData) {
            mNovoSensor.setmDataFuncionamento(novaData);
            if (mNovoSensor.getmDataFuncionamento().equals(novaData)) {
                return true;
            }
            return false;
        }

        public boolean atribuirLocalizacao (Localizacao novaLocalizacao) {
            mNovoSensor.setmLocalizacao(novaLocalizacao);
            if (mNovoSensor.getmLocalizacao().equals(novaLocalizacao)) {
                return true;
            }
            return false;
        }

        public boolean atribuirTipoSensor (String novoTipoSensor) {

            if (this.mListaTipoSensores.getmListaTiposSensores().contains(novoTipoSensor)) {
                mNovoSensor.getmTipoSensor().setmTipo(novoTipoSensor);
                //mNovoSensor.setmTipoSensor(TipoSensor.);
                return true;
            }
            return false;
        }

        public boolean adicionarSensorAAreaGeografica (String areaGeografica) {

            if (this.mListaAreaGeografica.getmListaAG().contains(mListaAreaGeografica.getAreaGeografica(areaGeografica))) {
                mListaAreaGeografica.getAreaGeografica(areaGeografica).adicionarSensorAListaDeSensores(this.mNovoSensor);
                return true;
            }
            return false;
        }
}
