package pt.ipp.isep.dei.project.model.house;

public final class HouseMapper {

    protected HouseMapper() {

    }

    public static HouseDTO newHouseDTO() {
        return new HouseDTO();
    }


/*    public static House updateHouseWithRoomsAndGrids(HouseDTO houseDTO, House house) {
        Address houseAddress = AddressMapper.updateHouseWithRoomsAndGrids(houseDTO.addressDTO);
        house.setAddress(houseAddress);

        for (RoomDTO roomDTO : houseDTO.getRoomDTOList()) {
            Room room = RoomMapper.updateHouseWithRoomsAndGrids(roomDTO);
            house.addRoom(room);
        }
        for (HouseGridDTO houseGridDTO : houseDTO.getHouseGridDTOList()) {
            HouseGrid houseGrid = HouseGridMapper.updateHouseWithRoomsAndGrids(houseGridDTO);
            house.addGrid(houseGrid);
        }
        return house;
    }*/
}
