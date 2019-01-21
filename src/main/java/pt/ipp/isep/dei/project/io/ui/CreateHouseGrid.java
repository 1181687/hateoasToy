package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.CreateHouseGridController;
import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;

/** US130 As an Administrator, I want to create a house grid, so that I can define the rooms
that are attached to it and the contracted maximum power for that grid. */

public class CreateHouseGrid {
    private CreateHouseGridController mController;

    public CreateHouseGrid(HouseGridList houseGridList) {
        this.mController = new CreateHouseGridController(houseGridList);
    }

    public void run(){
        String label1 ="Please insert the name of the House Grid you want to create.";
        String nameHG = InputValidator.getString(label1);

        HouseGrid houseGridCreated = mController.createANewHouseGrid(nameHG);
        mController.addHouseGridToTheListOfHouseGrids(houseGridCreated);
        System.out.println("Your House Grid was succesfully created! \n" );
    }

}
