package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaIdMapper;

import java.util.ArrayList;
import java.util.List;

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
        sensorDTO.setActive(sensor.isActive());
        return sensorDTO;
    }

    /**
     * Method that turns a GeoAreaSensorDTO into a GeoAreaSensor.
     *
     * @param sensorDTO GeoAreaSensor to be used.
     * @return GeoAreaSensor with the required information.
     */
    public static GeoAreaSensor mapToEntity(GeoAreaSensorDTO sensorDTO) {
        Location sensorLocation = LocationMapper.mapToEntity(sensorDTO.getLocation());
        GeoAreaId geoAreaId = GeoAreaIdMapper.mapToEntity(sensorDTO.getGeographicalAreaId());
        SensorId geoAreaSensorId = new SensorId(sensorDTO.getId());
        SensorTypeId sensorTypeId = new SensorTypeId(sensorDTO.getSensorType());
        List<Reading> readings = new ArrayList<>();
        for (ReadingDTO readingDTO : sensorDTO.getReadingDTOs()) {
            Reading reading = ReadingMapper.mapToEntity(readingDTO);
            readings.add(reading);
        }
        GeoAreaSensor sensor = new GeoAreaSensor(geoAreaSensorId, sensorDTO.getName(), sensorDTO.getStartingDate().atStartOfDay(), sensorTypeId, sensorLocation, sensorDTO.getUnits(), geoAreaId);
        sensor.getListOfReadings().addAll(readings);
        return sensor;
    }
}
