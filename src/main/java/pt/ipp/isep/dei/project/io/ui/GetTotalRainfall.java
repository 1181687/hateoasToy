package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetTotalAndAverageRainfallAndCurrentTempHouseAreaController;
import pt.ipp.isep.dei.project.model.House;

import java.time.LocalDate;


/** US620 As a Regular User, I want to get the total rainfall in the house area for a given day. */
public class GetTotalRainfall {
    private GetTotalAndAverageRainfallAndCurrentTempHouseAreaController controller;

    public GetTotalRainfall(House house) {
        this.controller = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(house);
    }

    public void run() {

        String label1 = "Please insert the date when you want to get the total Rainfall (yyyy-MM-dd):";
        LocalDate dateLD = InputValidator.getStringDate(label1);

        if (!(Double.isNaN(controller.getTotalRainfallInTheHouseAreaInTheSelectedDay(dateLD)))) {
            System.out.println("The total Rainfall of this House Area is " + controller.getTotalRainfallInTheHouseAreaInTheSelectedDay(dateLD) + "l/m2");
        } else {
            System.out.println("There's no registers for this day.");
        }
    }
}