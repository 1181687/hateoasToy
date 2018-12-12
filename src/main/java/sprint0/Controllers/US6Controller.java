package sprint0.Controllers;

import sprint0.Model.Localizacao;
import sprint0.Model.Sensor;
import sprint0.Model.TipoSensor;

import java.util.Date;

public class US6Controller {

        private Sensor mNovoSensor;

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

        public boolean novaData (Date novaData) {
            mNovoSensor.setmDataFuncionamento(novaData);
            if (mNovoSensor.getmDataFuncionamento().equals(novaData)) {
                return true;
            }
            return false;
        }

        public boolean novalocalizacao (Localizacao novaLocalizacao) {
            mNovoSensor.setmLocalizacao(novaLocalizacao);
            if (mNovoSensor.getmLocalizacao().equals(novaLocalizacao)) {
                return true;
            }
            return false;
        }

        public boolean novoTipoSensor (TipoSensor novoTipoSensor) {
            mNovoSensor.setmTipoSensor(novoTipoSensor);
            if (mNovoSensor.getmTipoSensor().equals(novoTipoSensor)) {
                return true;
            }
            return false;
        }
}
