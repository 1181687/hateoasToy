package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class SensorTypeList {

    private List<SensorType> mListaTiposSensores = new ArrayList<>();

    public SensorTypeList() {
    }

    public SensorTypeList(List<SensorType> mListaTiposSensores) {
        this.mListaTiposSensores = mListaTiposSensores;
    }

    public List<SensorType> getmListaTiposSensores() {
        return mListaTiposSensores;
    }

    public SensorType novoTipoSensor(String novoTipo) {
        return new SensorType(novoTipo);
    }

    public boolean adicionarTipoSensorALista(SensorType novoSensorType) {
        if (!(this.mListaTiposSensores.contains(novoSensorType))) {
            this.mListaTiposSensores.add(novoSensorType);
            return true;
        }
        return false;
    }

    public SensorType getTipoSensorPorPosicao(int posicao) {
        return this.mListaTiposSensores.get(posicao);
    }

    /**
     * Method that shows the content of the Sensor type in the list.
     * @return
     */
    public String displaySensorTypeList() {
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= this.mListaTiposSensores.size(); i++) {
            content.append(i + " - Sensor Type: " + this.mListaTiposSensores.get(i - 1).getmType());
            content.append("\n");
        }
        return content.toString();
    }

    public boolean checkIfListOfTypeSensorsIsEmpty () {
        return this.mListaTiposSensores.isEmpty();
    }

}
