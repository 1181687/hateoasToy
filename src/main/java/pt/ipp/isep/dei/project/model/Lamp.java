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

    public boolean setmLuminousFlux(double mLuminousFlux) {
        this.mLuminousFlux = mLuminousFlux;
        return true;
    }

    public boolean setmNominalPower(double mNominalPower) {
        this.mNominalPower = mNominalPower;
        return true;
    }

    @Override
    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Luminous Flux: " + mLuminousFlux + "\n");
        attributes.append("2 - Nominal Power: " + mNominalPower + "\n");
        String lampAttributes = attributes.toString();
        return lampAttributes;
    }

    @Override
    public boolean setAttribute(int attribute, double value) {
        switch (attribute) {
            case 1:
                return setmLuminousFlux(value);
            case 2:
                return setmNominalPower(value);
        }
        System.out.println("Please select a valid number.");
        return false;
    }

    @Override
    public int getNumberOfAttributes() {
        return 2;
    }
}
