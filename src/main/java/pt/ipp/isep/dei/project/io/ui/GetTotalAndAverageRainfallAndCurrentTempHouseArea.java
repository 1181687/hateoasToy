package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetTotalAndAverageRainfallAndCurrentTempHouseAreaController;
import pt.ipp.isep.dei.project.model.House;

import java.time.LocalDate;


/** US620 As a Regular User, I want to get the total rainfall in the house area for a given day.

US623 As a Regular User, I want to get the average daily rainfall in the house area for a
given period (days), as it is needed to assess the garden’s watering needs.

US600 As a Regular User, I want to get the current temperature in the house area. If, in the
first element with temperature sensors of the hierarchy of geographical areas that
includes the house, there is more than one temperature sensor, the nearest one
should be used.
 */
public class GetTotalAndAverageRainfallAndCurrentTempHouseArea {
    private GetTotalAndAverageRainfallAndCurrentTempHouseAreaController mCtrl;

    public GetTotalAndAverageRainfallAndCurrentTempHouseArea(House house) {
        this.mCtrl = new GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(house);
    }

    public void run() {

        String label1 = "Please insert the date when you want to get the total Rainfall (yyyy-MM-dd):";
        LocalDate dateLD = InputValidator.getStringDate(label1);

        if (!(Double.isNaN(mCtrl.getTotalRainfallInTheHouseAreaInTheSelectedDay(dateLD)))) {
            System.out.println("The total Rainfall of this House Area is " + mCtrl.getTotalRainfallInTheHouseAreaInTheSelectedDay(dateLD) + "l/m2");
        } else {
            System.out.println("There's no registers for this day.");
        }
    }

    public void run2() {

        String label1 = "Please insert the first date of the period (yyyy-MM-dd):";
        LocalDate firstDate = InputValidator.getStringDate(label1);

        String label2 = "Please insert the second date of the period (yyyy-MM-dd):";
        LocalDate secondDate = InputValidator.getStringDate(label2);

        if (firstDate.isAfter(secondDate)) {
            System.out.println("That is not a valid period. Please try again.\n");
            return;
        }


        if (!(Double.isNaN(mCtrl.getAverageDailyRainfall(firstDate, secondDate)))) {
            System.out.println("The average daily rainfall for the chosen period is: " + mCtrl.getAverageDailyRainfall(firstDate, secondDate) + "l/m2");
        } else {
            System.out.println("There's no registers for this period.");
        }
    }

    public void run3() {
        if (Double.isNaN(mCtrl.getMostRecentAvailableMeasurement())) {
            System.out.println("There are no " + mCtrl.getTypeTemperature() + " sensors with valid measurements in the house area.");
            return;
        } else {
            System.out.println("The most recent measurement available in the house area for "
                    + mCtrl.getTypeTemperature() + " is " + mCtrl.getMostRecentAvailableMeasurement() + ".");
        }
    }
}