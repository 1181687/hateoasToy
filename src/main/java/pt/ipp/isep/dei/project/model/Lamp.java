package pt.ipp.isep.dei.project.model;

public class Lamp implements DeviceSpecs {
    private String mTypeName ;
    private double mLuminousFlux;
    private double mTime;
    private double mNominalPower;

    public Lamp(double mLuminousFlux, double mNominalPower) {
        this.mTypeName = "Lamp";
        this.mLuminousFlux = mLuminousFlux;
        this.mNominalPower = mNominalPower;
    }

    public Lamp() {
    }

    @Override
    public String getmTypeName() {
        return mTypeName;
    }

    @Override
    public double getmNominalPower() {
        return mNominalPower;
    }

    @Override
    public double getEnergyConsumptionInADay() {
        return mNominalPower * mTime;
    }
}
