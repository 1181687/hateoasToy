package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US101Controller;
import pt.ipp.isep.dei.project.model.House;

import java.util.Scanner;

public class US101UI {

    private US101Controller ctrl;

    public US101UI(House house) {
        this.ctrl = new US101Controller(house);
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

        ctrl.defineNewAddress(zipCode, latitude, longitude, altitude);
        ctrl.setAddressToTheHouse();
        System.out.println("Success!");
    }
}