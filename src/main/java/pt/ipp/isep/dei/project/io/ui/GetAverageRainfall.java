package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetTotalAndAverageRainfallAndCurrentTempHouseAreaController;
import pt.ipp.isep.dei.project.model.house.House;

import java.time.LocalDate;


/**
 * US623 As a Regular User, I want to get the average daily rainfall in the housegrid area for a
 * given period (days), as it is needed to assess the garden’s watering needs.
 */

public class GetAverageRainfall {
    private GetTotalAndAverageRainfallAndCurrentTempHouseAreaController controller;

    public GetAverageRainfall(House house) {
        this.controller = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(house);
    }

    public void run() {

        String label1 = "Please insert the first date of the period (yyyy-MM-dd):";
        LocalDate firstDate = InputValidator.getStringDate(label1);

        String label2 = "Please insert the second date of the period (yyyy-MM-dd):";
        LocalDate secondDate = InputValidator.getStringDate(label2);

        if (firstDate.isAfter(secondDate)) {
            System.out.println("That is not a valid period. Please try again.\n");
            return;
        }


        if (!(Double.isNaN(controller.getAverageDailyRainfall(firstDate, secondDate)))) {
            System.out.println("The average daily rainfall for the chosen period is: " + controller.getAverageDailyRainfall(firstDate, secondDate) + " l/m2");
        } else {
            System.out.println("There's no registers for this period.");
        }
    }
}
