package pt.ipp.isep.dei.project.controllers.getLastColdestDayHouseAreaController;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.house.House;

import java.time.LocalDate;

public class GetLastColdestDayHouseAreaController {
    private House house;

    public GetLastColdestDayHouseAreaController(House house) {
        this.house = house;
    }

    public boolean checkMeasurementExistenceBetweenDates(LocalDate startDate, LocalDate endDate){
        return house.checkMeasurementExistenceBetweenDates(this.house.getLocation(),startDate,endDate);
    }
}
