package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddSensorToGeoAreaController;
import pt.ipp.isep.dei.project.model.GeoAreaList;
import pt.ipp.isep.dei.project.model.SensorTypeList;

import java.util.Scanner;

/** US006 As an Administrator, I want to add a new sensor and associate it to a geographical
area, so that one can get measurements of that type in that area. */

public class AddSensorToGeoArea {
    private AddSensorToGeoAreaController controller6;

    public AddSensorToGeoArea(GeoAreaList geoAreaList, SensorTypeList sensorTypeList) {
        this.controller6 = new AddSensorToGeoAreaController(sensorTypeList, geoAreaList);
    }

    public void run() {
        String label = "Introduce the name of the new sensor.";
        Scanner ler = new Scanner(System.in);
        String nome = InputValidator.getString(label);

        String label1 = "Introduce the latitude of the new sensor (valid numbers between -90 and 90).";
        double latitude = InputValidator.getDoubleRange(label1, -90, 90);

        String label2 = "Introduce the longitude of the new sensor (valid numbers between -180 and 180).";
        double longitude = InputValidator.getDoubleRange(label2, -180, 180);

        String label3 = "Introduce the altitude of the new sensor.";
        double altitude = InputValidator.getInt(label3);

        System.out.println("Introduce the type of sensor");
        int posicao1 = -1;
        do {
            for (int i = 0; i < controller6.numeroElementosDaListaTipoDeSensor(); i++) {
                System.out.println((i + 1) + " - " +(controller6.getNomeTipoSensorPorIndice(i)));
            }
            posicao1 = ler.nextInt();
        }
        while (posicao1 < 0 || posicao1 > controller6.numeroElementosDaListaTipoDeSensor());

        ler.nextLine();
        System.out.println("In which geographical area is this sensor included?");
        int posicao2 = -1;
        do {
            for (int i = 0; i < controller6.numeroElementosDaListaAreaGeografica(); i++) {
                System.out.println((i + 1) + " - " + (controller6.getNomeAreaGeograficaPorIndice(i)));
            }
            posicao2 = ler.nextInt();
        }
        while (posicao2 < 0 || posicao2 > controller6.numeroElementosDaListaAreaGeografica());

        controller6.getAreaGeograficaNaListaPorPosicao(posicao2 - 1);
        controller6.criarNovaLocalizacao(altitude, latitude, longitude);
        controller6.getTipoSensorPorPosicao(posicao1-1);

        if (controller6.adicionarSensorAAreaGeografica(controller6.criarNovoSensor(nome))) {
            System.out.println("Success! A sensor was created.");
        } else {
            System.out.println("This sensor already exists in this geographical area.");
        }
    }

}
