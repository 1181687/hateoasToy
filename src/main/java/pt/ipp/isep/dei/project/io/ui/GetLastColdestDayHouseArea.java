package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetInfoHouseAreaController;
import pt.ipp.isep.dei.project.controllers.getlastcoldestdayhouseareacontroller.GetLastColdestDayHouseAreaController;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.readings.ReadingDTO;
import pt.ipp.isep.dei.project.services.GeoAreaAggregateService;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.util.Objects;

public class GetLastColdestDayHouseArea {

    private GetInfoHouseAreaController controller;
    private ReadingDTO readingDTO;
    private LocalDate firstDate;
    private LocalDate lastDate;

    public GetLastColdestDayHouseArea(House house, GeoAreaAggregateService geoAreaAggregateService) {
        this.controller = new GetInfoHouseAreaController(house, geoAreaAggregateService);
    }

    public void run() {
       /* if (Objects.isNull(controller.getCurrentTemperature())) {
            System.out.println("There are no " + controller.getTypeTemperature() + " sensors with valid measurements in the house area.");
        } else {

            askAndValidateFirstAndLastDate();

            //getLastColdestDayReadingAndDisplayResults();
        }*/
    }

    private void askAndValidateFirstAndLastDate() {
        boolean flag;
        do {
            flag = false;

            String label1 = "Please insert the first date of the period (yyyy-MM-dd):";
            this.firstDate = InputValidator.getStringDate(label1);

            String label2 = "Please insert the second date of the period (yyyy-MM-dd):";
            this.lastDate = InputValidator.getStringDate(label2);

            if (firstDate.isAfter(lastDate)) {
                System.out.println("That is not a valid period. Please try again.\n");
                flag = true;
            }
        }
        while (flag);
    }

   /* private void getLastColdestDayReadingAndDisplayResults() {
        if (!controller.hasReadingsBetweenDates(this.firstDate, this.lastDate)) {
            System.out.println("There are no valid readings in the nearest sensor for that period.\n");
        } else {
            this.readingDTO = controller.getLastLowestMaximumReading(this.firstDate, this.lastDate);

            if (Double.isNaN(this.readingDTO.getValue())) {
                System.out.println("The last reading of the nearest sensor is not valid.");
            } else {
                resultToString();
            }
        }
    }*/

    private void resultToString() {

        String lastColdestDay = this.readingDTO.getDateTime().toLocalDate().toString();
        double temperature = Utils.round(this.readingDTO.getValue(), 2);

        StringBuilder content = new StringBuilder();
        content.append("The last coldest day (lower maximum temperature) in the house area in the period between ");
        content.append(this.firstDate.toString());
        content.append(" and ");
        content.append(this.lastDate.toString());
        content.append(" is:\n");
        content.append(lastColdestDay);
        content.append(" with ");
        content.append(temperature);
        content.append(" Celsius.\n");

        System.out.println(content.toString());
    }
}
