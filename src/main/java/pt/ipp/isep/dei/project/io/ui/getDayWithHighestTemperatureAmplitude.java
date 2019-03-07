package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetDayWithHighestTemperatureAmplitudeController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/*US633: As a Regular User, I want to get the day with the highest temperature amplitude in the house area in a given period.*/

public class getDayWithHighestTemperatureAmplitude {

    private GetDayWithHighestTemperatureAmplitudeController controller;

    public getDayWithHighestTemperatureAmplitude(House house) {
        this.controller = new GetDayWithHighestTemperatureAmplitudeController(house);
    }

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

        Map<LocalDate, Double> dailyAmplitudes = controller.getDailyAmplitudeInIntervalInHouseArea(firstDate, secondDate);

        if (!((controller.getHighestDailyAmplitudeInHouseArea(dailyAmplitudes).isEmpty()))) {

            Set<Map.Entry<LocalDate, Double>> set = controller.getHighestDailyAmplitudeInHouseArea(dailyAmplitudes).entrySet();

            for (Map.Entry<LocalDate, Double> dailyAmplitude : set) {

                if (dailyAmplitudes.size() < 1) {
                    System.out.println("The highest daily temperature for the chosen period is: " +
                            dailyAmplitude.getValue().toString() + " Celsius" + " and was registered on " +
                            dailyAmplitude.getKey().toString());
                } else {
                    System.out.println("The highest daily temperature for the chosen period is: " +
                            Utils.round(dailyAmplitude.getValue(), 2) + " Celsius, and was registered on these dates: " +
                            dailyAmplitude.getKey().toString());
                }
            }
        } else {
            System.out.println("There's no registers for this period.");
        }
    }
}
