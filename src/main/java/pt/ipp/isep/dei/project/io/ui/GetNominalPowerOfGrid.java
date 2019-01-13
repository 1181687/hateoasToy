package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetNominalPowerOfAGridController;
import pt.ipp.isep.dei.project.model.House;

public class GetNominalPowerOfGrid {

    private GetNominalPowerOfAGridController mController;

    public GetNominalPowerOfGrid(House house) {
        this.mController = new GetNominalPowerOfAGridController(house);
    }

    public void run() {
        if (mController.checkIfGridListisEmpty()) {
            System.out.println("There are no house grids in the house. Please, add one");
        } else {
            String label1 = "Please select a House Grid to see its total nominal power: \n" + mController.listHouseGrids();
            int gridListLength = mController.houseGridListLength();
            int position = InputValidator.getIntRange(label1, 1, gridListLength);
            mController.getHouseGridbyPosition(position);

            double nominalPower = mController.getHouseGridTotalNominalPower();
            System.out.println("The total nominal power of the selected house grid is " + nominalPower);

        }
    }
}
