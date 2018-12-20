package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US6Controller;
import pt.ipp.isep.dei.project.model.*;

import java.util.Scanner;

public class US6UI {
    private US6Controller controller6;

    public US6UI(ListaAG listaAG, ListaTiposSensores listaTiposSensores) {
        this.controller6 = new US6Controller(listaTiposSensores,listaAG);
    }

    public void run() {
        System.out.println("Introduza o nome do novo sensor");
        Scanner ler = new Scanner(System.in);
        String nome = ler.nextLine();
        System.out.println("Introduza a latitude do novo sensor");
        double latitude = ler.nextDouble();
        System.out.println("Introduza a longitude do novo sensor");
        double longitude = ler.nextDouble();
        System.out.println("Introduza a altitude do novo sensor");
        double altitude = ler.nextDouble();
        ler.nextLine();
        System.out.println("Introduza o tipo de sensor");
        int posicao1 = -1;
        do {
            for (int i = 0; i < controller6.numeroElementosDaListaTipoDeSensor(); i++) {
                System.out.println((i + 1) + " - " +(controller6.getNomeTipoSensorPorIndice(i)));
            }
            posicao1 = ler.nextInt();
        }
        while (posicao1 < 0 || posicao1 > controller6.numeroElementosDaListaTipoDeSensor());

        ler.nextLine();
        System.out.println("Em que área geográfica está este sensor inserido?");
        int posicao2 = -1;
        do {
            for (int i = 0; i < controller6.numeroElementosDaListaAreaGeografica(); i++) {
                System.out.println((i + 1) + " - " + (controller6.getNomeAreaGeograficaPorIndice(i)));
            }
            posicao2 = ler.nextInt();
        }
        while (posicao2 < 0 || posicao2 > controller6.numeroElementosDaListaAreaGeografica());

        GeographicalArea geographicalArea = controller6.getAreaGeograficaNaListaPorPosicao(posicao2 - 1);
        Location novaLocalizacao = controller6.criarNovaLocalizacao(altitude, latitude, longitude, geographicalArea);
        TipoSensor novoTipoSensor = controller6.getTipoSensorPorPosicao(posicao1-1);
        Sensor novoSensor = controller6.criarNovoSensor(nome, novoTipoSensor, novaLocalizacao, geographicalArea);

        if (controller6.adicionarSensorAAreaGeografica(novoSensor, geographicalArea)) {
            System.out.println("Sucesso! Foi criado um Sensor.");
        } else {
            System.out.println("Este sensor já existe nesta área geográfica.");
        }
    }

}
