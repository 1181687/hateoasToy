package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetInfoHouseAreaController;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.services.GeoAreaAggregateService;

import java.time.LocalDate;


/**
 * US623 As a Regular User, I want to get the average daily rainfall in the housegrid area for a
 * given period (days), as it is needed to assess the garden’s watering needs.
 */

public class GetAverageRainfall {
    private GetInfoHouseAreaController controller;

    /**
     * Constructor.
     *
     * @param house                   House to be used.
     * @param geoAreaAggregateService Service to be used.
     */
    public GetAverageRainfall(House house, GeoAreaAggregateService geoAreaAggregateService) {
        this.controller = new GetInfoHouseAreaController(house, geoAreaAggregateService);
    }

    /**
     * RUN!
     */
    public void run() {
        String label1 = "Please insert the first date of the period (yyyy-MM-dd):";
        LocalDate firstDate = InputValidator.getStringDate(label1);
        String label2 = "Please insert the second date of the period (yyyy-MM-dd):";
        LocalDate secondDate = InputValidator.getStringDate(label2);
        if (firstDate.isAfter(secondDate)) {
            System.out.println("That is not a valid period. Please try again.\n");
            return;
        }
        Double value = controller.getAverageDailyRainfall(firstDate, secondDate);
        if (!(Double.isNaN(value))) {
            System.out.println("The average daily rainfall for the chosen period is: " + value + " l/m2");
        } else {
            System.out.println("There's no registers for this period.");
        }
    }
}
