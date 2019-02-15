package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.CreateHouseGridController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.HouseGrid;

/**
 * US130 As an Administrator, I want to create a house grid, so that I can define the rooms
 * that are attached to it and the contracted maximum power for that grid.
 */

public class CreateHouseGrid {
    private CreateHouseGridController mController;

    public CreateHouseGrid(House house) {
        this.mController = new CreateHouseGridController(house);
    }

    public void run() {
        boolean flag = true;
        String label1 = "Please insert the name of the House Grid you want to create.";
        String nameHG;

        do {
            nameHG = InputValidator.getString(label1);
            try {
                HouseGrid houseGridCreated = mController.createANewHouseGrid(nameHG);
                mController.addHouseGridToTheListOfHouseGrids(houseGridCreated);
                System.out.println("Your House Grid was successfully created! \n");
                flag=false;
            } catch (Exception e) {
                System.out.println("Name already exists. Please write a new one.");
                flag = true;
            }
        } while (flag);
    }

}
