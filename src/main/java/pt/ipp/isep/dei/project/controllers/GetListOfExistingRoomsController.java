package pt.ipp.isep.dei.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomMapper;
import pt.ipp.isep.dei.project.services.RoomService;

import java.util.ArrayList;
import java.util.List;

public class GetListOfExistingRoomsController {

    @Autowired
    private RoomService roomService;

    public GetListOfExistingRoomsController(RoomService roomService) {
        this.roomService = roomService;
    }

    public List<RoomDTO> getRoomDTOList() {
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : roomService.getAllRooms()) {
            roomDTOList.add(RoomMapper.mapToDTO(room));
        }
        return roomDTOList;
    }
}
