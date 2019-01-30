package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetEnergyConsumptionDataSeriesController;
import pt.ipp.isep.dei.project.model.House;

import java.time.LocalDateTime;

public class GetEnergyConsumptionDataSeries {

    private GetEnergyConsumptionDataSeriesController mCtrl;

    public GetEnergyConsumptionDataSeries(House house) {
        this.mCtrl = new GetEnergyConsumptionDataSeriesController(house);
    }

    private String menu() {
        StringBuilder firstMenu = new StringBuilder();
        firstMenu.append("1 - House Grid\n");
        firstMenu.append("2 - Room\n");
        firstMenu.append("3 - Device\n");
        return firstMenu.toString();
    }

    private LocalDateTime getInitialDate() {
        String label3 = "Please insert the initial date/hour of the period you want to consider for the calculations in the following format: yyyy-MM-dd HH:mm. ";
        LocalDateTime initialDate = InputValidator.getStringDateTime(label3);
        return initialDate;
    }

    private LocalDateTime getFinalDate() {
        String label4 = "Please insert the final date/hour of the period you want to consider for the calculations in the following format: yyyy-MM-dd HH:mm. ";
        LocalDateTime finalDate = InputValidator.getStringDateTime(label4);
        return finalDate;
    }

    private String getDataSeriesGrid() {
        String label2 = "Please choose a house grid:\n" + mCtrl.getHouseGridListToString();
        int chosenGrid = InputValidator.getIntRange(label2, 1, mCtrl.getHouseGridListSize()) - 1;
        mCtrl.getHouseGridByPosition(chosenGrid);
        LocalDateTime initialDate = getInitialDate();
        LocalDateTime finalDate = getFinalDate();
        return mCtrl.getHouseGridDataSeriesToString(initialDate, finalDate);
    }

    private String getDataSeriesRoom() {
        String label2 = "Please choose a room:\n" + mCtrl.getRoomListToString();
        int chosenRoom = InputValidator.getIntRange(label2, 1, mCtrl.getRoomListSize()) - 1;
        mCtrl.getRoomByPosition(chosenRoom);
        LocalDateTime initialDate = getInitialDate();
        LocalDateTime finalDate = getFinalDate();
        return mCtrl.getRoomDataSeriesToString(initialDate, finalDate);
    }

    private String getDataSeriesDevice() {
        String label2 = "Please choose a device:\n" + mCtrl.getDeviceListToString();
        int chosenDevice = InputValidator.getIntRange(label2, 1, mCtrl.getDeviceListSize()) - 1;
        mCtrl.getDeviceByPosition(chosenDevice);
        LocalDateTime initialDate = getInitialDate();
        LocalDateTime finalDate = getFinalDate();
        return mCtrl.getDeviceDataSeriesToString(initialDate, finalDate);
    }


    public void run() {
        String label1 = "Would you like to get the data series of energy consumption of a House Grid, a Room or a Device? " +
                "Please select the number that matches your choice:\n" + menu();
        int chosenOption = InputValidator.getIntRange(label1, 1, 3);
        if (chosenOption == 0) {
            return;
        }
        switch (chosenOption) {
            case 1:
                System.out.println(getDataSeriesGrid());
                break;
            case 2:
                System.out.println(getDataSeriesRoom());
                break;
            case 3:
                System.out.println(getDataSeriesDevice());
                break;
        }
    }
}
