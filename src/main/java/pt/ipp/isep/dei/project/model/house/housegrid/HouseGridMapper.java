package pt.ipp.isep.dei.project.model.house.housegrid;

public final class HouseGridMapper {

    public static HouseGridDTO newHouseGridDTO(){
        return new HouseGridDTO();
    }

    public static HouseGrid mapToEntity(HouseGridDTO gridDTO){
        return new HouseGrid(gridDTO.getName());
    }



}
