package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.ConfHouseLocationController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.house.House;

import java.util.Scanner;

public class ConfHouseLocation {

    /**
     * US101 As an Administrator, I want to configure the location of the housegrid.
     */
    private ConfHouseLocationController controller;
    private GeographicalAreaDTO geographicalAreaDTO;

    public ConfHouseLocation(GeographicalAreaList geographicalAreaList, House house) {
        this.controller = new ConfHouseLocationController(geographicalAreaList, house);
    }


    public void run() {
        System.out.println("Introduce the zip code of the new location.");
        Scanner ler = new Scanner(System.in);
        String zipCode = ler.nextLine();
        String label1 = "Introduce the latitude of the new location (valid numbers between -90 and 90).";
        double latitude = InputValidator.getDoubleRange(label1, -90, 90);

        String label2 = "Introduce the longitude of the new location (valid numbers between -180 and 180).";
        double longitude = InputValidator.getDoubleRange(label2, -180, 180);

        String label3 = "Introduce the altitude of the new location.";
        double altitude = InputValidator.getInt(label3);

        String label4 = "Choose the number that corresponds to the geographical area you wish to include in another geographical area.";
        int positionChosenGeoArea = InputValidator.getIntRange(label4
                + "\n" + controller.getGeoAreaListToString(true), 1, controller.getNumberOfGeoAreas());
        controller.setGeographicalArea(positionChosenGeoArea);

        controller.defineNewAddress(zipCode, latitude, longitude, altitude);
        controller.setAddress();
        System.out.println("\nSuccess!\n");
    }
}