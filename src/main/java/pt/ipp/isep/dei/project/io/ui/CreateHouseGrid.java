package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.CreateHouseGridController;
import pt.ipp.isep.dei.project.services.HouseGridService;

public class CreateHouseGrid {
    private CreateHouseGridController controller;

    /**
     * Constructor.
     *
     * @param houseGridService Service to be used.
     */
    public CreateHouseGrid(HouseGridService houseGridService) {
        this.controller = new CreateHouseGridController(houseGridService);
    }

    /**
     * RUN!
     */
    public void run() {
        boolean flag = true;
        while (flag) {
            String id = InputValidator.getString("Please insert an id for the house grid you want to create.");
            if (controller.createANewHouseGrid(id)) {
                System.out.println("Your house grid was successfully created!\n");
                flag = false;
            } else {
                System.out.println("A house grid with that id already exists.");
            }
        }
    }
}
