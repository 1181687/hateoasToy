package pt.ipp.isep.dei.project.model.house.housegrid;


import pt.ipp.isep.dei.project.model.house.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class HouseGridDTO {

    private String name;
    private List<RoomDTO> roomDTOS = new ArrayList<>();

    public HouseGridDTO() {
        //Intentionally empty
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }

    public List<RoomDTO> getRoomDTOS() {
        return roomDTOS;
    }

    public void setRoomDTOS(List<RoomDTO> roomDTOS) {
        this.roomDTOS = roomDTOS;
    }

    public void addRoomDTO(RoomDTO room) {
        roomDTOS.add(room);

    }
}
