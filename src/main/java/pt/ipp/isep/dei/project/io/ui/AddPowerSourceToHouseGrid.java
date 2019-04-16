package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddPowerSourceToHouseGridController;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.HouseService;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.services.HouseGridService;
import pt.ipp.isep.dei.project.services.PowerSourceTypeService;

import java.util.List;

/**
 * US135 As an Administrator, I want to add a power source to a housegrid grid, so that the
 * produced energy may be used by all devices in that grid.
 */

public class AddPowerSourceToHouseGrid {

    private AddPowerSourceToHouseGridController controller;
    private List<HouseGridDTO> houseGridDTOS;

    public AddPowerSourceToHouseGrid(HouseService houseService) {
        this.controller = new AddPowerSourceToHouseGridController(houseService);
    }

    public void run() {
        if (controller.isHouseGridRepositoryEmpty()) {
            System.out.println("There are no grids in your house. Please insert a new grid. \n");
        } else {

            String label1 = "Please select the grid to which you want to add the Power Source: \n" + getGridListToString();
            int positionOfHouseGrid = InputValidator.getIntRange(label1, 1, getGridListSize());

            String gridId = getGridDTOByPosition(positionOfHouseGrid-1).getId();

            controller.getHouseGridFromListByPosition(positionOfHouseGrid);

            String powerSourceName;
            boolean isPowerSourceAdded = false;
            do {

                String label2 = "Please insert the name of the new power source";
                powerSourceName = InputValidator.getString(label2);

                String label3 = "Please select the power source type: \n" + controller.getPowerSourceTypeListToString();
                int positionOfPowerSource = InputValidator.getIntRange(label3, 1, controller.getPowerSourceTypeListSize()) - 1;

                controller.getPowerSourceTypeByPosition(positionOfPowerSource);

                try {
                    isPowerSourceAdded = controller.createAndAddPowerSourceToHouseGrid(powerSourceName);
                } catch (Exception e) {
                    System.out.println("Name already exists. Please write a new one.");
                    isPowerSourceAdded = false;
                }

            } while (!isPowerSourceAdded);

            String houseGridName = controller.getHouseGridName();
            System.out.println("Success! The power source " + powerSourceName + " was added to the housegrid grid " + houseGridName + ".");
            String label4 = "Do you want to see the list of power sources added to this housegrid grid? (y/n)";
            String answer = InputValidator.confirmValidation(label4);
            if ("y".equals(answer) || "Y".equals(answer)) {
                System.out.println(controller.listPowerSourcesConnectedToGrid());
            }
        }
    }

    private String getGridListToString(){
        this.houseGridDTOS = this.controller.getGridList();
        int number = 1;

        StringBuilder content = new StringBuilder();

        for (HouseGridDTO grid : this.houseGridDTOS) {
            content.append(number);
            content.append(" - ");
            content.append(grid.getId());
            content.append("\n");
            number++;
        }
        return content.toString();
    }

    private int getGridListSize(){
        return this.houseGridDTOS.size();
    }

    private HouseGridDTO getGridDTOByPosition(int position){
        return this.houseGridDTOS.get(position);
    }
}


