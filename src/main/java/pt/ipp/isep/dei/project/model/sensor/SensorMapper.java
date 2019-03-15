package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.LocationMapper;

public class SensorMapper {

    private SensorMapper() {
        // empty
    }

    /**
     * Method that creates a new SensorDTO.
     *
     * @return SensorDTO.
     */
    public static SensorDTO newSensorDTO() {
        return new SensorDTO();
    }

    /**
     * Method that creates a SensorDTO based on a existing Sensor.
     *
     * @param sensor Sensor to be used.
     * @return SensorDTO.
     */
    public static SensorDTO entityToMap(Sensor sensor) {
        SensorDTO sensorDTO = newSensorDTO();
        sensorDTO.setId(sensor.getId());
        sensorDTO.setName(sensor.getSensorName());
        sensorDTO.setStartingDate(sensor.getStartingDate().toLocalDate());
        sensorDTO.setSensorType(sensor.getSensorType().getType());
        sensorDTO.setLocation(LocationMapper.mapToDTO(sensor.getLocation()));
        sensorDTO.setUnits(sensor.getUnits());
        return sensorDTO;
    }

    /**
     * Method that turns a SensorDTO into a Sensor.
     *
     * @param sensorDTO Sensor to be used.
     * @return Sensor with the required information.
     */
    public static Sensor mapToEntity(SensorDTO sensorDTO) {
        SensorType sensorType = new SensorType(sensorDTO.getSensorType());
        Location location = LocationMapper.mapToEntity(sensorDTO.getLocation());
        return new Sensor(sensorDTO.getId(), sensorDTO.getName(), sensorDTO.getStartingDate().atStartOfDay(), sensorType, location, sensorDTO.getUnits());
    }
}
