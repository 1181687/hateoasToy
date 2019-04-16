package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.DetachRoomFromHouseGridController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.HouseService;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomList;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;

import java.util.ArrayList;
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

    public void showFinalList(int firstOption) {
        String label = "Do you want to see the list of room(s) on the selected Grid? (Y/N)";
        String answer = InputValidator.confirmValidation(label);
        if ("y".equals(answer) || "Y".equals(answer)) {
            if (controller.getListOfRoomsInACertainHouseGrid(firstOption).isEmpty()) {
                System.out.println("There's no rooms in this list now.");
            } else {
                System.out.println(controller.getListOfRoomsInACertainHouseGrid(firstOption));
            }
        } else {
            return;
        }
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
                    System.out.println(roomIterator + " - " + roomDTO.getRoomId());
                    roomIterator++;
                }
                if (controller.detachRoomFromGridList((controller.getHouseGridFromTheList(firstOption)), controller.getRoomFromTheListOfRoomByAPosition(secondOption))) {
                    System.out.println("The room has been detached from the grid.");
                    showFinalList(firstOption);
                } else {
                    System.out.println("Please select a valid room to detach.");
                }
            }
        }
    }


}
