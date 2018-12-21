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
        System.out.println("Introduce the latitude of the new location (valid numbers between -90 and 90).");
        double latitude = ler.nextDouble();
        while (latitude < -90 || latitude > 90) {
            System.out.println("That value is out of bounds (-90 e 90). Introduce a new value of latitude).");
            latitude = ler.nextDouble();
        }

        System.out.println("Introduce the longitude of the new location (valid numbers between -180 and 180).");
        double longitude = ler.nextDouble();
        while (longitude < -180 || longitude > 180) {
            System.out.println("That value is out of bounds (-180 e 180). Introduce a new value of longitude).");
            longitude = ler.nextDouble();
        }

        System.out.println("Introduce the altitude of the new location.");
        Double altitude = ler.nextDouble();

        ctrl.defineNewAddress(zipCode, latitude, longitude, altitude);
        ctrl.setAddressToTheHouse();
        System.out.println("Success!");
    }
}