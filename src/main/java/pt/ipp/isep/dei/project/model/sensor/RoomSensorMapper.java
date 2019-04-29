package pt.ipp.isep.dei.project.model.sensor;

public final class RoomSensorMapper {

    protected RoomSensorMapper() {

    }

    public static RoomSensorDTO newRoomSensorDTO() {
        return new RoomSensorDTO();
    }

    public static RoomSensor mapToEntity(RoomSensorDTO sensorDTO) {
        SensorId sensorId = new SensorId();
        SensorTypeId typeId = new SensorTypeId();
        return new RoomSensor(sensorId, sensorDTO.getName(), sensorDTO.getStartingDate(), typeId, sensorDTO.getUnits());
    }
}
