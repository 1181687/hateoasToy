package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetDevicesInHouseGridController;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.services.RoomAggregateService;

import java.util.List;

/**
 * US160 As a Power User[or administrator], i want to get a list of all devices in a grid,
 * grouped by device type. It must include device location.
 */
public class GetDevicesInHouseGrid {

    private GetDevicesInHouseGridController controller;
    private List<HouseGridDTO> houseGridDTOList;

    /**
     * method constructor that receives a housegrid
     *
     * @param houseService houseService received
     */
    public GetDevicesInHouseGrid(HouseService houseService) {
        this.controller = new GetDevicesInHouseGridController(houseService);
    }

    /**
     * method that shows a list of House Grids, the user select one, and then
     * it gets the list off all devices in that grid, grouped by device type and show the location of each device.
     * if there are no House Grids in the House, it shows that message
     * if there aren't devices in that House Grid, it shows that message.
     */
    public void run() {
        if (controller.isHouseGridListEmpty()) {
            System.out.println("There aren't House Grids in the House.\n");
            return;
        }

        StringBuilder content = new StringBuilder();
        content.append(this.getHouseGridListToString());
        content.append("0 - Exit");
        String listContentRoom = content.toString();

        int maxPosition = this.getHouseGridListSize();
        String label1 = "\nChoose the House Grid you want to get the list of devices\n" + listContentRoom;
        int selection = InputValidator.getIntRange(label1, 0, maxPosition);
        int gridPosition = (selection - 1);
        if (selection == 0) {
            return;
        }
        content.delete(0, content.length());

        HouseGridDTO chosenHouseGrid = houseGridDTOList.get(gridPosition);

        if (controller.checkIfThereAreNoDevicesInGridbyId(chosenHouseGrid.getId())) {
            System.out.println("There aren't devices in the chosen House Grid.\n");
            return;
        }

        content.append("\n\nThe list of devices in House Grid ");
        content.append(controller.getGridNameById(chosenHouseGrid));
        content.append(" is:\n\n");
        content.append(controller.getDeviceListContentNameTypeLocationByGrid(chosenHouseGrid.getId()));
        System.out.println(content.toString());
    }


    // Necessary methods
    public String getHouseGridListToString() {
        this.houseGridDTOList = this.controller.getHouseGridDTOList();
        int number = 1;

        StringBuilder content = new StringBuilder();

        for (HouseGridDTO houseGridDTO : this.houseGridDTOList) {
            content.append(number);
            content.append(" - ");
            content.append(houseGridDTO.getId());
            content.append("\n");
            number++;
        }
        return content.toString();
    }

    private int getHouseGridListSize() {
        return this.houseGridDTOList.size();
    }


}
