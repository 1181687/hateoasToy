package pt.ipp.isep.dei.project.io.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import pt.ipp.isep.dei.project.controllers.AddGeoAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeList;

import java.util.Scanner;
/** US003 As an Administrator, I want to add a new geographical area. */

public class AddGeoArea {

    private AddGeoAreaController controller;

    @Autowired
    private GeographicalAreaTypeList list;

    @Autowired
    private GeographicalAreaList geoAreaList;

    public AddGeoArea() {
        this.controller = new AddGeoAreaController(geoAreaList, list);
    }

    public void run() {
        System.out.println("Introduce the id of the Geographical Area");
        Scanner ler = new Scanner(System.in);
        String id = ler.nextLine();
        System.out.println("Introduce the description of the Geographical Area");
        String description = ler.nextLine();
        System.out.println("Choose the Geographical Area type");
        for (int i = 1; i <= controller.getGeoAreaList().size(); i++) {
            System.out.println(i + " - " +
                    "Type: " + controller.getGeoAreaList().get(i - 1));
        }
        int opcao = ler.nextInt();
        String nomeTipoAG = controller.getGeoAreaList().get(opcao - 1);
        String label1 = "Introduce the latitude of the Geographical Area(valid numbers between -90 and 90).";
        double latitude = InputValidator.getDoubleRange(label1, -90, 90);

        String label2 = "Introduce the longitude of the Geographical Area(valid numbers between -180 and 180).";
        double longitude = InputValidator.getDoubleRange(label2, -180, 180);

        String label3 = "Introduce the altitude of the Geographical Area.";
        double elevation = InputValidator.getInt(label3);

        String label4 = "Introduce the length of the Geographical Area(valid numbers greater than 0).";
        double length = InputValidator.getDoublePos(label4);

        String label5 = "Introduce the width of the Geographical Area(valid numbers greater than 0).";
        double width = InputValidator.getDoublePos(label5);


        GeographicalArea newGeoArea = controller.createNewGeoArea(id, description, nomeTipoAG, controller.createLocation(latitude, longitude, elevation), length, width);
        if (controller.addNewGeoArea(newGeoArea)) {
            System.out.println("Success!");
        } else {
            System.out.println("That Geographical Area already exists!");
        }
    }
}

