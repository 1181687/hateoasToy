package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US5Controller;
import pt.ipp.isep.dei.project.model.SensorTypeList;

import java.util.Scanner;

public class DefineSensorType {

    private US5Controller mController;

    public DefineSensorType(SensorTypeList mSensorTypeList) {

        this.mController = new US5Controller(mSensorTypeList);
    }

    public void run(){
        System.out.println("Introduce a new sensor type.");
        Scanner ler = new Scanner(System.in);
        String nome = ler.nextLine();
        if(this.mController.criarEAdicionarTipoDeSensor(nome)){
            System.out.println("Success!");
        }
        else{
            System.out.println("Try another sensor type!");
        }
    }
}
