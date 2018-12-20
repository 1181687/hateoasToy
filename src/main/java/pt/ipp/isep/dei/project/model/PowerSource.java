package pt.ipp.isep.dei.project.model;

public class PowerSource {
    private String mPowerSourceName;
    private PowerSourceType mPowerSourceType;
    private double mMaximumPowerOutput;
    private boolean mIsRechargeable;
    private double mMaximumAmountOfStorableEnergy;

    public PowerSource(String powerSourceName, PowerSourceType powerSourceType, boolean isRechargeable) {
        this.mPowerSourceType = powerSourceType;
        this.mIsRechargeable = isRechargeable;
        this.mPowerSourceName = powerSourceName;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PowerSource)) {
            return false;
        }

        PowerSource type = (PowerSource) obj;

        if(this.mIsRechargeable!=type.mIsRechargeable){
            return false;
        }

        final double delta = 0.0001;

        if(type.mIsRechargeable){
            return this.mPowerSourceName.equals(type.mPowerSourceName) && this.mPowerSourceType.equals(type.mPowerSourceType) && Math.abs(this.mMaximumPowerOutput - type.mMaximumPowerOutput)<delta && Math.abs(this.mMaximumAmountOfStorableEnergy-type.mMaximumAmountOfStorableEnergy)<delta;
        }
        else {
            return this.mPowerSourceName.equals(type.mPowerSourceName) && this.mPowerSourceType.equals(type.mPowerSourceType) && Math.abs(this.mMaximumPowerOutput - type.mMaximumPowerOutput)<delta;
        }
    }

}
