package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AttachRoomToHouseGridController;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.services.HouseService;

import java.util.List;

public class AttachRoomToHouseGrid {
    private static final String EXIT_OPTION = "\r0 - Exit";
    private AttachRoomToHouseGridController controller;
    private List<HouseGridDTO> gridDTOS;
    private List<RoomDTO> roomDTOS;

    /**
     * Constructor.
     *
     * @param houseService HouseService to be used.
     */
    public AttachRoomToHouseGrid(HouseService houseService) {
        this.controller = new AttachRoomToHouseGridController(houseService);
        this.gridDTOS = controller.getGrids();
        this.roomDTOS = controller.getRooms();
    }

    /**
     * RUN!
     */
    public void run() {
        if (gridDTOS.isEmpty()) {
            System.out.println("There are no house grids available. Please create one first.\n");
            return;
        }
        if (roomDTOS.isEmpty()) {
            System.out.println("There are no rooms available. Please create one first.\n");
        } else {
            chooseHouseGrid();
            chooseRoomAndAttach();
            attachRoom();
        }
    }

    /**
     * Method that lists all the grids, in order to show to the user.
     *
     * @return String with the info.
     */
    private String getGridsToList() {
        StringBuilder list = new StringBuilder();
        int number = 1;
        for (HouseGridDTO gridDTO : gridDTOS) {
            list.append(number + " " + gridDTO.getId() + "\n");
            number++;
        }
        list.append(EXIT_OPTION);
        return list.toString();
    }

    /**
     * Method that allows the user to choose a house grid and set it as the chosen one.
     */
    private void chooseHouseGrid() {
        String label = "Please choose the house grid where the room will be attached: \n" + getGridsToList();
        int uiId = InputValidator.getIntRange(label, 0, gridDTOS.size()) - 1;
        if (uiId == -1) {
            return;
        }
        String gridId = gridDTOS.get(uiId).getId();
        //controller.setGrid(gridId);
    }

    /**
     * Method that lists all the rooms, in order to show to the user.
     *
     * @return String with the info.
     */
    private String getRoomsToList() {
        StringBuilder list = new StringBuilder();
        int number = 1;
        for (RoomDTO roomDTO : roomDTOS) {
            list.append(number + " " + roomDTO.getId() + "\n");
            number++;
        }
        list.append(EXIT_OPTION);
        return list.toString();
    }

    /**
     * Method that allows the user to choose a room to be edited.
     */
    private void chooseRoomAndAttach() {
        String label = "Please choose a room to be attached to the chosen house grid: \n" + getRoomsToList();
        int uiId = InputValidator.getIntRange(label, 0, roomDTOS.size()) - 1;
        if (uiId == -1) {
            return;
        }
        String roomId = roomDTOS.get(uiId).getId();
        controller.setRoom(roomId);
        checkIfRoomIsAlreadyInAGrid();
    }

    /**
     * Method that checks if a room is already in a grid. If so, it can be in the chosen grid or not.
     */
    private void checkIfRoomIsAlreadyInAGrid() {
        while (controller.isRoomInAGrid()) {
            if (controller.isRoomAlreadyInChosenGrid()) {
                System.out.println("The specified room is already in the house grid.");
                chooseRoomAndAttach();
            } else {
                optionsWhenRoomConnectedToOtherGrid();
            }
        }
    }

    /**
     * Method that presents the option of disconnecting the room from the grid it's in and connecting it to the chosen
     * grid.
     */
    private void optionsWhenRoomConnectedToOtherGrid() {
        String label = "The specified room is already in another grid. Do you want to disconnect it and connect it the chosen grid? (y/n)";
        String answer = InputValidator.confirmValidation(label);
        if ("y".equalsIgnoreCase(answer)) {
            attachRoom();
        } else {
            System.out.println("No changes were made.\n");
        }
    }

    /**
     * Method that attaches the room into the grid.
     */
    private void attachRoom() {
        String label = "Confirm? (y/n)";
        String answer = InputValidator.confirmValidation(label);
        if ("y".equalsIgnoreCase(answer)) {
            controller.attachRoomInTheHouseGrid();
            System.out.println("The room has been attached to the house grid.\n");
        } else {
            System.out.println("No changes were made.\n");
        }
    }
}