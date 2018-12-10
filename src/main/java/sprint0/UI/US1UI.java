package sprint0.UI;

import sprint0.Controllers.US1Controller;
import sprint0.Model.ListaTiposAG;

import java.util.Scanner;

public class US1UI {
    ListaTiposAG lista;
    US1Controller ctrl;

    public US1UI(ListaTiposAG lista) {
        this.lista = lista;
        this.ctrl = new US1Controller(lista);
    }

    public void run() {
        System.out.println("Introduza o nome do novo tipo de Area Geografica");
        Scanner ler = new Scanner(System.in);
        String nome = ler.nextLine();
        if (ctrl.novoTAG(nome)) {
            System.out.println("Sucesso!");
        } else {
            System.out.println("Tente outro nome!");
        }
    }
}
