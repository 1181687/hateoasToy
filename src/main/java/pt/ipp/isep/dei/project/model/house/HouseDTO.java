package pt.ipp.isep.dei.project.model.house;

import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;

import java.util.List;

public class HouseDTO {
    AddressDTO addressDTO;
    List<RoomDTO> roomDTOList;
    List<HouseGridDTO> houseGridDTOList;


    //intentionally empty
    public HouseDTO() {

    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    public List<RoomDTO> getRoomDTOList() {
        return roomDTOList;
    }

    public void setRoomDTOList(List<RoomDTO> roomDTOList) {
        this.roomDTOList = roomDTOList;
    }

    public List<HouseGridDTO> getHouseGridDTOList() {
        return houseGridDTOList;
    }

    public void setHouseGridDTOList(List<HouseGridDTO> houseGridDTOList) {
        this.houseGridDTOList = houseGridDTOList;
    }
}
