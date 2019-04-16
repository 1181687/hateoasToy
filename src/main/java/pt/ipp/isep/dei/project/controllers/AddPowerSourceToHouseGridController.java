package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.HouseService;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSource;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceType;
import pt.ipp.isep.dei.project.services.HouseGridService;
import pt.ipp.isep.dei.project.services.PowerSourceTypeService;

import java.util.ArrayList;
import java.util.List;

public class AddPowerSourceToHouseGridController {

    private HouseService houseService;


    public AddPowerSourceToHouseGridController(HouseService houseService) {

        this.houseService = houseService;
    }

    /**
     * Method that checks if the List of House Grids is empty.
     *
     * @return boolean
     */
    public boolean isHouseGridRepositoryEmpty() {
        return this.houseService.isGridRepositoryEmpty();
    }


    public List<HouseGridDTO> getGridList() {
        List<HouseGrid> grids = this.houseService.getAllGrids();
        List<HouseGridDTO> gridDTOS = new ArrayList<>();
        for (HouseGrid grid : grids) {
            HouseGridDTO gridDTO = HouseGridMapper.mapToDTO(grid);
            gridDTOS.add(gridDTO);
        }
        return gridDTOS;
    }

/*
    public void getHouseGridById(String id) {
        HouseGridId gridId = new HouseGridId(id);
        this.houseService
    }

    *//**
     * Method that creates a powersource and adds it to the PowerSourceService in the selected housegrid.
     * @param name of the Power Source.
     * @return true if the Power Source is created and added with success to the housegrid or false if the powersource is not added
     *//*

    public boolean createAndAddPowerSourceToHouseGrid(String name) {
        PowerSource newPowerSource = this.selectedHouseGrid.getPowerSourceList().createNewPowerSource(name, this.selectedPowerSourceType);
        return this.selectedHouseGrid.addPowerSource(newPowerSource);
    }

    *//**
     * Returns a list of Power Source Types.
     * @return String
     *//*
    public String getPowerSourceTypeListToString() {
        return powerSourceTypeService.getPowerSourceTypeListToString();
    }

    *//**
     * Returns the number of elements contained in the PowerSourceTypeService.
     *
     * @return
     *//*
    public int getPowerSourceTypeListSize() {
        return powerSourceTypeService.getSize();
    }

    *//**
     * Method that, given a position, retrieves a PowerSourceType from a PowerSourceTypeService.
     *
     * @param position
     *//*
    public void getPowerSourceTypeByPosition(int position) {
        this.selectedPowerSourceType = this.powerSourceTypeService.getPowerSourceTypeFromASpecificPosition(position);
    }

    *//**
     * Returns the selected housegrid name.
     * @return String
     *//*
    public String getHouseGridName(){
        return selectedHouseGrid.getName();
    }

    *//**
     * Returns a list of PowerSources that have been added to the housegrid's PowerSourceService.
     * @return
     *//*
    public String listPowerSourcesConnectedToGrid(){
        return selectedHouseGrid.getPowerSourceListContent();
    }*/
}
