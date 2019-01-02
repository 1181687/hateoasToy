package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US6Controller;
import pt.ipp.isep.dei.project.model.GeoAreaList;
import pt.ipp.isep.dei.project.model.SensorTypeList;

import java.util.Scanner;

public class US6UI {
    private US6Controller controller6;

    public US6UI(GeoAreaList geoAreaList, SensorTypeList sensorTypeList) {
        this.controller6 = new US6Controller(sensorTypeList, geoAreaList);
    }

    public void run() {
        System.out.println("Introduce the name of the new sensor.");
        Scanner ler = new Scanner(System.in);
        String nome = ler.nextLine();
        System.out.println("Introduce the latitude of the new sensor.");
        double latitude = ler.nextDouble();
        System.out.println("Introduce the longitude of the new sensor.");
        double longitude = ler.nextDouble();
        System.out.println("Introduce the altitude of the new sensor.");
        double altitude = ler.nextDouble();
        ler.nextLine();
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
