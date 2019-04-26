package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.ConfHouseLocationController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaService;
import pt.ipp.isep.dei.project.model.house.House;

import java.util.Scanner;

public class ConfHouseLocation {

    /**
     * US101 As an Administrator, I want to configure the location of the house.
     */
    private ConfHouseLocationController controller;

    public ConfHouseLocation(GeographicalAreaService geographicalAreaService, House house) {
        this.controller = new ConfHouseLocationController(geographicalAreaService, house);
    }


    public void run() {
        if (controller.isGeographicalAreaListEmpty()) {
            System.out.println("\nThere's no geographical areas in the system. Please create or import some.\n");
            return;
        }
        System.out.println("Introduce the zip code of the new location.");
        Scanner ler = new Scanner(System.in);
        String zipCode = ler.nextLine();
        String label1 = "Introduce the latitude of the new location (valid numbers between -90 and 90).";
        double latitude = InputValidator.getDoubleRange(label1, -90, 90);

        String label2 = "Introduce the longitude of the new location (valid numbers between -180 and 180).";
        double longitude = InputValidator.getDoubleRange(label2, -180, 180);

        String label3 = "Introduce the altitude of the new location.";
        double altitude = InputValidator.getInt(label3);

        boolean flag = true;
        do {
            String label4 = "Choose the number that corresponds to the geographical area you wish to include the house in.";
            String list = controller.getGeoAreaListToString(true);
            int positionChosenGeoArea = InputValidator.getIntRange(label4
                    + "\n" + list, 1, controller.getNumberOfGeoAreas()) - 1;
            controller.setGeographicalArea(positionChosenGeoArea);

            if (!controller.isHouseAlreadyInGeoArea()) {
                controller.defineNewAddress(zipCode, latitude, longitude, altitude);
                controller.setAddress();
                flag = false;
            } else {
                System.out.println("\nThe house is already in the chosen geographical area. Please choose another one.\n");
                continue;
            }
        } while (flag);
        System.out.println("\nSuccess!\n");
    }
}