package pt.ipp.isep.dei.project.controllers.getDayWithHighestTemperatureAmplitudeController;

import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/*US633: As a Regular User, I want to get the day with the highest temperature amplitude in the housegrid area in a given period.*/

public class GetDayWithHighestTemperatureAmplitudeController {

    private House house;
    private SensorType sensorTypeTemperature;
    private Map<LocalDate, Double> mapOfDailyAmplitude;
    private Map<LocalDate, Double> mapResult;

    /**
     * constructor that receives a House and a SensorType
     *
     * @param house
     */
    public GetDayWithHighestTemperatureAmplitudeController(House house) {
        this.house = house;
        this.sensorTypeTemperature = new SensorType("temperature");
    }

    public void getDailyAmplitudeInIntervalInHouseArea(LocalDate startDate, LocalDate endDate) {
        this.mapOfDailyAmplitude = this.house
                .getDailyAmplitudeInIntervalInHouseArea(this.sensorTypeTemperature, this.house.getLocation(), startDate, endDate);
    }

    public void getHighestDailyAmplitudeInHouseArea() {
        this.mapResult = this.house
                .getHighestDailyAmplitudeInHouseArea(this.mapOfDailyAmplitude);
    }

    public void getDayWithHighestTemperatureAmplitude(LocalDate startDate, LocalDate endDate) {
        this.getDailyAmplitudeInIntervalInHouseArea(startDate, endDate);
        this.getHighestDailyAmplitudeInHouseArea();
    }

    /**
     * uses the Map<LocalDate, Double> map Result and prints the highest temperature amplitude and the date(s)
     * that registered that value. If the Map is empty, it prints that there's no registers for this period.
     *
     * @return String with results
     */
    public String displayResults() {

        StringBuilder content = new StringBuilder();

        if (!this.mapResult.isEmpty()) {

            Set<Map.Entry<LocalDate, Double>> set = mapResult.entrySet();
            boolean output = false;
            for (Map.Entry<LocalDate, Double> highestAmplitude : set) {

                if (!output) {
                    output = true;
                    content.append("The highest temperature amplitude for the chosen period is ");
                    content.append(Utils.round(highestAmplitude.getValue(), 2));
                    content.append(" Celsius");
                    content.append(" and was registered on:");
                    content.append("\n");
                }
                content.append(highestAmplitude.getKey());
                content.append("\n");
            }
            return content.toString();
        }
        return "There's no registers for this period.";
    }
}
