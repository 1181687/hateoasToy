package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.CreateHouseGridController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.HouseGrid;

/** US130 As an Administrator, I want to create a house grid, so that I can define the rooms
that are attached to it and the contracted maximum power for that grid. */

public class CreateHouseGrid {
    private CreateHouseGridController mController;

    public CreateHouseGrid(House house) {
        this.mController = new CreateHouseGridController(house);
    }

    public void run(){
        String label1 ="Please insert the name of the House Grid you want to create.";
        String nameHG = InputValidator.getString(label1);

        HouseGrid houseGridCreated = mController.createANewHouseGrid(nameHG);
        mController.addHouseGridToTheListOfHouseGrids(houseGridCreated);
        System.out.println("Your House Grid was succesfully created! \n" );
    }

}
