package pt.ipp.isep.dei.project.controllers;


import pt.ipp.isep.dei.project.services.HouseGridService;


public class CreateHouseGridController {

    private HouseGridService houseGridService;

    /**
     * Constructor.
     *
     * @param houseGridService Service to be used.
     */
    public CreateHouseGridController(HouseGridService houseGridService) {
        this.houseGridService = houseGridService;
    }

    /**
     * Method that creates a house grid and adds it to the repo.
     * If there isn't already a house grid with that id in the repo, a new house grid is created and added to it.
     * Else, no house grid is created and/or sent to the repo.
     *
     * @param gridId String to be used.
     * @return True or false.
     */
    public boolean createANewHouseGrid(String gridId) {
        return houseGridService.createHouseGrid(gridId);
    }

}