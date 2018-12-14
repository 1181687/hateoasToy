package sprint0.UI;

import sprint0.Controllers.US4Controller;
import sprint0.Model.ListaAG;
import sprint0.Model.ListaTiposAG;
import java.util.Scanner;

public class US4UI {

    private US4Controller mCtrl; //o controller tem como atributo as listas logo nao preciso ter aqui


    public US4UI(ListaAG lista, ListaTiposAG listaTAG) {
        this.mCtrl = new US4Controller(lista, listaTAG);
    }

    public void run() {
        System.out.println("Escolha o tipo de àrea geografica que pretende vizualizar");
        //List<String> listaDeTiposDeAG = mCtrl.getListaDosTiposDeAG();
        Scanner ler = new Scanner(System.in);
        int opcaoEscolhida = -1;
        do {
            for (int i = 1; i <= mCtrl.getListaDosTiposDeAG().size(); i++) {
                System.out.println(i +"-"+ mCtrl.getListaDosTiposDeAG().get(i - 1));
            }
            opcaoEscolhida = ler.nextInt();
        }
            while (opcaoEscolhida < 1 || opcaoEscolhida > mCtrl.getListaDosTiposDeAG().size());


            String tipoEscolhido = mCtrl.getListaDosTiposDeAG().get(opcaoEscolhida-1);

            if (mCtrl.getListaAGPorTipo(tipoEscolhido).isEmpty()) {
                System.out.println("Não foram encontradas àreas geográficas do tipo escolhido");
            } else {
                System.out.println(mCtrl.getListaAGPorTipo(tipoEscolhido));
            }
        }

    }
