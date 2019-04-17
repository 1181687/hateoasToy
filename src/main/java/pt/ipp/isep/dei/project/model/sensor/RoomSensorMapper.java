package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.house.RoomId;

public final class RoomSensorMapper {

    protected RoomSensorMapper() {

    }

    public static RoomSensorDTO newRoomSensorDTO() {
        return new RoomSensorDTO();
    }


    public static RoomSensor mapToEntity(RoomSensorDTO sensorDTO) {
        RoomSensorId sensorId = new RoomSensorId(sensorDTO.getId());
        SensorTypeId sensorTypeId = new SensorTypeId(sensorDTO.getSensorTypeId());
        RoomId roomId = new RoomId(sensorDTO.getRoomId());

        return new RoomSensor(sensorId,
                sensorDTO.getName(),
                sensorDTO.getStartingDate(),
                sensorTypeId,
                sensorDTO.getUnits(),
                roomId
        );
    }


}