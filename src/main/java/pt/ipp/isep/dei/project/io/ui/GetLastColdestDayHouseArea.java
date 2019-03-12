package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.getLastColdestDayHouseAreaController.GetLastColdestDayHouseAreaController;
import pt.ipp.isep.dei.project.model.house.House;

public class GetLastColdestDayHouseArea {

    private GetLastColdestDayHouseAreaController controller;

    public GetLastColdestDayHouseArea(House house) {
        this.controller = new GetLastColdestDayHouseAreaController(house);
    }
}
