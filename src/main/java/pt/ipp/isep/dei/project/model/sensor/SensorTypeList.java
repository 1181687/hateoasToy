package pt.ipp.isep.dei.project.model.sensor;


import java.util.ArrayList;
import java.util.List;

public class SensorTypeList {

    private List<SensorType> listOfSensorTypes = new ArrayList<>();

    public SensorTypeList() {
    }

    /**
     * Constructor Method
     *
     * @param listOfSensorTypes List of sensor Types
     */
    public SensorTypeList(List<SensorType> listOfSensorTypes) {
        this.listOfSensorTypes = listOfSensorTypes;
    }

    /**
     * Get method
     *
     * @return sensor Type List
     */
    public List<SensorType> getListOfSensorTypes() {
        return listOfSensorTypes;
    }

    /**
     * Method that creates a new sensor Type
     *
     * @param novoTipo new Type of sensor (string)
     * @return new sensor Type
     */
    public SensorType newSensorType(String novoTipo) {
        return new SensorType(novoTipo);
    }

    /**
     * Boolean method that adds a sensor Type to the sensor Type List if the list doesn't already contain that sensor type
     *
     * @param novoSensorType new type of sensor
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
     * Method that retrieves the sensor type in a specific position of the sensor Type List
     *
     * @param posicao position the sensor Type in the sensor Type List
     * @return the sensor Type of that position
     */
    public SensorType getSensorTypeByPosition(int posicao) {
        return this.listOfSensorTypes.get(posicao);
    }

    /**
     * Method that shows the content of the sensor type in the list.
     * @return
     */
    public String getSensorTypeListToString() {
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= this.listOfSensorTypes.size(); i++) {
            content.append(i + " - sensor Type: " + this.listOfSensorTypes.get(i - 1).getSensorType());
            content.append("\n");
        }
        return content.toString();
    }

    /**
     * Boolean method that checks if the sensor Type List is empty
     * @return empty sensor Type List
     */
    public boolean isEmpty() {
        return this.listOfSensorTypes.isEmpty();
    }

}
