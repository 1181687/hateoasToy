package pt.ipp.isep.dei.project.model.sensor;

import java.util.Objects;

public final class SensorIdMapper {

    public SensorIdMapper() {
    }

    public static SensorIdDTO newDTO() {
        return new SensorIdDTO();
    }

    public static SensorIdDTO mapToDTO(SensorId id) {
        SensorIdDTO dto = newDTO();
        dto.setId(id.getSensorId());
        return dto;
    }

    public static SensorId mapToEntity(SensorIdDTO sensorIdDTO) {
        if (Objects.isNull(sensorIdDTO)) {
            return null;
        }
        return new SensorId(sensorIdDTO.getId());
    }
}
