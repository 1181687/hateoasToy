package pt.ipp.isep.dei.project.model.house;

import java.util.Objects;

public final class RoomMapper {

    /**
     * Constructor.
     */
    protected RoomMapper() {
        // empty
    }

    /**
     * Method that creates a new RoomDTO.
     *
     * @return RoomDTO.
     */
    public static RoomDTO newRoomDTO() {
        return new RoomDTO();
    }


    /**
     * Method that creates a RoomDTO based on a existing Room.
     *
     * @param room Room to be used.
     * @return RoomDTO.
     */
    public static RoomDTO mapToDTO(Room room) {
        if (Objects.isNull(room)) {
            return null;
        }
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getRoomId().getId());
        roomDTO.setDescription(room.getDescription());
        roomDTO.setHouseFloor(room.getHouseFloor());
        roomDTO.setWidth(room.getDimension().getWidth());
        roomDTO.setLength(room.getDimension().getLength());
        roomDTO.setHeight(room.getDimension().getHeight());
        roomDTO.setGridId(room.getHouseGridId().getId());
        return roomDTO;
    }

    /**
     * Method that turns a RoomDTO into a Room.
     *
     * @param roomDTO to be used.
     * @return room with the required information.
     */
    public static Room mapToEntity(RoomDTO roomDTO) {
        if (Objects.isNull(roomDTO)) {
            return null;
        }

        Dimension dimension = new Dimension(roomDTO.getWidth(), roomDTO.getLength(), roomDTO.getHeight());

        return new Room(new RoomId(roomDTO.getId()), roomDTO.getDescription(), roomDTO.getHouseFloor(), dimension);

    }

}
