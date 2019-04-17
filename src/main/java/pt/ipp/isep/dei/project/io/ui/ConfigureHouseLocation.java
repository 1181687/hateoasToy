package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.ConfigureHouseLocationController;
import pt.ipp.isep.dei.project.services.HouseService;


public class ConfigureHouseLocation {

    /**
     * US101 As an Administrator, I want to configure the location of the house
     */


    private ConfigureHouseLocationController controller;

    public ConfigureHouseLocation(HouseService houseService) {
        this.controller = new ConfigureHouseLocationController(houseService);
    }

}

