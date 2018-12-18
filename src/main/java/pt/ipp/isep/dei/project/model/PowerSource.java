package pt.ipp.isep.dei.project.model;

public class PowerSource {
    private PowerSourceType mPowerSourceType;
    private double mMaximumPowerOutput;
    private boolean mIsRechargeable;
    private double mMaximumAmountOfStorableEnergy;

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

        if(type.mIsRechargeable){
            return this.mPowerSourceType.equals(type.mPowerSourceType) && this.mMaximumPowerOutput == type.mMaximumPowerOutput && this.mMaximumAmountOfStorableEnergy==type.mMaximumAmountOfStorableEnergy;

        }
        else {
            return this.mPowerSourceType.equals(type.mPowerSourceType) && this.mMaximumPowerOutput == type.mMaximumPowerOutput;
        }
    }
}
