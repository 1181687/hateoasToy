package pt.ipp.isep.dei.project.model.sensor;

public final class RoomSensorMapper {

    protected RoomSensorMapper() {

    }

    public static RoomSensorDTO newRoomSensorDTO() {
        return new RoomSensorDTO();
    }
}

    /*public static RoomSensor mapToEntity(RoomSensorDTO sensorDTO) {
        SensorType type = new SensorType(sensorDTO.getSensorType());
        SensorTypeId typeId = new SensorTypeId(type.getSensorType());
        return new RoomSensor(sensorDTO.getId(), sensorDTO.getName(), sensorDTO.getStartingDate(), typeId, sensorDTO.getUnits());
    }*/


