package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.DefineSensorTypeController;
import pt.ipp.isep.dei.project.model.SensorTypeList;

import java.util.Scanner;
/** US005 As an Administrator, I want to define the sensor types. */

public class DefineSensorType {

    private DefineSensorTypeController mController;

    public DefineSensorType(SensorTypeList mSensorTypeList) {

        this.mController = new DefineSensorTypeController(mSensorTypeList);
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
