package pt.ipp.isep.dei.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;

import java.util.ArrayList;
import java.util.List;

public class DetachRoomFromHouseGridController {
    @Autowired
    private HouseService houseService;
    private List<HouseGrid> houseGridList;


    public DetachRoomFromHouseGridController(HouseService houseService) {
        this.houseService = houseService;
        this.houseGridList = houseService.getAllGrids();
    }

    /**
     * Method that return the number of grids in the house.
     * @return
     */
    public int getGridListSize() {
        return houseGridList.size();
    }

    public List<HouseGridDTO> getListOfHouseGrid(){
        List<HouseGridDTO> gridDTOS = new ArrayList<>();
        for (HouseGrid grid : houseGridList) {
            gridDTOS.add(HouseGridMapper.mapToDTO(grid));
        }
        return gridDTOS;
    }


    public List<RoomDTO> getRoomsOfHouseGrid(String houseGridId){
        HouseGridId houseGridId1 = new HouseGridId(houseGridId);
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room : houseService.getRoomsOfAHouseGrid(houseGridId1)) {
            roomDTOS.add(RoomMapper.mapToDTO(room));
        }
        return roomDTOS;
    }

    public boolean detachRoomFromHouseGrid(RoomDTO roomDTO){
        RoomId roomId = new RoomId(roomDTO.getId());
        return houseService.detachRoomFromHouseGrid(roomId);
    }



}
