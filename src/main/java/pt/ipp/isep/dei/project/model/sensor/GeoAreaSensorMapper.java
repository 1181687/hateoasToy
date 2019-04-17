package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.LocationMapper;

public final class GeoAreaSensorMapper {

    protected GeoAreaSensorMapper() {
        // empty
    }

    /**
     * Method that creates a new GeoAreaSensorDTO.
     *
     * @return GeoAreaSensorDTO.
     */
    public static GeoAreaSensorDTO newSensorDTO() {
        return new GeoAreaSensorDTO();
    }

    /**
     * Method that creates a GeoAreaSensorDTO based on a existing GeoAreaSensor.
     *
     * @param sensor GeoAreaSensor to be used.
     * @return GeoAreaSensorDTO.
     */
    public static GeoAreaSensorDTO mapToDTO(GeoAreaSensor sensor) {
        GeoAreaSensorDTO sensorDTO = newSensorDTO();
        sensorDTO.setId(sensor.getId().getSensorId());
        sensorDTO.setName(sensor.getSensorName());
        sensorDTO.setStartingDate(sensor.getStartingDate().toLocalDate());
        sensorDTO.setSensorType(sensor.getSensorType().getSensorTypeId());
        sensorDTO.setLocation(LocationMapper.mapToDTO(sensor.getLocation()));
        sensorDTO.setUnits(sensor.getUnits());
        //sensorDTO.setActive(sensor.isActive());
        return sensorDTO;
    }

    /**
     * Method that turns a GeoAreaSensorDTO into a GeoAreaSensor.
     *
     * @param sensorDTO GeoAreaSensor to be used.
     * @return GeoAreaSensor with the required information.
     */
    public static GeoAreaSensor mapToEntity(GeoAreaSensorDTO sensorDTO) {

        GeoAreaSensorId geoAreaSensorId = new GeoAreaSensorId();
        Location geoLocation = LocationMapper.mapToEntity(sensorDTO.getLocation());
        SensorTypeId sensorTypeId = new SensorTypeId(sensorDTO.getSensorType());

        GeoAreaSensor newSensor = new GeoAreaSensor(geoAreaSensorId, sensorDTO.getName(), sensorDTO.getStartingDate().atStartOfDay(), sensorTypeId, geoLocation, sensorDTO.getUnits());
        return newSensor;
    }
}
