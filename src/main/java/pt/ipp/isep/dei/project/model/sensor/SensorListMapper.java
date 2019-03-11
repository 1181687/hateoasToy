package pt.ipp.isep.dei.project.model.sensor;

public class SensorListMapper {

    /**
     * Method that creates a new SensorDTOList.
     *
     * @return SensorDTOList.
     */
    public static SensorDTOList newSensorDTOList() {
        return new SensorDTOList();
    }

    /**
     * Method that creates a SensorDTOList based on a existing SensorList.
     *
     * @param sensorList SensorList to be used.
     * @return SensorDTOList.
     */
    public static SensorDTOList entityToMap(SensorList sensorList) {
        SensorDTOList sensorDTOList = newSensorDTOList();
        for (Sensor sensor : sensorList.getListOfSensors()) {
            sensorDTOList.addSensorDTO(SensorMapper.entityToMap(sensor));
        }
        return sensorDTOList;
    }

    /**
     * Method that turns a SensorDTOList into a SensorList.
     *
     * @param sensorDTOList SensorDTOList to be used.
     * @return SensorList with the required information.
     */
    public static SensorList mapToEntity(SensorDTOList sensorDTOList) {
        return new SensorList();
    }
}
