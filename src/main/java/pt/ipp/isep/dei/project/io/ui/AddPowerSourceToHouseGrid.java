package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddPowerSourceToHouseGridController;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeList;

/**
 * US135 As an Administrator, I want to add a power source to a housegrid grid, so that the
 * produced energy may be used by all devices in that grid.
 */

public class AddPowerSourceToHouseGrid {

    private AddPowerSourceToHouseGridController controller;

    public AddPowerSourceToHouseGrid(House house, PowerSourceTypeList powerSourceTypeList) {
        this.controller = new AddPowerSourceToHouseGridController(house, powerSourceTypeList);
    }

    public void run() {
        if (controller.isHouseGridListEmpty()) {
            System.out.println("There are no housegrid grids in your housegrid. Please insert a new housegrid grid." + "\n");
        } else {

            String label1 = "Please select the House Grid you want to add the Power Source: \n" + controller.getHouseGridListToString();
            int positionOfHouseGrid = InputValidator.getIntRange(label1, 1, controller.getHouseGridListSize()) - 1;
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
}


