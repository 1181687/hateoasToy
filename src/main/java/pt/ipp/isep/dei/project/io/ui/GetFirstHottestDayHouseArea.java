package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetFirstHottestDayHouseAreaController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;

public class GetFirstHottestDayHouseArea {
    private GetFirstHottestDayHouseAreaController ctrl;

    public GetFirstHottestDayHouseArea(House house) {
        this.ctrl = new GetFirstHottestDayHouseAreaController(house);
    }

    public void run() {
        boolean flag;
        LocalDate initialDate;
        LocalDate finalDate;
        do {
            flag = false;
            String label0 = "Please insert the initial date of the period you want to consider for the calculations in the following format: yyyy-MM-dd. ";
            initialDate = InputValidator.getStringDate(label0);
            String label1 = "Please insert the final date of the period you want to consider for the calculations in the following format: yyyy-MM-dd. ";
            finalDate = InputValidator.getStringDate(label1);
            if (initialDate.isAfter(finalDate)) {
                System.out.println("That is not a valid period (final date should be after initial date). Please try again.\n");
                flag = true;
            }
        } while (flag);
        if (Double.isNaN(ctrl.getHighestReadingOfASensor(initialDate, finalDate)) || (!(ctrl.checkMeasurementExistenceBetweenDates(ctrl.getHouseLocation(), initialDate, finalDate)))) {
            System.out.println("There are no " + ctrl.getTypeTemperature() + " sensors with valid measurements in the house area.");
            return;
        } else {
            System.out.println("The first hottest day in the house area in the chosen interval is " +
                    ctrl.getFirstHighestReadingDateHouseArea(ctrl.getHouseLocation(), ctrl.getTypeTemperature(), initialDate, finalDate) +
                    " (maximum temperature of " +
                    Utils.round(ctrl.getFirstHighestReadingValueHouseArea(ctrl.getHouseLocation(), ctrl.getTypeTemperature(), initialDate, finalDate), 2) + " Celsius).\n");
        }
    }
}

