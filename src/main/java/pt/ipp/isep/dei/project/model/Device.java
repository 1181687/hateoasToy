package pt.ipp.isep.dei.project.model;

import java.util.List;

public class Device implements Measurable {
    private String mName;
    private Room mLocation;
    private DeviceSpecs mSpec;
    private double mNominalPower;

    @Override
    public double getNominalPower() {
        return mNominalPower;
    }

    /*public List<String> getTypeNames (){

        List <String> listOfTypeNames = null;

        DeviceSpecs fridge = new Fridge();
        listOfTypeNames.add(fridge.getmTypeName());

        DeviceSpecs lamp= new Lamp();
        listOfTypeNames.add(lamp.getmTypeName());

        DeviceSpecs dishWasher= new DishWasher();
        listOfTypeNames.add(dishWasher.getmTypeName());

        DeviceSpecs washingMachine = new WashingMachine();
        listOfTypeNames.add(washingMachine.getmTypeName());

        DeviceSpecs electricWaterHeater =new ElectricWaterHeater();
        listOfTypeNames.add(electricWaterHeater.getmTypeName());

        return listOfTypeNames;
    }*/

}
