package sprint0.UI;

import sprint0.Controllers.US1Controller;
import sprint0.Controllers.US3Controller;
import sprint0.Model.*;

import java.util.Scanner;

public class US3UI {
    private ListaAG lista;
    private US1Controller ctrl;
    private US3Controller ctrl3;

    public US3UI(ListaAG lista) {
        this.lista = lista;
        this.ctrl3 = new US3Controller(lista);
    }


    public void run() {
        System.out.println("Introduza o nome da Area Geografica");
        Scanner ler = new Scanner(System.in);
        String nome = ler.nextLine();
        System.out.println("Introduza o tipo da Area Geografica");
        String tipo = ler.nextLine();
        System.out.println("Introduza a latitude da localização da Area Geografica");
        Double latitude = ler.nextDouble();
        System.out.println("Introduza a longitude da localização da Area Geografica");
        Double longitude = ler.nextDouble();
        System.out.println("Introduza a altitude da localização da Area Geografica");
        Double altitude = ler.nextDouble();
        System.out.println("Introduza o comprimento da Area Geografica");
        Double comprimento = ler.nextDouble();
        System.out.println("Introduza a largura da Area Geografica");
        Double largura = ler.nextDouble();


        TipoAreaGeo novoTipo = new TipoAreaGeo(tipo);
        Localizacao novaLocalizacao = new Localizacao(latitude, longitude, altitude);
        RetanguloArea novoRetanguloArea = new RetanguloArea(comprimento, largura, novaLocalizacao);
        AreaGeografica novaAG = new AreaGeografica(nome, novoTipo, novaLocalizacao, novoRetanguloArea);
        if (ctrl3.adicionarNovaAG(novaAG)) {
            System.out.println("Sucesso!");
        } else {
            System.out.println("Tente outro nome!");
        }
    }
}

