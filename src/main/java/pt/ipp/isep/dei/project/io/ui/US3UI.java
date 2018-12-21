package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US3Controller;
import pt.ipp.isep.dei.project.model.GeographicalArea;
import pt.ipp.isep.dei.project.model.ListaAG;
import pt.ipp.isep.dei.project.model.ListaTiposAG;

import java.util.Scanner;

public class US3UI {

    private US3Controller ctrl3;

    public US3UI(ListaAG lista, ListaTiposAG listaTAG) {
        this.ctrl3 = new US3Controller(lista, listaTAG);
    }

    public void run() {
        System.out.println("Introduce the name of the Geographical Area");
        Scanner ler = new Scanner(System.in);
        String nome = ler.nextLine();
        System.out.println("Choose the Geographical Area type");
        for (int i = 1; i <= ctrl3.getListaTAG().size(); i++) {
            System.out.println(i + " - " +
                    "Type: " + ctrl3.getListaTAG().get(i - 1));
        }
        int opcao = ler.nextInt();
        String nomeTipoAG = ctrl3.getListaTAG().get(opcao-1);
        System.out.println("Introduce the latitude of the location of the Geographical Area (valid values between -90 and 90)");
        double latitude = ler.nextDouble();
        while (latitude < -90 || latitude > 90) {
            System.out.println("That value is out of bounds (-90 e 90). Introduce a new latitude value).");
            latitude = ler.nextDouble();
        }

        System.out.println("Introduce the longitude of the location of the Geographical Area(valid numbers between -180 and 180).");
        double longitude = ler.nextDouble();
        while (longitude < -180 || longitude > 180) {
            System.out.println("That value is out of bounds (-180 e 180). Introduce a new longitude value).");
            longitude = ler.nextDouble();
        }

        System.out.println("Introduce the altitude of the location of the Geographical Area.");
        Double altitude = ler.nextDouble();

        System.out.println("Introduce the length of the Geographical Area (valid numbers greater than 0).");
        double comprimento = ler.nextDouble();
        while (comprimento <=0) {
            System.out.println("That value is out of bounds (greater than 0). Introduce a new length value.");
            comprimento = ler.nextDouble();
        }

        System.out.println("Introduce the width of the Geographical Area (valid numbers greater than 0).");
        double largura = ler.nextDouble();
        while (largura <=0) {
            System.out.println("That value is out of bounds (greater than 0). Introduce a new width value.");
            largura = ler.nextDouble();
        }

        GeographicalArea novaAG = ctrl3.criarNovaAG(nome, nomeTipoAG, latitude, longitude, altitude, comprimento, largura);
        if (ctrl3.adicionarNovaAG(novaAG)) {
            System.out.println("Success!");
        } else {
            System.out.println("That Geographical Area already exists!");
        }
    }
}

