package pt.ipp.isep.dei.project.model.house.housegrid;

public final class HouseGridMapper {

    protected HouseGridMapper() {
        //This is an utility class
    }

    public static HouseGridDTO newHouseGridDTO(){
        return new HouseGridDTO();
    }

    public static HouseGridDTO mapToDTO(HouseGrid grid) {
        HouseGridDTO gridDTO = new HouseGridDTO();
        gridDTO.setId(grid.getHouseGridId().getId());
        return gridDTO;
    }

    /**
     * Method that turns a house grid dto into a house grid.
     *
     * @param gridDTO GridDTO to be used.
     * @return HouseGrid with the same info as the gridDTO.
     */
    public static HouseGrid mapToEntity(HouseGridDTO gridDTO) {
        HouseGridId houseGridId = new HouseGridId(gridDTO.getId());
        return new HouseGrid(houseGridId);
    }
}

