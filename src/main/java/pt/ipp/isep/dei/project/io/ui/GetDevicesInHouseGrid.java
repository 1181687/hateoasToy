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
            System.out.println("There aren't House Grids in the House.\n");
            return;
        }
        System.out.println(mctrl.getHouseGridListToString());
        int maxPosition = mctrl.getHouseGridListLength();

        String label1 = "Choose the House Grid you want to get the list of devices";
        int selection = InputValidator.getIntRange(label1, 1, maxPosition);
        int positionHG = (selection - 1);

        if (mctrl.checkIfThereAreNoDevicesHGbyPosition(positionHG)) {
            System.out.println("There aren't devices in the chosen House Grid.\n");
            return;
        }
        StringBuilder content = new StringBuilder();
        content.append("\nThe list of devices in House Grid ");
        content.append(mctrl.getHGNameByHGPosition(positionHG));
        content.append(" is:\n\n");
        content.append(mctrl.getDeviceListContentNameTypeLocationByHG(positionHG));
        System.out.println(content.toString());
    }
}
