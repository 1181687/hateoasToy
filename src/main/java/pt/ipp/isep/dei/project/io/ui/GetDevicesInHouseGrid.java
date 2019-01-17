package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetDevicesInHouseGridController;
import pt.ipp.isep.dei.project.model.House;

/**
 * US160 As a Power User[or administrator], i want to get a list of all devices in a grid,
 * grouped by device type. It must include device location.
 */
public class GetDevicesInHouseGrid {

    private GetDevicesInHouseGridController mctrl;

    /**
     * method constructor that receives a house
     * @param house house received
     */
    public GetDevicesInHouseGrid(House house) {
        this.mctrl = new GetDevicesInHouseGridController(house);
    }

    /**
     * method that shows a list of House Grids, the user select one, and then
     * it gets the list off all devices in that grid, grouped by device type and show the location of each device.
     * if there are no House Grids in the House, it shows that message
     * if there aren't devices in that House Grid, it shows that message.
     */
    public void run() {
        if (mctrl.checkIfHouseGridListIsEmpty()) {
            System.out.println("There aren't House Grids in the House.\n");
            return;
        }

        StringBuilder content = new StringBuilder();
        content.append(mctrl.getHouseGridListToString());
        content.append("0 - Exit");
        System.out.println(content.toString());

        int maxPosition = mctrl.getHouseGridListLength();
        String label1 = "Choose the House Grid you want to get the list of devices";
        int selection = InputValidator.getIntRange(label1, 0, maxPosition);
        int positionHG = (selection - 1);
        if (selection == 0) {
            return;
        }

        if (mctrl.checkIfThereAreNoDevicesHGbyPosition(positionHG)) {
            System.out.println("There aren't devices in the chosen House Grid.\n");
            return;
        }

        content.append("\nThe list of devices in House Grid ");
        content.append(mctrl.getHGNameByHGPosition(positionHG));
        content.append(" is:\n\n");
        content.append(mctrl.getDeviceListContentNameTypeLocationByHG(positionHG));
        System.out.println(content.toString());
    }
}
