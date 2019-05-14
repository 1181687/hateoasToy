package pt.ipp.isep.dei.project.model.house.housegrid;

import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomMapper;

import java.util.Objects;

public final class HouseGridMapper {

    protected HouseGridMapper() {
        //This is an utility class
    }

    public static HouseGridDTO newHouseGridDTO(){
        return new HouseGridDTO();
    }

    public static HouseGrid mapToEntity(HouseGridDTO gridDTO) {
        HouseGrid houseGrid = new HouseGrid(new HouseGridId(gridDTO.getName()));
        if (!gridDTO.getRoomDTOS().isEmpty()) {
            for (RoomDTO roomDTO : gridDTO.getRoomDTOS()) {
                Room room = RoomMapper.mapToEntity(roomDTO);
                houseGrid.addRoom(room);
            }
        }
        return houseGrid;
    }

    /**
     * Method that creates a HouseGridDTO based on a existing houseGrid.
     *
     * @param houseGrid Room to be used.
     * @return HouseGridDTO.
     */
    public static HouseGridDTO mapToDTO(HouseGrid houseGrid) {
        if (Objects.isNull(houseGrid)) {
            return null;
        }
        HouseGridDTO houseGridDTO = new HouseGridDTO();
        houseGridDTO.setName(houseGrid.getName());

        return houseGridDTO;

    }
}

