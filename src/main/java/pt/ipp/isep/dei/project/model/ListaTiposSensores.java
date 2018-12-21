package pt.ipp.isep.dei.project.model;

import java.util.*;

public class ListaTiposSensores {

    private List<TipoSensor> mListaTiposSensores = new ArrayList<>();

    public ListaTiposSensores() {
    }

    public ListaTiposSensores(List<TipoSensor> mListaTiposSensores) {
        this.mListaTiposSensores = mListaTiposSensores;
    }

    public List<TipoSensor> getmListaTiposSensores() {
        return mListaTiposSensores;
    }

    public TipoSensor novoTipoSensor(String novoTipo) {
        return new TipoSensor(novoTipo);
    }

    public boolean adicionarTipoSensorALista(TipoSensor novoTipoSensor) {
        if (!(this.mListaTiposSensores.contains(novoTipoSensor))) {
            this.mListaTiposSensores.add(novoTipoSensor);
            return true;
        }
        return false;
    }

    public TipoSensor getTipoSensorPorPosicao(int posicao) {
        return this.mListaTiposSensores.get(posicao);
    }

    /**
     * Method that shows the content of the Sensor type in the list.
     * @return
     */
    public String displaySensorTypeList() {
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= this.mListaTiposSensores.size(); i++) {
            content.append(i + " - Sensor Type: " + this.mListaTiposSensores.get(i - 1).getmTipo());
            content.append("\n");
        }
        return content.toString();
    }

    public boolean checkIfListOfTypeSensorsIsEmpty () {
        return this.mListaTiposSensores.isEmpty();
    }

}
