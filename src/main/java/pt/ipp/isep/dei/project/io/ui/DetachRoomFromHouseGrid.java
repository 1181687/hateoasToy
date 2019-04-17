package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.DetachRoomFromHouseGridController;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.services.HouseService;

import java.util.List;

/**
 * US149 As an Administrator, I want to detach a room from a housegrid grid, so that the room’s
 * power and energy consumption is not included in that grid. The room’s
 * characteristics are not changed.
 */

public class DetachRoomFromHouseGrid {
    private DetachRoomFromHouseGridController controller;

    public DetachRoomFromHouseGrid(HouseService houseService) {
        this.controller = new DetachRoomFromHouseGridController(houseService);
    }

    public void run() {
        String exitMenu = "0 - Return to the previous menu";
        if (controller.getGridListSize() == 0) {
            System.out.println("There are no housegrid grids in your housegrid. Please insert a new housegrid grid.\n");
        } else {
            List<HouseGridDTO> houseGridDTOList = controller.getListOfHouseGrid();
            int gridIterator = 1;
            for (HouseGridDTO houseGridDTO : houseGridDTOList) {
                System.out.println(gridIterator + " - " + houseGridDTO.getId());
                gridIterator++;
            }
            System.out.println(exitMenu);
            String label1 = "Please choose the housegrid grid where the room will be detached.";
            int firstOption = InputValidator.getIntRange(label1, 0, controller.getGridListSize()) - 1;
            if (firstOption == -1) {
                return;
            }
            HouseGridDTO chosenHouseGrid = houseGridDTOList.get(firstOption);
            List<RoomDTO> houseGridRooms = controller.getRoomsOfHouseGrid(chosenHouseGrid.getId());
            if (houseGridRooms.isEmpty()) {
                System.out.println("There are no rooms to detach." + "\n");
            } else {
                System.out.println(houseGridRooms);
                String label2 = "Please choose the room to be detached from the chosen house grid.";
                int secondOption = InputValidator.getIntRange(label2, 0, houseGridRooms.size()) - 1;
                if (secondOption == -1) {
                    return;
                }
                int roomIterator = 1;
                for (RoomDTO roomDTO : houseGridRooms) {
                    System.out.println(roomIterator + " - " + roomDTO.getId());
                    roomIterator++;
                }
                if (controller.detachRoomFromHouseGrid(houseGridRooms.get(secondOption))) {
                    System.out.println("The room has been detached from the grid.");
                } else {
                    System.out.println("Please select a valid room to detach.");
                }
            }
        }
    }


}
