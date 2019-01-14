package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetDevicesInHouseGridController;
import pt.ipp.isep.dei.project.model.House;

public class GetDevicesInHouseGrid {

    private GetDevicesInHouseGridController mctrl;

    /**
     * method constructor that receives a house
     *
     * @param house house received
     */
    public GetDevicesInHouseGrid(House house) {
        this.mctrl = new GetDevicesInHouseGridController(house);
    }

    public void run() {
        if (mctrl.checkIfHouseGridListIsEmpty()) {
            System.out.println("There aren't House Grids in the House.");
        }
        System.out.println(mctrl.getHouseGridListToString());
        int maxPosition = mctrl.getHouseGridListLength();

        String label1 = "Choose the House Grid you want to get the list of devices";
        int seleccion = InputValidator.getIntRange(label1, 1, maxPosition);
        int positionHG = (seleccion - 1);

        if (mctrl.checkIfThereAreNoDevicesHGbyPosition(positionHG)) {
            System.out.println("There aren't devices in the choosen House Grid.");
        }
        mctrl.getDeviceListContentNameTypeLocationByHG(positionHG);
    }

}
