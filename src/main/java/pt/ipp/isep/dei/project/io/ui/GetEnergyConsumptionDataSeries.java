package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionDataSeriesController;
import pt.ipp.isep.dei.project.model.House;

import java.time.LocalDateTime;

public class GetEnergyConsumptionDataSeries {

    private GetEnergyConsumptionDataSeriesController controller;

    public GetEnergyConsumptionDataSeries(House house) {
        this.controller = new GetEnergyConsumptionDataSeriesController(house);
    }

    private String getDataSeriesGrid() {
        if (controller.houseGridListIsEmpty()) {
            return ("Sorry! There are no house grids.");
        }
        else {
            String label2 = "Please choose a house grid:\n" + controller.getHouseGridListToString();
            int chosenGrid = InputValidator.getIntRange(label2, 1, controller.getHouseGridListSize()) - 1;
            controller.getHouseGridByPosition(chosenGrid);
            while (controller.roomListIsEmpty()) {
                System.out.println("\"There are no devices in this house grid, so it is not possible to \" " +
                        "+\ncalculate its energy consumption. Please choose another house grid.\\n\");");
                continue;
            }
            LocalDateTime initialDate = Menu.getInitialDate();
            LocalDateTime finalDate = Menu.getFinalDate();
            return controller.getHouseGridDataSeriesToString(initialDate, finalDate);
        }
    }

    private String getDataSeriesRoom() {
        if (controller.roomListIsEmpty()) {
            return ("Sorry! There are no rooms.");
        }
        String label2 = "Please choose a room:\n" + controller.getRoomListToString();
        int chosenRoom = InputValidator.getIntRange(label2, 1, controller.getRoomListSize()) - 1;
        controller.getRoomByPosition(chosenRoom);
        while (controller.deviceListIsEmpty()) {
            System.out.println("There are no devices in this room, so it is not possible to calculate its energy consumption.\n");
            chosenRoom = InputValidator.getIntRange(label2, 1, controller.getRoomListSize()) - 1;
            controller.getRoomByPosition(chosenRoom);
        }
        LocalDateTime initialDate = Menu.getInitialDate();
        LocalDateTime finalDate = Menu.getFinalDate();
        return controller.getRoomDataSeriesToString(initialDate, finalDate);
    }

    private String getDataSeriesDevice() {
        if (controller.deviceListIsEmpty()) {
            return ("Sorry! There are no devices.");
        }
        String label2 = "Please choose a device:\n" + controller.getDeviceListToString();
        int chosenDevice = InputValidator.getIntRange(label2, 1, controller.getDeviceListSize()) - 1;
        controller.getDeviceByPosition(chosenDevice);
        LocalDateTime initialDate = Menu.getInitialDate();
        LocalDateTime finalDate = Menu.getFinalDate();
        return controller.getDeviceDataSeriesToString(initialDate, finalDate);
    }


    public void run() {
        String label1 = "Would you like to get the data series of energy consumption of a House Grid, a Room or a Device1? " +
                "Please select the number that matches your choice:\n" + Menu.menuDataSeries();
        int chosenOption = InputValidator.getIntRange(label1, 0, 3);
        if (chosenOption == 0) {
            return;
        }
        switch (chosenOption) {
            case 1:
                System.out.println("The data series of the selected House Grid is:\n" + getDataSeriesGrid());
                break;
            case 2:
                System.out.println("The data series of the selected Room is:\n" + getDataSeriesRoom());
                break;
            case 3:
                System.out.println("The data series of the selected Device1 is:\n" + getDataSeriesDevice());
                break;
        }
    }
}
