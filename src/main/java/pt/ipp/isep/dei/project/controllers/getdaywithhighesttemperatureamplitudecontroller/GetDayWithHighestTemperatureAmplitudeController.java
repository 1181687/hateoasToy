package pt.ipp.isep.dei.project.controllers.getdaywithhighesttemperatureamplitudecontroller;

import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/*US633: As a Regular User, I want to get the day with the highest temperature amplitude in the housegrid area in a given period.*/

public class GetDayWithHighestTemperatureAmplitudeController {

    private House house;
    private SensorTypeId sensorTypeTemperature;
    private Map<LocalDate, Double> mapOfDailyAmplitude;
    private Map<LocalDate, Double> mapResult;

    /**
     * constructor that receives a House and a SensorType
     *
     * @param houseService
     */
    public GetDayWithHighestTemperatureAmplitudeController(HouseService houseService) {
        this.house = houseService.getHouse();
        this.sensorTypeTemperature = new SensorTypeId("Temperature");
    }

    /**
     * get Daily Amplitude Map <localdate, Double> in a given interval of Localdate,
     * by given sensortype and location of the house
     *
     * @param startDate initial Localdate of the interval
     * @param endDate   final Localdate of the interval
     *                  save the results in Map<LocalDate,Double> mapOfDailyAmplitude
     */
    public void getDailyAmplitudeInIntervalInHouseArea(LocalDate startDate, LocalDate endDate) {
        this.mapOfDailyAmplitude = this.house
                .getDailyAmplitudeInIntervalInHouseArea(this.sensorTypeTemperature, this.house.getLocation(), startDate, endDate);
    }

    /**
     * uses the map Of Daily Amplitude and gets the Highest Daily Amplitude (localdate-Double)
     * if there are two Dates with equal amplitudes, it gets both.
     * <p>
     * save the results in the Map<LocalDate, Double> map Of Highest Daily Amplitude
     */
    public void getHighestDailyAmplitudeInHouseArea() {
        this.mapResult = this.house
                .getHighestDailyAmplitudeInHouseArea(this.mapOfDailyAmplitude);
    }

    /**
     * invokes getDailyAmplitudeInIntervalInHouseArea and getHighestDailyAmplitudeInHouseArea method
     *
     * @param startDate initial Localdate of the interval
     * @param endDate   final Localdate of the interval
     */
    public void getDayWithHighestTemperatureAmplitude(LocalDate startDate, LocalDate endDate) {
        this.getDailyAmplitudeInIntervalInHouseArea(startDate, endDate);
        this.getHighestDailyAmplitudeInHouseArea();
    }

    /**
     * uses the Map<LocalDate, Double> map Result and prints the highest temperature amplitude and the date(s)
     * that registered that value. If the Map is empty, it prints that there's no registers for this period.
     * If the map has a value Amplitude 0, it is because there weren't enough measuremnts to calculate the amplitude
     * so it prints that There are not enough values to calculate the amplitude.
     *
     * @return String with results
     */
    public String displayResults() {

        StringBuilder content = new StringBuilder();

        if (!this.mapResult.isEmpty()) {

            Set<Map.Entry<LocalDate, Double>> set = mapResult.entrySet();
            boolean output = false;
            for (Map.Entry<LocalDate, Double> highestAmplitude : set) {

                if (Double.compare(highestAmplitude.getValue(), 0) == 0) {

                    return "There are not enough values to calculate the amplitude.\n";
                }

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
        return "There's no registers for this period.\n";
    }
}


