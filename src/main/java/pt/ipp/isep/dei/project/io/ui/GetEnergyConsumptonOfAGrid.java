package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionOfAGridController;
import pt.ipp.isep.dei.project.model.House;

import java.time.LocalDateTime;

public class GetEnergyConsumptonOfAGrid {

    private GetEnergyConsumptionOfAGridController mController;

    public GetEnergyConsumptonOfAGrid(House house) {
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
            mController.getHouseGridByPosition(position);

            String label2 = "Please insert the first date of the period (yyyy-MM-dd): ";
            LocalDateTime startDate = InputValidator.getStringDateTime(label2);
        }
    }

}

