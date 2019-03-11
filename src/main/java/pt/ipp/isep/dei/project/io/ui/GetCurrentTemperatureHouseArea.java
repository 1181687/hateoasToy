package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetTotalAndAverageRainfallAndCurrentTempHouseAreaController;
import pt.ipp.isep.dei.project.model.house.House;


/** US600 As a Regular User, I want to get the current temperature in the housegrid area. If, in the
 first element with temperature sensors of the hierarchy of geographical areas that
 includes the housegrid, there is more than one temperature sensor, the nearest one
 should be used.
 */

public class GetCurrentTemperatureHouseArea {
    private GetTotalAndAverageRainfallAndCurrentTempHouseAreaController controller;

    public GetCurrentTemperatureHouseArea(House house) {
        this.controller = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(house);
    }

    public void run() {
        if (Double.isNaN(controller.getMostRecentAvailableMeasurement())) {
            System.out.println("There are no " + controller.getTypeTemperature() + " sensors with valid measurements in the housegrid area.");
            return;
        } else {
            System.out.println("The most recent measurement available in the housegrid area for "
                    + controller.getTypeTemperature() + " is " + controller.getMostRecentAvailableMeasurement() + " on " + controller.getDateOfLastMeasurement() + ".\n");
        }
    }
}
