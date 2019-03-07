package pt.ipp.isep.dei.project.model;

public class SensorMapper {

    /**
     * Method that creates a new SensorDTO.
     *
     * @return SensorDTO.
     */
    public static SensorDTO newSensorDTO() {
        return new SensorDTO();
    }

    /**
     * Method that turns a SensorDTO into a Sensor.
     *
     * @param sensorDTO Sensor to be used.
     * @return Sensor with the required information.
     */
    public static Sensor mapToEntity(SensorDTO sensorDTO) {
        return new Sensor(sensorDTO.getName(), sensorDTO.getStartingDate(), sensorDTO.getSensorType(), sensorDTO.getLocation());
    }
}
