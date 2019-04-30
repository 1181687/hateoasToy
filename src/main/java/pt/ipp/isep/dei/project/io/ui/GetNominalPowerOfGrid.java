package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetNominalPowerController;
import pt.ipp.isep.dei.project.services.HouseService;

public class GetNominalPowerOfGrid {

    private GetNominalPowerController controller;

    public GetNominalPowerOfGrid(HouseService houseService) {
        this.controller = new GetNominalPowerController(houseService);
    }

    public void run() {
        String exit = "0- Exit";
        if (controller.isGridListEmpty()) {
            System.out.println("There are no housegrid grids in the housegrid. Please, add one");
        } else {
            String label1 = "Please select a House Grid to see its total nominal power: \n" + controller.getHouseGridsListToString() + exit;
            int gridListLength = controller.getHouseGridListSize();
            int position = InputValidator.getIntRange(label1, 0, gridListLength);
            if (position == 0) {
                return;
            }
            controller.getHouseGridByPosition(position);

            double nominalPower = controller.getHouseGridTotalNominalPower();
            System.out.println("The total nominal power of the selected housegrid grid is " + nominalPower + "kW.");

        }
    }
}
