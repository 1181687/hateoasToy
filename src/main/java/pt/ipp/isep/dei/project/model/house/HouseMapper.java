package pt.ipp.isep.dei.project.model.house;

public final class HouseMapper {

    protected HouseMapper() {

    }

    public static HouseDTO newHouseDTO() {
        return new HouseDTO();
    }


/*    public static House mapToEntity(HouseDTO houseDTO, House house) {
        Address houseAddress = AddressMapper.mapToEntity(houseDTO.addressDTO);
        house.setAddress(houseAddress);

        for (RoomDTO roomDTO : houseDTO.getRoomDTOList()) {
            Room room = RoomMapper.mapToEntity(roomDTO);
            house.addRoom(room);
        }
        for (HouseGridDTO houseGridDTO : houseDTO.getHouseGridDTOList()) {
            HouseGrid houseGrid = HouseGridMapper.mapToEntity(houseGridDTO);
            house.addGrid(houseGrid);
        }
        return house;
    }*/
}
