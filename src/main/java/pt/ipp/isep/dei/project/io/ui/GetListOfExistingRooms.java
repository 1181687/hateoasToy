package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetListOfExistingRoomsController;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.services.RoomAggregateService;

import java.util.List;

public class GetListOfExistingRooms {

    private GetListOfExistingRoomsController controller;

    public GetListOfExistingRooms(RoomAggregateService roomAggregateService) {
        this.controller = new GetListOfExistingRoomsController(roomAggregateService);
    }

    public void run() {
        List<RoomDTO> getRoomListDTO = controller.getRoomDTOList();

        if (getRoomListDTO.isEmpty()) {
            System.out.println("\nThere are no rooms in the house. Please create a new room.");
            return;
        }
        System.out.println("\nThe table below shows the current existent rooms in the house:");
        int listOrderByNumber = 1;
        for (RoomDTO roomDTO : getRoomListDTO) {
            System.out.println(listOrderByNumber + " - Id: " + roomDTO.getId() + ", Description: " + roomDTO.getDescription());
            listOrderByNumber++;
        }
    }
}
