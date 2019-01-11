package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetTotalAndAverageRainfallAndCurrentTempHouseAreaController;
import pt.ipp.isep.dei.project.model.House;

import java.time.DateTimeException;
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

        int year = -1, month = -1, day = -1;
        boolean flag;
        do {
            try {
                flag = false;

                String label1 = "Please insert the year:";
                year = InputValidator.getIntRange(label1, 2000, 2019);

                String label2 = "Please insert the month:";
                month = InputValidator.getIntRange(label2, 1, 12);

                String label3 = "Please insert the day:";
                day = InputValidator.getIntRange(label3, 1, 31);

                mCtrl.createANewDate(year, month, day);
            } catch (DateTimeException e) {
                System.out.println(e.getMessage());
                flag = true;
            }
        } while (flag);
        LocalDate date = mCtrl.createANewDate(year, month, day);

        if (!(Double.isNaN(mCtrl.getTotalRainfallInTheHouseAreaInTheSelectedDay(date)))) {
            System.out.println("The total Rainfall of this House Area is " + mCtrl.getTotalRainfallInTheHouseAreaInTheSelectedDay(date));
        } else {
            System.out.println("There's no registers for this day.");
        }
    }

    public void run2() {

        int year1 = -1, month1 = -1, day1 = -1, year2 = -1, month2 = -1, day2 = -1;
        boolean flag;
        do {
            try {
                flag = false;

                //input of the start date
                String label1 = "Please insert the year of the first date of the period:";
                year1 = InputValidator.getIntRange(label1, 2000, 2019);

                String label2 = "Please insert the month of the first date of the period:";
                month1 = InputValidator.getIntRange(label2, 1, 12);

                String label3 = "Please insert the day of the first date of the period:";
                day1 = InputValidator.getIntRange(label3, 1, 31);

                mCtrl.createANewDate(year1, month1, day1);

                //input of the ending date
                String label4 = "Please insert the year of the last date of the period:";
                year2 = InputValidator.getIntRange(label4, 2000, 2019);

                String label5 = "Please insert the month of the last date of the period:";
                month2 = InputValidator.getIntRange(label5, 1, 12);

                String label6 = "Please insert the day of the last date of the period:";
                day2 = InputValidator.getIntRange(label6, 1, 31);

                mCtrl.createANewDate(year2, month2, day2);

            } catch (DateTimeException e) {
                System.out.println(e.getMessage());
                flag = true;
            }
        } while (flag);
        LocalDate date1 = mCtrl.createANewDate(year1, month1, day1);
        LocalDate date2 = mCtrl.createANewDate(year2, month2, day2);

        if (date1.isAfter(date2)) {
            System.out.println("That is not a valid period. Please try again.\n");
            return;
        }


        if (!(Double.isNaN(mCtrl.getAverageDailyRainfallInTheHouseAreaInTheSelectedPeriod(date1, date2)))) {
            System.out.println("The average daily rainfall for the chosen period is: " + mCtrl.getAverageDailyRainfallInTheHouseAreaInTheSelectedPeriod(date1, date2));
        } else {
            System.out.println("There's no registers for this period.");
        }
    }

    public void run3() {
        if (Double.isNaN(mCtrl.getMostRecentAvailableMeasurementInTheHouseArea())) {
            System.out.println("There are no " + mCtrl.getmTypeTemperature() + " sensors with valid measurements in the house area.");
            return;
        } else {
            System.out.println("The most recent measurement available in the house area for "
                    + mCtrl.getmTypeTemperature() + " is " + mCtrl.getMostRecentAvailableMeasurementInTheHouseArea() + ".");
        }
    }
}