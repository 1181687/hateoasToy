package pt.ipp.isep.dei.project.model;

public class Device implements Measurable {
    private String mName;
    private Room mLocation;
    private DeviceSpecs mSpec;
    private double mNominalPower;

    @Override
    public double getNominalPower() {
        return mNominalPower;
    }

    /**
     * get method
     *
     * @return name of device
     */
    public String getmName() {
        return this.mName;
    }
}
