package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSource;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceType;
import pt.ipp.isep.dei.project.services.HouseGridService;
import pt.ipp.isep.dei.project.services.PowerSourceTypeService;

import java.util.List;

public class AddPowerSourceToHouseGridController {

    private PowerSourceTypeService powerSourceTypeService;
    private HouseGridService houseGridService;


    public AddPowerSourceToHouseGridController(PowerSourceTypeService powerSourceTypeService, HouseGridService houseGridService) {

        this.powerSourceTypeService = powerSourceTypeService;
        this.houseGridService=houseGridService;
    }

    /**
     * Method that checks if the List of House Grids is empty.
     *
     * @return boolean
     */
    public boolean isHouseGridRepositoryEmpty() {
        return this.houseGridService.isGridRepositoryEmpty();
    }


    /**
     * Method that lists the House Grids in the list of Housegrids.
     *
     * @return String
     */
    public List<HouseGridDTO> getGridList() {
        List<HouseGrid> grids = this.houseGridService.getAllGrids();
        for (HouseGrid grid : grids) {
            HouseGridMapper
        }
    }

    /**
     * Method that calculates the size of the housegrid list.
     * @return int
     */

    public int getHouseGridListSize(){
        return house.getHouseGridListSize();
    }

    /**
     * Method that gets a housegrid from a list of Housegrids by position.
     * @param position
     */

    public void getHouseGridFromListByPosition(int position) {
        this.selectedHouseGrid = this.house.getHouseGridByPosition(position);
    }

    /**
     * Method that creates a powersource and adds it to the PowerSourceService in the selected housegrid.
     * @param name of the Power Source.
     * @return true if the Power Source is created and added with success to the housegrid or false if the powersource is not added
     */

    public boolean createAndAddPowerSourceToHouseGrid(String name) {
        PowerSource newPowerSource = this.selectedHouseGrid.getPowerSourceList().createNewPowerSource(name, this.selectedPowerSourceType);
        return this.selectedHouseGrid.addPowerSource(newPowerSource);
    }

    /**
     * Returns a list of Power Source Types.
     * @return String
     */
    public String getPowerSourceTypeListToString() {
        return powerSourceTypeService.getPowerSourceTypeListToString();
    }

    /**
     * Returns the number of elements contained in the PowerSourceTypeService.
     *
     * @return
     */
    public int getPowerSourceTypeListSize() {
        return powerSourceTypeService.getSize();
    }

    /**
     * Method that, given a position, retrieves a PowerSourceType from a PowerSourceTypeService.
     *
     * @param position
     */
    public void getPowerSourceTypeByPosition(int position) {
        this.selectedPowerSourceType = this.powerSourceTypeService.getPowerSourceTypeFromASpecificPosition(position);
    }

    /**
     * Returns the selected housegrid name.
     * @return String
     */
    public String getHouseGridName(){
        return selectedHouseGrid.getName();
    }

    /**
     * Returns a list of PowerSources that have been added to the housegrid's PowerSourceService.
     * @return
     */
    public String listPowerSourcesConnectedToGrid(){
        return selectedHouseGrid.getPowerSourceListContent();
    }
}
