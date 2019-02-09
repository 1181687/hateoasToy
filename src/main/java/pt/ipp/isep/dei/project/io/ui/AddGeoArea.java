package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddGeoAreaController;
import pt.ipp.isep.dei.project.model.GeographicalArea;
import pt.ipp.isep.dei.project.model.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.GeographicalAreaTypeList;

import java.util.Scanner;
/** US003 As an Administrator, I want to add a new geographical area. */

public class AddGeoArea {

    private AddGeoAreaController ctrl3;

    public AddGeoArea(GeographicalAreaList lista, GeographicalAreaTypeList listaTAG) {
        this.ctrl3 = new AddGeoAreaController(lista, listaTAG);
    }

    public void run() {
        System.out.println("Introduce the name of the Geographical Area");
        Scanner ler = new Scanner(System.in);
        String nome = ler.nextLine();
        System.out.println("Choose the Geographical Area type");
        for (int i = 1; i <= ctrl3.getTGAList().size(); i++) {
            System.out.println(i + " - " +
                    "Type: " + ctrl3.getTGAList().get(i - 1));
        }
        int opcao = ler.nextInt();
        String nomeTipoAG = ctrl3.getTGAList().get(opcao - 1);
        String label1 = "Introduce the latitude of the Geographical Area(valid numbers between -90 and 90).";
        double latitude = InputValidator.getDoubleRange(label1, -90, 90);

        String label2 = "Introduce the longitude of the Geographical Area(valid numbers between -180 and 180).";
        double longitude = InputValidator.getDoubleRange(label2, -180, 180);

        String label3 = "Introduce the altitude of the Geographical Area.";
        double altitude = InputValidator.getInt(label3);

        String label4 = "Introduce the length of the Geographical Area(valid numbers greater than 0).";
        double comprimento = InputValidator.getDoublePos(label4);

        String label5 = "Introduce the width of the Geographical Area(valid numbers greater than 0).";
        double largura = InputValidator.getDoublePos(label5);

        GeographicalArea novaAG = ctrl3.createNewGeoArea(nome, nomeTipoAG, latitude, longitude, altitude, comprimento, largura);
        if (ctrl3.addNewGeoArea(novaAG)) {
            System.out.println("Success!");
        } else {
            System.out.println("That Geographical Area already exists!");
        }
    }
}

