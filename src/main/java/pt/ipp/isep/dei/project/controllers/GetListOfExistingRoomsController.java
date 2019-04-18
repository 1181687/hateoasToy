package pt.ipp.isep.dei.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomMapper;
import pt.ipp.isep.dei.project.services.RoomAggregateService;

import java.util.ArrayList;
import java.util.List;

public class GetListOfExistingRoomsController {

    @Autowired
    private RoomAggregateService roomAggregateService;

    public GetListOfExistingRoomsController(RoomAggregateService roomAggregateService) {
        this.roomAggregateService = roomAggregateService;
    }

    public List<RoomDTO> getRoomDTOList() {
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : roomAggregateService.getAllRooms()) {
            roomDTOList.add(RoomMapper.mapToDTO(room));
        }
        return roomDTOList;
    }

}
