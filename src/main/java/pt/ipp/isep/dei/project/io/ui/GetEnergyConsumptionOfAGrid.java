package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionController;
import pt.ipp.isep.dei.project.model.House;

import java.time.LocalDateTime;

public class GetEnergyConsumptionOfAGrid {

    private GetEnergyConsumptionController controller;

    public GetEnergyConsumptionOfAGrid(House house) {
        this.controller = new GetEnergyConsumptionController(house);
    }

    public void run() {

        String exit = "0- Exit";

        if (controller.isHouseGridListEmpty()) {
            System.out.println("There are no house grids in the house. Please, add one");
        } else {
            String label1 = "Please select a House Grid to see its total energy consumption: \n" + controller.getHouseGridListToString() + exit;
            int gridListLength = controller.getHouseGridListSize();
            int position = InputValidator.getIntRange(label1, 0, gridListLength);
            if (position == 0) {
                return;
            }
            controller.getHouseGridByPosition(position - 1);

            LocalDateTime startDate;
            LocalDateTime endDate;

            do {
                String label2 = "Please insert the initial date/hour of the period you want to consider in the following format: yyyy-MM-dd HH:mm. ";
                startDate = InputValidator.getStringDateTime(label2);

                String label3 = "Please insert the final date/hour of the period you want to consider in the following format: yyyy-MM-dd HH:mm. ";
                endDate = InputValidator.getStringDateTime(label3);
                if(endDate.isBefore(startDate)){
                    System.out.println("Error! The final date/hour can't be before the initial date/hour.\nPlease, insert new dates.");
                }
            }while (endDate.isBefore(startDate));

            double energyConsumption = controller.getEnergyConsumptionInAnInterval(startDate, endDate);
            String houseGridName = controller.getHouseGridName();
            System.out.println("The total energy consumption of the grid " + houseGridName + " is " + energyConsumption + " kWh.");
        }
    }



}

