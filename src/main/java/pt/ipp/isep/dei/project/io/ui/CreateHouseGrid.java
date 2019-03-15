package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.CreateHouseGridController;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;

/**
 * US130 As an Administrator, I want to create a housegrid grid, so that I can define the rooms
 * that are attached to it and the contracted maximum power for that grid.
 */

public class CreateHouseGrid {
    private CreateHouseGridController controller;
    private HouseGridDTO gridDTO = HouseGridMapper.newHouseGridDTO();

    public CreateHouseGrid(House house) {
        this.controller = new CreateHouseGridController(house);
    }

    public void run() {
        boolean flag = true;
        String label1 = "Please insert the name of the House Grid you want to create.";
        String nameHG;

        do {
            nameHG = InputValidator.getString(label1);
            gridDTO.setName(nameHG);
            try {
                if(controller.createANewHouseGrid(gridDTO)){

                    System.out.println("Your House Grid was successfully created! \n");
                    flag=false;
                }

            } catch (Exception e) {
                System.out.println("Name already exists. Please write a new one.");
                flag = true;
            }
        } while (flag);
    }

}
