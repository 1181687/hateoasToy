package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US620Controller;
import pt.ipp.isep.dei.project.model.House;

import java.time.LocalDate;
import java.util.Scanner;

public class US620UI {
    private US620Controller mCtrl;

    public US620UI(House house) {
        this.mCtrl = new US620Controller(house);
    }

    public void run() {
        String label1 = "Please insert the year:";
        int year = InputValidator.getIntRange(label1, 2000, 2019);

        String label2 = "Please insert the month:";
        int month = InputValidator.getIntRange(label2, 1, 12);

        String label3 = "Please insert the day:";
        int day = InputValidator.getIntRange(label3, 1, 31);

        if (!(Double.isNaN(mCtrl.getTotalRainfallInTheHouseAreaInTheSelectedDay(year, month, day)))) {
            System.out.println("The total Rainfall of this House Area is " + mCtrl.getTotalRainfallInTheHouseAreaInTheSelectedDay(year, month, day));
        } else {
            System.out.println("There's no registers for this day.");
        }
    }

    public void run2() {
        //input of the start date
        String label1 = "Please insert the year of the first date of the period:";
        int year1 = InputValidator.getIntRange(label1, 2000, 2019);

        String label2 = "Please insert the month of the first date of the period:";
        int month1 = InputValidator.getIntRange(label2, 1, 12);

        String label3 = "Please insert the day of the first date of the period:";
        int day1 = InputValidator.getIntRange(label3, 1, 31);

        //input of the ending date
        String label4 = "Please insert the year of the last date of the period:";
        int year2 = InputValidator.getIntRange(label4, 2000, 2019);

        String label5 = "Please insert the month of the last date of the period:";
        int month2 = InputValidator.getIntRange(label5, 1, 12);

        String label6 = "Please insert the day of the last date of the period:";
        int day2 = InputValidator.getIntRange(label6, 1, 31);

        if (!(Double.isNaN(mCtrl.getAverageDailyRainfallInTheHouseAreaInTheSelectedPeriod(year1, month1, day1, year2, month2, day2)))) {
            System.out.println("The average daily rainfall for the chosen period is: " + mCtrl.getAverageDailyRainfallInTheHouseAreaInTheSelectedPeriod(year1, month1, day1, year2, month2, day2));
        } else {
            System.out.println("There's no registers for this period.");
        }
    }


}

