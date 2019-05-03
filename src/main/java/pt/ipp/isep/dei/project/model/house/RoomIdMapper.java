package pt.ipp.isep.dei.project.model.house;

import java.util.Objects;

public final class RoomIdMapper {

    public RoomIdMapper() {
    }

    public static RoomIdDTO newDTO() {
        return new RoomIdDTO();
    }

    public static RoomIdDTO mapToDTO(RoomId id) {
        RoomIdDTO dto = newDTO();
        dto.setId(id.getId());
        return dto;
    }

    public static RoomId mapToEntity(RoomIdDTO roomIdDTO) {
        if (Objects.isNull(roomIdDTO)) {
            return null;
        }
        return new RoomId(roomIdDTO.getId());
    }
}
