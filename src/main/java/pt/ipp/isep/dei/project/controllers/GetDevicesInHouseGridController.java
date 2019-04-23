package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.services.RoomAggregateService;

import java.util.ArrayList;
import java.util.List;

/**
 * US160 As a Power User[or administrator], i want to get a list of all devices in a grid,
 * grouped by device type. It must include device location.
 */

public class GetDevicesInHouseGridController {
    private HouseService houseService;


    public GetDevicesInHouseGridController(HouseService houseService) {
        this.houseService = houseService;
    }


    public String getDeviceListContentNameTypeLocationByGrid(String id) {
        return houseService.getDeviceListContentNameTypeLocationByGrid(id);
    }

    public List<HouseGridDTO> getHouseGridDTOList() {
        List<HouseGrid> houseGridList = this.houseService.getAllGrids();
        List<HouseGridDTO> houseGridDTOList = new ArrayList<>();
        for (HouseGrid houseGrid : houseGridList) {
            HouseGridDTO houseGridDTO = HouseGridMapper.mapToDTO(houseGrid);
            houseGridDTOList.add(houseGridDTO);
        }
        return houseGridDTOList;
    }

    /**
     * Method that checks if the housegrid grid's list is empty.
     * @return True or false.
     */
    public boolean isHouseGridListEmpty() {
        return houseService.isHouseGridListEmpty();
    }

    /**
     * method that checks if there are no devices in the RoomList
     * @return true if there aren't devices. False if there are devices
     */
    public boolean checkIfThereAreNoDevicesInGridbyId(String houseGridId) {
        return this.houseService.getDeviceListByRoomOfGridById(houseGridId);
    }


    /**
     * method that gets the name of House Grid by it's position in the list of housegrid grids.
     * @param houseGridDTO DTO of the House Grid
     * @return String name
     */
    public String getGridNameById(HouseGridDTO houseGridDTO) {
        return this.houseService.getGridNameById(houseGridDTO.getId());
    }


}
