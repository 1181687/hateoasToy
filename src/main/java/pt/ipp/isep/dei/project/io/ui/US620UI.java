package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US620Controller;
import pt.ipp.isep.dei.project.model.House;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class US620UI {
    private US620Controller mCtrl;

    public US620UI(House house) {
        this.mCtrl = new US620Controller(house);
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
        Date date = mCtrl.createANewDate(year, month, day);

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
        Date date1 = mCtrl.createANewDate(year1, month1, day1);
        Date date2 = mCtrl.createANewDate(year2, month2, day2);


        if (!(Double.isNaN(mCtrl.getAverageDailyRainfallInTheHouseAreaInTheSelectedPeriod(date1, date2)))) {
            System.out.println("The average daily rainfall for the chosen period is: " + mCtrl.getAverageDailyRainfallInTheHouseAreaInTheSelectedPeriod(date1, date2));
        } else {
            System.out.println("There's no registers for this period.");
        }
    }


}

