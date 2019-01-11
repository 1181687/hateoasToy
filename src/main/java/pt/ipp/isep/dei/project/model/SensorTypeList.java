package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class SensorTypeList {

    private List<SensorType> mListaTiposSensores = new ArrayList<>();

    public SensorTypeList() {
    }

    /**
     * Constructor Method
     *
     * @param mListaTiposSensores List of Sensor Types
     */
    public SensorTypeList(List<SensorType> mListaTiposSensores) {
        this.mListaTiposSensores = mListaTiposSensores;
    }

    /**
     * Get method
     *
     * @return Sensor Type List
     */
    public List<SensorType> getSensorTypeList() {
        return mListaTiposSensores;
    }

    /**
     * Method that creates a new Sensor Type
     *
     * @param novoTipo new Type of Sensor (string)
     * @return new Sensor Type
     */
    public SensorType newSensorType(String novoTipo) {
        return new SensorType(novoTipo);
    }

    /**
     * Boolean method that adds a Sensor Type to the Sensor Type List if the list doesn't already contain that sensor type
     *
     * @param novoSensorType new type of Sensor
     * @return true or false
     */
    public boolean addSensorType(SensorType novoSensorType) {
        if (!(this.mListaTiposSensores.contains(novoSensorType))) {
            this.mListaTiposSensores.add(novoSensorType);
            return true;
        }
        return false;
    }

    /**
     * Method that retrieves the sensor type in a specific position of the Sensor Type List
     *
     * @param posicao position the Sensor Type in the Sensor Type List
     * @return the Sensor Type of that position
     */
    public SensorType getSensorTypeByPosition(int posicao) {
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

    /**
     * Boolean method that checks if the Sensor Type List is empty
     * @return empty Sensor Type List
     */
    public boolean checkIfListOfTypeSensorsIsEmpty () {
        return this.mListaTiposSensores.isEmpty();
    }

}
