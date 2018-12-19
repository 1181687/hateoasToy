package pt.ipp.isep.dei.project.model;

public class PowerSource {
    private PowerSourceType mPowerSourceType;
    private double mMaximumPowerOutput;
    private boolean mIsRechargeable;
    private double mMaximumAmountOfStorableEnergy;

    public PowerSource(PowerSourceType powerSourceType, boolean isRechargeable) {
        this.mPowerSourceType = powerSourceType;
        this.mIsRechargeable = isRechargeable;
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

        if(type.mIsRechargeable){
            return this.mPowerSourceType.equals(type.mPowerSourceType) && this.mMaximumPowerOutput == type.mMaximumPowerOutput && this.mMaximumAmountOfStorableEnergy==type.mMaximumAmountOfStorableEnergy;

        }
        else {
            return this.mPowerSourceType.equals(type.mPowerSourceType) && this.mMaximumPowerOutput == type.mMaximumPowerOutput;
        }
    }
}
