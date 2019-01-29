package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionOfAGridController;
import pt.ipp.isep.dei.project.model.House;

import java.time.LocalDateTime;

public class GetEnergyConsumptionOfAGrid {

    private GetEnergyConsumptionOfAGridController mController;

    public GetEnergyConsumptionOfAGrid(House house) {
        this.mController = new GetEnergyConsumptionOfAGridController(house);
    }

    public void run() {

        String exit = "0- Exit";

        if (mController.isHouseGridListEmpty()) {
            System.out.println("There are no house grids in the house. Please, add one");
        } else {
            String label1 = "Please select a House Grid to see its total energy consumption: \n" + mController.getHouseGridListToString() + exit;
            int gridListLength = mController.getHouseGridListSize();
            int position = InputValidator.getIntRange(label1, 0, gridListLength);
            if (position == 0) {
                return;
            }
            mController.getHouseGridByPosition(position);

            String label2 = "Please insert the initial date/hour of the period you want to consider in the following format: yyyy-MM-dd HH:mm. ";
            LocalDateTime startDate = InputValidator.getStringDateTime(label2);
            String label3 = "Please insert the final date/hour of the period you want to consider in the following format: yyyy-MM-dd HH:mm. ";
            LocalDateTime endDate = InputValidator.getStringDateTime(label3);

            double energyConsumption = mController.getEnergyConsumptionInAnInterval(startDate, endDate);
            String houseGridName = mController.getHouseGridName();
            System.out.println("The total energy consumption of the grid " + houseGridName + " is " + energyConsumption + " kWh.");
        }
    }

}

