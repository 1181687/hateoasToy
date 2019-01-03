package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US600Controller;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.SensorType;

public class US600UI {

    private US600Controller ctrl;

    public US600UI(House house, SensorType sensorType) {
        this.ctrl = new US600Controller(house, sensorType);
    }

    public void run() {
        if (Double.isNaN(ctrl.getMostRecentAvailableMeasurementInTheHouseArea())) {
            System.out.println("There are no " + ctrl.getSensorType() + " sensors with valid measurements in the house area.");
            return;
        } else {
            System.out.println("The most recent measurement available in the house area for "
                    + ctrl.getSensorType() + "is" + ctrl.getMostRecentAvailableMeasurementInTheHouseArea());
        }
    }
}
