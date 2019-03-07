package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.getDayWithHighestTemperatureAmplitudeController;
import pt.ipp.isep.dei.project.model.House;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class getDayWithHighestTemperatureAmplitude {

    private getDayWithHighestTemperatureAmplitudeController controller;


    public getDayWithHighestTemperatureAmplitude(House house) {
        this.controller = new getDayWithHighestTemperatureAmplitudeController(house);
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

        Map<LocalDate, Double> dailyAmplitudes = controller.getDailyAmplitudeInIntervalInHouseArea(firstDate, secondDate);

        if (!((controller.getHighestDailyAmplitudeInHouseArea(dailyAmplitudes).isEmpty()))) {

            Set<Map.Entry<LocalDate, Double>> set = dailyAmplitudes.entrySet();

            for (Map.Entry<LocalDate, Double> dailyAmplitude : set) {

                if (dailyAmplitudes.size() < 1) {
                    System.out.println("The highest daily temperature for the chosen period is: " +
                            dailyAmplitude.getValue().toString() + " Celsius" + " and was registered on " +
                            dailyAmplitude.getKey().toString());
                } else {
                    System.out.println("The highest daily temperature for the chosen period is: " +
                            dailyAmplitude.getValue().toString() + " Celsius, and was registered on these dates: " +
                            dailyAmplitude.getKey().toString());
                }
            }
        } else {
            System.out.println("There's no registers for this period.");
        }
    }

}
