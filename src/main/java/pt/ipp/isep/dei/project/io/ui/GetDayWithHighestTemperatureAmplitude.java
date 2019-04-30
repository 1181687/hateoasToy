package pt.ipp.isep.dei.project.io.ui;


import pt.ipp.isep.dei.project.controllers.getdaywithhighesttemperatureamplitudecontroller.GetDayWithHighestTemperatureAmplitudeController;
import pt.ipp.isep.dei.project.services.HouseService;

import java.time.LocalDate;

/*US633: As a Regular User, I want to get the day with the highest temperature amplitude in the housegrid area in a given period.*/

public class GetDayWithHighestTemperatureAmplitude {

    private GetDayWithHighestTemperatureAmplitudeController controller;

    /**
     * constructor that receives a HouseService
     *
     * @param houseService The HouseService
     */
    public GetDayWithHighestTemperatureAmplitude(HouseService houseService) {
        this.controller = new GetDayWithHighestTemperatureAmplitudeController(houseService);
    }

    /**
     * method that gets the highest temperature amplitude in the housegrid area in a given period
     * prints the highest temperature amplitude and the date(s)
     * that registered that value. If the Map is empty, it prints that there's no registers for this period.
     */
    public void run() {

        LocalDate firstDate;
        LocalDate secondDate;
        boolean flag;

        do {
            flag = false;

            String label1 = "Please insert the first date of the period (yyyy-MM-dd):";
            firstDate = InputValidator.getStringDate(label1);

            String label2 = "Please insert the second date of the period (yyyy-MM-dd):";
            secondDate = InputValidator.getStringDate(label2);

            if (firstDate.isAfter(secondDate)) {
                System.out.println("That is not a valid period. Please try again.\n");
                flag = true;
            }
        }
        while (flag);

        controller.getDayWithHighestTemperatureAmplitude(firstDate, secondDate);

        System.out.println(controller.displayResults() + "\n");
    }
}
