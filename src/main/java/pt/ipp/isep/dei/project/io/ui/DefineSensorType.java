package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.DefineSensorTypeController;
import pt.ipp.isep.dei.project.model.Sensor.SensorTypeList;

import java.util.Scanner;
/** US005 As an Administrator, I want to define the sensor types. */

public class DefineSensorType {

    private DefineSensorTypeController controller;

    public DefineSensorType(SensorTypeList mSensorTypeList) {

        this.controller = new DefineSensorTypeController(mSensorTypeList);
    }

    public void run(){
        System.out.println("Introduce a new sensor type.");
        Scanner ler = new Scanner(System.in);
        String nome = ler.nextLine();
        if (this.controller.createAndAddSensorType(nome)) {
            System.out.println("Success!");
        }
        else{
            System.out.println("Try another sensor type!");
        }
    }
}
