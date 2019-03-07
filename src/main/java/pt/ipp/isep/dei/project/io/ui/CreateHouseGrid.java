package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.CreateHouseGridController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.HouseGridDTO;
import pt.ipp.isep.dei.project.model.HouseGridMapper;

/**
 * US130 As an Administrator, I want to create a house grid, so that I can define the rooms
 * that are attached to it and the contracted maximum power for that grid.
 */

public class CreateHouseGrid {
    private CreateHouseGridController controller;
    private HouseGridDTO gridDTO;

    public CreateHouseGrid(House house) {
        this.controller = new CreateHouseGridController(house);
    }

    public void run() {
        boolean flag = true;
        gridDTO = HouseGridMapper.newHouseGridDTO();
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
