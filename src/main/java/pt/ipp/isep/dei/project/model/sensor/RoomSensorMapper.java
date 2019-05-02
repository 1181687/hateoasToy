package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.house.RoomId;

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
        return new RoomSensor(sensorId, sensorDTO.getName(), sensorDTO.getStartingDate(), typeId, sensorDTO.getUnits(), roomId);
    }
}
