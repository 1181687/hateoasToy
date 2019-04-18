package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetInfoHouseAreaController;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.services.GeoAreaAggregateService;

import java.util.Objects;

public class GetCurrentTemperatureHouseArea {
    private GetInfoHouseAreaController controller;

    /**
     * Constructor.
     *
     * @param house                House to be used.
     */
    public GetCurrentTemperatureHouseArea(House house, GeoAreaAggregateService geoAreaAggregateService) {
        this.controller = new GetInfoHouseAreaController(house, geoAreaAggregateService);
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
