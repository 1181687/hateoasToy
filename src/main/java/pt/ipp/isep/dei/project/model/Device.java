package pt.ipp.isep.dei.project.model;

public class Device implements Measurable {
    private String mName;
    private Room mLocation;
    private DeviceSpecs mSpec;
    private double mNominalPower;

    public Device(String mName, Room mLocation, DeviceSpecs mSpec, double mNominalPower) {
        this.mName = mName;
        this.mLocation = mLocation;
        this.mSpec = mSpec;
        this.mNominalPower = mNominalPower;
    }

    @Override
    public double getNominalPower() {
        return mNominalPower;
    }
}
