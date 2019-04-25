package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetInfoHouseAreaController;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.services.GeoAreaAggregateService;

import java.time.LocalDate;

public class GetTotalRainfall {
    private GetInfoHouseAreaController controller;

    /**
     * Constructor.
     *
     * @param house                   House to be used.
     * @param geoAreaAggregateService Service to be used.
     */
    public GetTotalRainfall(House house, GeoAreaAggregateService geoAreaAggregateService) {
        this.controller = new GetInfoHouseAreaController(house, geoAreaAggregateService);
    }

    /**
     * RUN!
     */
    public void run() {
        String label = "Please insert the date when you want to get the total rainfall (yyyy-MM-dd):";
        LocalDate date = InputValidator.getStringDate(label);
        double value = controller.getTotalRainfall(date).getValue();
        if (!(Double.isNaN(value))) {
            System.out.println("The total rainfall in the house Area in" + date + " is " + value + "l/m2");
        } else {
            System.out.println("There's no registers for this day.");
        }
    }
}