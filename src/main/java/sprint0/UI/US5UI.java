package sprint0.UI;

import sprint0.Controllers.US5Controller;
import sprint0.Model.ListaTiposSensores;

import java.util.Scanner;

public class US5UI {

    private US5Controller mController;

    public US5UI(ListaTiposSensores mListaTiposSensores) {

        this.mController = new US5Controller(mListaTiposSensores);
    }

    public void run(){
        System.out.println("Introduza uma nova característica meteorológica");
        Scanner ler = new Scanner(System.in);
        String nome = ler.nextLine();
        if(this.mController.criarEAdicionarTipoDeSensor(nome)){
            System.out.println("Sucesso!");
        }
        else{
            System.out.println("Tente outra característica meteorológica!");
        }
    }
}
