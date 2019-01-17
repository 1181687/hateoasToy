package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetNominalPowerOfAGridController;
import pt.ipp.isep.dei.project.model.House;

public class GetNominalPowerOfGrid {

    private GetNominalPowerOfAGridController mController;

    public GetNominalPowerOfGrid(House house) {
        this.mController = new GetNominalPowerOfAGridController(house);
    }

    public void run() {
        String exit = "0- Exit";
        if (mController.checkIfGridListIsEmpty()) {
            System.out.println("There are no house grids in the house. Please, add one");
        } else {
            String label1 = "Please select a House Grid to see its total nominal power: \n" + mController.listHouseGrids()+exit;
            int gridListLength = mController.getHouseGridListLength();
            int position = InputValidator.getIntRange(label1, 0, gridListLength);
            if(position == 0){
                return;
            }
            mController.getHouseGridByPosition(position);

            double nominalPower = mController.getHouseGridTotalNominalPower();
            System.out.println("The total nominal power of the selected house grid is " + nominalPower + "kW.");

        }
    }
}
