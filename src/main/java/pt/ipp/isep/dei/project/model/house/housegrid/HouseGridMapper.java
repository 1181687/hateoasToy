package pt.ipp.isep.dei.project.model.house.housegrid;

import pt.ipp.isep.dei.project.model.house.RoomMapper;

public final class HouseGridMapper {

    protected HouseGridMapper() {
        //This is an utility class
    }

    public static HouseGridDTO newHouseGridDTO(){
        return new HouseGridDTO();
    }


    public static HouseGrid mapToEntity(HouseGridDTO gridDTO){
        HouseGrid houseGrid = new HouseGrid(gridDTO.getName());
        if (!gridDTO.getRoomDTOS().isEmpty()) {

        }
        return houseGrid;
    }

}

