package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.getFirstHottestDayHouseAreaController.GetFirstHottestDayHouseAreaController;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.utils.Utils;

import java.time.LocalDate;

public class GetFirstHottestDayHouseArea {
    private GetFirstHottestDayHouseAreaController ctrl;

    public GetFirstHottestDayHouseArea(House house) {
        this.ctrl = new GetFirstHottestDayHouseAreaController(house);
    }

    public void run() {
        if (ctrl.isSensorListOfATypeEmpty()) {
            System.out.println("There are no temperature sensors in the house area.\n");
            return;
        }
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
        if (!ctrl.checkNearestSensorReadingsExistenceBetweenDates(initialDate, finalDate) ||
                Double.isNaN(ctrl.getFirstHighestReadingHouseArea(initialDate, finalDate).getValue())) {
            System.out.println("It is not possible to present valid readings in the house area.\n");
            return;
        }
        System.out.println("The first hottest day in the house area in the chosen interval is " +
                ctrl.getFirstHighestReadingHouseArea(initialDate, finalDate).getDateTime().toLocalDate() +
                " (maximum temperature of " +
                Utils.round(ctrl.getFirstHighestReadingHouseArea(initialDate, finalDate).getValue(), 2) + " Celsius).\n");
    }
}