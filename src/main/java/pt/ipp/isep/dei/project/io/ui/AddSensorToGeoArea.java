package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddSensorToGeoAreaController;
import pt.ipp.isep.dei.project.services.GeoAreaService;

import java.util.Scanner;

/**
 * US006 As an Administrator, I want to add a new sensor and associate it to a geographical
 * area, so that one can get measurements of that type in that area.
 */

public class AddSensorToGeoArea {
    private AddSensorToGeoAreaController controller;

    public AddSensorToGeoArea(GeoAreaService geoAreaService) {
        this.controller = new AddSensorToGeoAreaController(geoAreaService);
    }

    public void run() {
        String label1 = "Introduce the ID of the new sensor.";
        String label2 = "Introduce the name of the new sensor.";


        String label3 = "Introduce the units of the new sensor.";
        Scanner read = new Scanner(System.in);


        String id = InputValidator.getString(label1);
        String name = InputValidator.getString(label2);
        String units = InputValidator.getString(label3);


        String label4 = "Introduce the latitude of the new sensor (valid numbers between -90 and 90).";
        double latitude = InputValidator.getDoubleRange(label4, -90, 90);

        String label5 = "Introduce the longitude of the new sensor (valid numbers between -180 and 180).";
        double longitude = InputValidator.getDoubleRange(label5, -180, 180);

        String label6 = "Introduce the altitude of the new sensor.";
        double altitude = InputValidator.getInt(label6);

        System.out.println("Introduce the type of sensor");





        /*
        int posicao1 = -1;
        do {
            for (int i = 0; i < controller.numeroElementosDaListaTipoDeSensor(); i++) {
                System.out.println((i + 1) + " - " + (controller.getNomeTipoSensorPorIndice(i)));
            }
            posicao1 = ler.nextInt();
        }
        while (posicao1 < 0 || posicao1 > controller.numeroElementosDaListaTipoDeSensor());

        ler.nextLine();
        System.out.println("In which geographical area is this sensor included?");
        int posicao2 = -1;
        do {
            for (int i = 0; i < controller.numeroElementosDaListaAreaGeografica(); i++) {
                System.out.println((i + 1) + " - " + (controller.getNomeAreaGeograficaPorIndice(i)));
            }
            posicao2 = ler.nextInt();
        }
        while (posicao2 < 0 || posicao2 > controller.numeroElementosDaListaAreaGeografica());

        controller.getAreaGeograficaNaListaPorPosicao(posicao2 - 1);
        controller.criarNovaLocalizacao(altitude, latitude, longitude);
        controller.getTipoSensorPorPosicao(posicao1 - 1);

        if (controller.adicionarSensorAAreaGeografica(controller.criarNovoSensor(id, nome, units))) {
            System.out.println("Success! A sensor was created.");
        } else {
            System.out.println("This sensor already exists in this geographical area.");
        }

         */
    }


}
