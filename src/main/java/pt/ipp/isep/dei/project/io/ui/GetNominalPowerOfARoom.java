package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetNominalPowerController;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.services.RoomAggregateService;

import java.util.List;

public class GetNominalPowerOfARoom {
    private GetNominalPowerController controller;
    private List<RoomDTO> roomDTOS;


    public GetNominalPowerOfARoom(HouseService houseService) {
        this.controller = new GetNominalPowerController(houseService);
    }

    public void run() {
        String exit = "0- Exit";
        this.roomDTOS = controller.getAllRooms();
        if (this.roomDTOS.isEmpty()) {
            System.out.println("There are no rooms in the house. Please, add one first");
        } else {

            int roomListLength = this.roomDTOS.size();
            String label1 = "Please select a room to see its total nominal power: \n" + getRoomsToString() + exit;
            int position = InputValidator.getIntRange(label1, 0, roomListLength)-1;
            if (position == -1) {
                return;
            }
            RoomDTO roomDTO =this.roomDTOS.get(position);
            if (controller.roomDeviceListIsEmpty(roomDTO.getId())) {
                System.out.println("There are no devices in the room. Please, add one first");
            } else {
                double nominalPower = controller.getRoomNominalPower(roomDTO.getId());
                System.out.println("The total nominal power of the selected room is " + nominalPower + " kW");
            }
        }
    }

    private String getRoomsToString(){
        StringBuilder content = new StringBuilder();
        int iterator = 1;

        for (RoomDTO roomDTO : this.roomDTOS) {
            content.append(iterator);
            content.append(" - ");
            content.append(roomDTO.getId());
            content.append("\n");
        }
        return content.toString();
    }

}
