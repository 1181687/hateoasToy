package sprint0.UI;

import sprint0.Controllers.US6Controller;
import sprint0.Model.*;

import java.util.*;

public class US6UI {
    private US6Controller controller6;
    private ListaTiposSensores listaTiposSensores;
    private ListaAG listaAG;

    public US6UI(ListaAG listaAG, ListaTiposSensores listaTiposSensores) {
        this.controller6 = new US6Controller(listaTiposSensores,listaAG);
        this.listaTiposSensores = listaTiposSensores;
        this.listaAG = listaAG;
    }

    public void run() {
        System.out.println("Introduza o nome do novo sensor");
        Scanner ler = new Scanner(System.in);
        String nome = ler.nextLine();
        System.out.println("Introduza a latitude do novo sensor");
        Double latitude = ler.nextDouble();
        System.out.println("Introduza a longitude do novo sensor");
        Double longitude = ler.nextDouble();
        System.out.println("Introduza a altitude do novo sensor");
        Double altitude = ler.nextDouble();
        ler.nextLine();
        System.out.println("Introduza o tipo de sensor");
        int posicao1 = -1;
        do {
            for (int i = 0; i < controller6.numeroElementosDaListaTipoDeSensor(listaTiposSensores); i++) {
                System.out.println((i + 1) + " - " +(controller6.getNomeTipoSensorPorIndice(i)));
            }
            posicao1 = ler.nextInt();
        }
        while (posicao1 < 0 || posicao1 > controller6.numeroElementosDaListaTipoDeSensor(listaTiposSensores));

        ler.nextLine();
        System.out.println("Em que área geográfica está este sensor inserido?");
        int posicao2 = -1;
        do {
            for (int i = 0; i < controller6.numeroElementosDaListaAreaGeografica(listaAG); i++) {
                System.out.println((i + 1) + " - " + (controller6.getNomeAreaGeograficaPorIndice(i)));
            }
            posicao2 = ler.nextInt();
        }
        while (posicao2 < 0 || posicao2 > controller6.numeroElementosDaListaAreaGeografica(listaAG));

        Localizacao novaLocalizacao = new Localizacao(latitude, longitude, altitude);
        TipoSensor novoTipoSensor = controller6.getTipoSensorPorPosicao(posicao1-1);
        Calendar calendario = new GregorianCalendar(Locale.getDefault());
        Date dataFuncionamento = calendario.getTime();
        Sensor novoSensor = new Sensor(nome, dataFuncionamento, novoTipoSensor, novaLocalizacao);
        AreaGeografica areaGeografica = controller6.getAreaGeografica(posicao2-1);

        if (controller6.adicionarSensorAAreaGeografica(novoSensor,areaGeografica)) {
            System.out.println("Sucesso! Foi criado um Sensor.");
        } else {
            System.out.println("Este sensor já existe nesta área geográfica.");
        }
    }

}
