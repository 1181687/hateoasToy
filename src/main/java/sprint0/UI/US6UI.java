package sprint0.UI;

import sprint0.Controllers.US6Controller;
import sprint0.Model.Sensor;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class US6UI {
    private Sensor mNovoSensor;
    private US6Controller controller6;

    public US6UI (Sensor novoSensor) {
        this.mNovoSensor = novoSensor;
        this.controller6 = new US6Controller(novoSensor);
    }

    public void run () {
        System.out.println("Introduza o nome do novo sensor");
        Scanner ler = new Scanner(System.in);
        String nome = ler.nextLine();
        System.out.println("Introduza a latitude do novo sensor");
        Double latitude = ler.nextDouble();
        System.out.println("Introduza a longitude do novo sensor");
        Double longitude = ler.nextDouble();
        System.out.println("Introduza a altitude do novo sensor");
        Double altitude = ler.nextDouble();
        System.out.println("Introduza o tipo de sensor");
        String tipo = ler.nextLine();
        if (controller6.atribuirTipoSensor(tipo)) {
            System.out.println("Sucesso!");
        } else {
            System.out.println("Tipo de sensor não existe!");
        }
        System.out.println("Em que área geográfica está este sensor inserido?");
        String areaInserida = ler.nextLine();
        if (controller6.adicionarSensorAAreaGeografica(areaInserida)) {
            System.out.println("Sucesso!");
        } else {
            System.out.println("Esta área geográfica não existe!");
        }

        

    }

}
