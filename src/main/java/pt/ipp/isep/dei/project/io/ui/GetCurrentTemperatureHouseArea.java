package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetInfoHouseAreaController;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.readings.ReadingDTO;
import pt.ipp.isep.dei.project.services.GeoAreaAggregateService;

import java.util.Objects;

public class GetCurrentTemperatureHouseArea {
    private GetInfoHouseAreaController controller;

    /**
     * Constructor.
     *
     * @param house                   House to be used.
     * @param geoAreaAggregateService Service to be used.
     */
    public GetCurrentTemperatureHouseArea(House house, GeoAreaAggregateService geoAreaAggregateService) {
        this.controller = new GetInfoHouseAreaController(house, geoAreaAggregateService);
    }

    /**
     * RUN!
     */
    public void run() {
        ReadingDTO readingDTO = controller.getCurrentTemperature();
        if (Objects.isNull(controller.getCurrentTemperature())) {
            System.out.println("There are no temperature sensors with valid measurements in the house area.");
        } else {
            System.out.println("The most recent [valid] reading available in the house area for temperature is " + readingDTO.getValue() + " on "
                    + readingDTO.getDateTime() + ".\n");
        }
    }
}
