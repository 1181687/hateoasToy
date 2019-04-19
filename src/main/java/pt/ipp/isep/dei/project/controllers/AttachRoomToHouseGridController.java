package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.house.RoomMapper;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;
import pt.ipp.isep.dei.project.services.HouseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AttachRoomToHouseGridController {
    private HouseService houseService;
    private HouseGrid chosenGrid;
    private Room chosenRoom;

    /**
     * Constructor.
     *
     * @param houseService HouseService to be used.
     */
    public AttachRoomToHouseGridController(HouseService houseService) {
        this.houseService = houseService;
    }

    /**
     * Method that asks for all the grids in the repo, turns each one into a dto object and adds it to a list,
     * in order to be sent to the UI.
     *
     * @return List of HouseGridDTO.
     */
    public List<HouseGridDTO> getGrids() {
        List<HouseGridDTO> gridDTOS = new ArrayList<>();
        for (HouseGrid grid : houseService.getAllGrids()) {
            gridDTOS.add(HouseGridMapper.mapToDTO(grid));
        }
        return gridDTOS;
    }

    /**
     * Method that asks for all the rooms in the repo, turns each one into a dto object and adds it to a list,
     * in order to be sent to the UI.
     *
     * @return List of RoomDTO.
     */
    public List<RoomDTO> getRooms() {
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room : houseService.getAllRooms()) {
            roomDTOS.add(RoomMapper.mapToDTO(room));
        }
        return roomDTOS;
    }

    /**
     * Method that stores the house grid selected by the user into the attribute chosenGrid.
     *
     * @param gridId Id of the grid to be stored.
     */
/*    public void setGrid(String gridId) {
        HouseGridId houseGridId = new HouseGridId(gridId);
        this.chosenGrid = houseService.getGridById(houseGridId);
    }*/

    /**
     * Method that stores the room selected by the user into the attribute chosenRoom.
     *
     * @param roomId Id of the room to be stored.
     */
    public void setRoom(String roomId) {
        RoomId id = new RoomId(roomId);
        this.chosenRoom = houseService.getRoomById(id);
    }

    /**
     * Method that asks for the grid where the room might already be connected.
     *
     * @return Grid where the room is is connected to.
     */
    public boolean isRoomInAGrid() {
        return Objects.nonNull(chosenRoom.getHouseGridId());
    }

    /**
     * Method that checks if the room isn't already in the chosen grid.
     *
     * @return True or false.
     */
    public boolean isRoomAlreadyInChosenGrid() {
        return chosenRoom.getHouseGridId().equals(chosenGrid.getHouseGridId());
    }

    /**
     * Method that attaches the chosen room in the chosen grid.
     */
    public void attachRoomInTheHouseGrid() {
        chosenRoom.setHouseGridId(chosenGrid.getHouseGridId());
        houseService.updateRoomRepository(chosenRoom);
    }
}