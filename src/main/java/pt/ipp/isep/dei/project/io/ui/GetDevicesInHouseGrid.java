package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetDevicesInHouseGridController;
import pt.ipp.isep.dei.project.model.house.House;

/**
 * US160 As a Power User[or administrator], i want to get a list of all devices in a grid,
 * grouped by device type. It must include device location.
 */
public class GetDevicesInHouseGrid {

    private GetDevicesInHouseGridController controller;

    /**
     * method constructor that receives a housegrid
     *
     * @param house housegrid received
     */
    public GetDevicesInHouseGrid(House house) {
        this.controller = new GetDevicesInHouseGridController(house);
    }






    /*    *//**
     * method that shows a list of House Grids, the user select one, and then
     * it gets the list off all devices in that grid, grouped by device type and show the location of each device.
     * if there are no House Grids in the House, it shows that message
     * if there aren't devices in that House Grid, it shows that message.
     *//*
    public void run() {
        if (controller.isHouseGridListEmpty()) {
            System.out.println("There aren't House Grids in the House.\n");
            return;
        }

        StringBuilder content = new StringBuilder();
        content.append(controller.getHouseGridListToString());
        content.append("0 - Exit");
        String listContentRoom = content.toString();

        int maxPosition = controller.getHouseGridListSize();
        String label1 = "\nChoose the House Grid you want to get the list of devices\n" + listContentRoom;
        int selection = InputValidator.getIntRange(label1, 0, maxPosition);
        int gridPosition = (selection - 1);
        if (selection == 0) {
            return;
        }
        content.delete(0, content.length());

        if (controller.checkIfThereAreNoDevicesInGridbyPosition(gridPosition)) {
            System.out.println("There aren't devices in the chosen House Grid.\n");
            return;
        }

        content.append("\n\nThe list of devices in House Grid ");
        content.append(controller.getGridNameByPosition(gridPosition));
        content.append(" is:\n\n");
        content.append(controller.getDeviceListContentNameTypeLocationByGrid(gridPosition));
        System.out.println(content.toString());
    }*/
}
