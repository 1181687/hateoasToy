package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US3Controller;
import pt.ipp.isep.dei.project.model.*;

import java.util.Scanner;

public class US3UI {

    private US3Controller ctrl3;

    public US3UI(ListaAG lista, ListaTiposAG listaTAG) {
        this.ctrl3 = new US3Controller(lista, listaTAG);
    }


    public void run() {
        System.out.println("Introduza o nome da Area Geografica");
        Scanner ler = new Scanner(System.in);
        String nome = ler.nextLine();
        System.out.println("Escolha o tipo da Area Geografica");
        for (int i = 1; i <= ctrl3.getListaTAG().size(); i++) {
            System.out.println(i + " - " +
                    "Tipo: " + ctrl3.getListaTAG().get(i - 1));
        }
        int opcao = ler.nextInt();
        System.out.println("Introduza a latitude da localização da Area Geografica (valores válidos entre -90 e 90)");
        double latitude = ler.nextDouble();
        while (latitude < -90 || latitude > 90) {
            System.out.println("Esse valor está fora dos valores válidos (-90 e 90). Introduza um novo valor de latitude.");
            latitude = ler.nextDouble();
        }

        System.out.println("Introduza a longitude da localização da Area Geografica (valores válidos entre -180 e 180)");
        double longitude = ler.nextDouble();
        while (longitude < -180 || longitude > 180) {
            System.out.println("Esse valor está fora dos valores válidos (-180 e 180). Introduza um novo valor de longitude.");
            longitude = ler.nextDouble();
        }

        System.out.println("Introduza a altitude da localização da Area Geografica");
        Double altitude = ler.nextDouble();

        System.out.println("Introduza o comprimento da Area Geografica (valores válidos superiores a 0)");
        double comprimento = ler.nextDouble();
        while (comprimento <=0) {
            System.out.println("Esse valor está fora dos valores válidos (superior a 0). Introduza um novo valor de comprimento.");
            comprimento = ler.nextDouble();
        }

        System.out.println("Introduza a largura da Area Geografica (valores válidos superiores a 0)");
        double largura = ler.nextDouble();
        while (largura <=0) {
            System.out.println("Esse valor está fora dos valores válidos (superior a 0). Introduza um novo valor de largura.");
            largura = ler.nextDouble();
        }


        TipoAreaGeo novoTipo = new TipoAreaGeo(ctrl3.getListaTAG ().get(opcao - 1));
        Location newLocation = new Location(latitude, longitude, altitude);
        RectangleArea newRectangleArea = new RectangleArea(comprimento, largura, newLocation);
        AreaGeografica novaAG = new AreaGeografica(nome, novoTipo, newLocation, newRectangleArea);
        if (ctrl3.adicionarNovaAG(novaAG)) {
            System.out.println("Sucesso!");
        } else {
            System.out.println("Tente outro nome!");
        }
    }
}

