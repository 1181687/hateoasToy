package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US4Controller;
import pt.ipp.isep.dei.project.model.ListaAG;
import pt.ipp.isep.dei.project.model.ListaTiposAG;

import java.util.List;
import java.util.Scanner;

public class US4UI {

    private US4Controller mCtrl; //o controller tem como atributo as listas logo nao preciso ter aqui


    public US4UI(ListaAG lista, ListaTiposAG listaTAG) {
        this.mCtrl = new US4Controller(lista, listaTAG);
    }

    public void run() {
        System.out.println("Escolha o tipo de àrea geografica que pretende vizualizar");
        List<String> listaDeTiposDeAG = mCtrl.getListaDosTiposDeAG();
        Scanner ler = new Scanner(System.in);
        int opcaoEscolhida = -1;
        do {
            for (int i = 1; i <= listaDeTiposDeAG.size(); i++) {
                System.out.println(i +"-"+ listaDeTiposDeAG.get(i - 1));
            }
            opcaoEscolhida = ler.nextInt();
        }
            while (opcaoEscolhida < 1 || opcaoEscolhida > listaDeTiposDeAG.size());


            String tipoEscolhido = listaDeTiposDeAG.get(opcaoEscolhida-1);

        List<String> listaDeAGPorTipo = mCtrl.getListaAGPorTipo(tipoEscolhido);

        if (listaDeAGPorTipo.isEmpty()) {
                System.out.println("Não foram encontradas àreas geográficas do tipo escolhido");
            } else {
                if (listaDeAGPorTipo.size()==1){
                    System.out.println("A área geográfica encontrada do tipo "+tipoEscolhido+" é:");
                }
                if (listaDeAGPorTipo.size()>1){
                System.out.println("As áreas geográficas encontradas do tipo "+tipoEscolhido+" são:");
                }
                for (int i = 0; i <listaDeAGPorTipo.size() ; i++) {
                    System.out.println(listaDeAGPorTipo.get(i));
                }
                System.out.println("");
            }
        }

    }
