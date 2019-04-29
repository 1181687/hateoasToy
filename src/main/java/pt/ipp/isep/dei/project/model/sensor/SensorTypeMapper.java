package pt.ipp.isep.dei.project.model.sensor;

public final class SensorTypeMapper {

    protected SensorTypeMapper() {
        //empty
    }

    public static SensorTypeDTO mapToDto(SensorType sensorType) {
        return new SensorTypeDTO(sensorType.getSensorType().getSensorTypeId());
    }

    public static SensorType mapToEntity(SensorTypeDTO sensorTypeDTO) {
        SensorTypeId sensorTypeId = new SensorTypeId(sensorTypeDTO.getSensorType());
        return new SensorType(sensorTypeId);
    }
}
