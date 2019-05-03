package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.house.RoomId;

import java.util.ArrayList;
import java.util.List;

public final class RoomSensorMapper {

    protected RoomSensorMapper() {
        // empty
    }

    public static RoomSensorDTO newRoomSensorDTO() {
        return new RoomSensorDTO();
    }

    public static RoomSensor mapToEntity(RoomSensorDTO sensorDTO) {
        SensorId sensorId = new SensorId(sensorDTO.getId());
        SensorTypeId typeId = new SensorTypeId(sensorDTO.getSensorType());
        RoomId roomId = new RoomId(sensorDTO.getRoomId());
        List<Reading> readings = new ArrayList<>();
        for (ReadingDTO readingDTO : sensorDTO.getReadingDTOs()) {
            Reading reading = ReadingMapper.mapToEntity(readingDTO);
            readings.add(reading);
        }
        RoomSensor sensor = new RoomSensor(sensorId, sensorDTO.getName(), sensorDTO.getStartingDate(), typeId, sensorDTO.getUnits(), roomId);
        sensor.getReadings().addAll(readings);
        return sensor;
    }

    /**
     * Method that creates a GeoAreaSensorDTO based on a existing GeoAreaSensor.
     *
     * @param sensor GeoAreaSensor to be used.
     * @return GeoAreaSensorDTO.
     */
    public static RoomSensorDTO mapToDTO(RoomSensor sensor) {
        RoomSensorDTO sensorDTO = newRoomSensorDTO();
        sensorDTO.setId(sensor.getId().getSensorId());
        sensorDTO.setName(sensor.getSensorName());
        sensorDTO.setStartingDate(sensor.getStartingDate().toLocalDate());
        sensorDTO.setSensorType(sensor.getSensorType().getSensorTypeId());
        sensorDTO.setRoomId(sensor.getRoomId().getId());
        sensorDTO.setUnits(sensor.getUnits());
        sensorDTO.setActive(sensor.isActive());
        List<ReadingDTO> readingDTOs = new ArrayList<>();
        for (Reading reading : sensor.getReadings()) {
            ReadingDTO readingDTO = ReadingMapper.mapToDTO(reading);
            readingDTO.setId(sensor.getId().getSensorId());
            readingDTOs.add(readingDTO);
        }
        sensorDTO.setReadingDTOs(readingDTOs);
        return sensorDTO;
    }
}
