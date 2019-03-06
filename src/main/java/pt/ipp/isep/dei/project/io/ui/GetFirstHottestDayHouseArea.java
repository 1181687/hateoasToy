package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetFirstHottestDayHouseAreaController;
import pt.ipp.isep.dei.project.model.House;

import java.time.LocalDate;

public class GetFirstHottestDayHouseArea {
    private GetFirstHottestDayHouseAreaController ctrl;

    private GetFirstHottestDayHouseArea(House house) {
        this.ctrl = new GetFirstHottestDayHouseAreaController(house);
    }

    public void run() {
        String label0 = "Please insert the initial date/hour of the period you want to consider for the calculations in the following format: yyyy-MM-dd HH:mm. ";
        LocalDate initialDate = InputValidator.getStringDate(label0);
        String label1 = "Please insert the final date/hour of the period you want to consider for the calculations in the following format: yyyy-MM-dd HH:mm. ";
        LocalDate finalDate = InputValidator.getStringDate(label1);
        System.out.println("The the first hottest day in the house area in the chosen interval is " +
                ctrl.getFirstHighestReadingDateHouseArea(ctrl.getHouseLocation(), ctrl.getTypeTemperature(), initialDate, finalDate));
    }
}

