package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetNominalPowerRoomsDevicesController;
import pt.ipp.isep.dei.project.model.House;

public class GetNominalPowerOfGrid {

    private GetNominalPowerRoomsDevicesController controller;

    public GetNominalPowerOfGrid(House house) {
        this.controller = new GetNominalPowerRoomsDevicesController(house);
    }

    public void run() {
        String exit = "0- Exit";
        if (controller.isGridListEmpty()) {
            System.out.println("There are no house grids in the house. Please, add one");
        } else {
            String label1 = "Please select a House Grid to see its total nominal power: \n" + controller.getHouseGridsListToString() + exit;
            int gridListLength = controller.getHouseGridListSize();
            int position = InputValidator.getIntRange(label1, 0, gridListLength);
            if (position == 0) {
                return;
            }
            controller.getHouseGridByPosition(position);

            double nominalPower = controller.getHouseGridTotalNominalPower();
            System.out.println("The total nominal power of the selected house grid is " + nominalPower + "kW.");

        }
    }
}
