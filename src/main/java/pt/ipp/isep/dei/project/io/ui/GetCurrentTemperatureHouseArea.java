package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetTotalAndAverageRainfallAndCurrentTempHouseAreaController;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;

import java.util.Objects;

public class GetCurrentTemperatureHouseArea {
    private GetTotalAndAverageRainfallAndCurrentTempHouseAreaController controller;

    /**
     * Constructor.
     *
     * @param house                House to be used.
     * @param geoAreaSensorService Service to be used.
     */
    public GetCurrentTemperatureHouseArea(House house, GeoAreaSensorService geoAreaSensorService) {
        this.controller = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(house, geoAreaSensorService);
    }

    /**
     * RUN!
     */
    public void run() {
        if (Objects.isNull(controller.getCurrentTemperature())) {
            System.out.println("There are no " + controller.getTypeTemperature() + " sensors with valid measurements in the house area.");
        } else {
            System.out.println("The most recent [valid] reading available in the house area for "
                    + controller.getTypeTemperature() + " is " + controller.getCurrentTemperature().keySet() + " on "
                    + controller.getCurrentTemperature().values() + ".\n");
        }
    }
}
