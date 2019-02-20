package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class SensorTypeList {

    private List<SensorType> listOfSensorTypes = new ArrayList<>();

    public SensorTypeList() {
    }

    /**
     * Constructor Method
     *
     * @param listOfSensorTypes List of Sensor Types
     */
    public SensorTypeList(List<SensorType> listOfSensorTypes) {
        this.listOfSensorTypes = listOfSensorTypes;
    }

    /**
     * Get method
     *
     * @return Sensor Type List
     */
    public List<SensorType> getListOfSensorTypes() {
        return listOfSensorTypes;
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
        if (!(this.listOfSensorTypes.contains(novoSensorType))) {
            this.listOfSensorTypes.add(novoSensorType);
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
        return this.listOfSensorTypes.get(posicao);
    }

    /**
     * Method that shows the content of the Sensor type in the list.
     * @return
     */
    public String getSensorTypeListToString() {
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= this.listOfSensorTypes.size(); i++) {
            content.append(i + " - Sensor Type: " + this.listOfSensorTypes.get(i - 1).getType());
            content.append("\n");
        }
        return content.toString();
    }

    /**
     * Boolean method that checks if the Sensor Type List is empty
     * @return empty Sensor Type List
     */
    public boolean isEmpty() {
        return this.listOfSensorTypes.isEmpty();
    }

}
