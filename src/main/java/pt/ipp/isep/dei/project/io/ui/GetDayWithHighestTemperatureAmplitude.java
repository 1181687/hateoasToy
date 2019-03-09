package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetDayWithHighestTemperatureAmplitudeController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/*US633: As a Regular User, I want to get the day with the highest temperature amplitude in the house area in a given period.*/

public class GetDayWithHighestTemperatureAmplitude {

    private GetDayWithHighestTemperatureAmplitudeController controller;

    /**
     * constructor that receives a House
     *
     * @param house House
     */
    public GetDayWithHighestTemperatureAmplitude(House house) {
        this.controller = new GetDayWithHighestTemperatureAmplitudeController(house);
    }

    /**
     * method that gets the highest temperature amplitude in the house area in a given period
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

        Map<LocalDate, Double> dailyAmplitudes = controller.getDailyAmplitudeInIntervalInHouseArea(firstDate, secondDate);

        Map<LocalDate, Double> dailyHighestAmplitude = controller.getHighestDailyAmplitudeInHouseArea(dailyAmplitudes);

        displayResults(dailyHighestAmplitude);
        System.out.println("\n");
    }

    /**
     * receives a Map<LocalDate, Double> and prints the highest temperature amplitude and the date(s)
     * that registered that value. If the Map is empty, it prints that there's no registers for this period.
     *
     * @param dailyHighestAmplitude Map<LocalDate, Double> highest temperature amplitude and date.
     */
    private void displayResults(Map<LocalDate, Double> dailyHighestAmplitude) {

        StringBuilder content = new StringBuilder();

        if (!dailyHighestAmplitude.isEmpty()) {

            Set<Map.Entry<LocalDate, Double>> set = dailyHighestAmplitude.entrySet();
            boolean output = false;
            for (Map.Entry<LocalDate, Double> highestAmplitude : set) {

                if (!output) {
                    output = true;
                    content.append("The highest temperature amplitude for the chosen period is ");
                    content.append(Utils.round(highestAmplitude.getValue(), 2));
                    content.append(" Celsius");
                    content.append(" and was registered on: ");
                    System.out.println(content.toString());
                }
                System.out.println(highestAmplitude.getKey().toString());
            }
        } else {
            System.out.println("There's no registers for this period.");
        }
    }
}
