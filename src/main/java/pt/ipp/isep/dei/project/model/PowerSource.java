package pt.ipp.isep.dei.project.model;

import static java.util.Objects.isNull;

public class PowerSource {
    private String mPowerSourceName;
    private PowerSourceType mPowerSourceType;
    private boolean mIsRechargeable;

    public PowerSource(String powerSourceName, PowerSourceType powerSourceType, boolean isRechargeable) {
        validateName(powerSourceName);
        validatePowerSourceType(powerSourceType);

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

        if(type.mIsRechargeable){
            return this.mPowerSourceName.equals(type.mPowerSourceName) && this.mPowerSourceType.equals(type.mPowerSourceType);
        }
        else {
            return this.mPowerSourceName.equals(type.mPowerSourceName) && this.mPowerSourceType.equals(type.mPowerSourceType);
        }
    }

    private static void validateName(String name) {
        if (isNull(name) || name.trim().length() == 0) {
            throw new NullPointerException("Please enter a valid name. Name should not be empty");
        }
    }

    private static void validatePowerSourceType(PowerSourceType powerSourceType) {
        if (isNull(powerSourceType)) {
            throw new NullPointerException("Please select a valid power source type");
        }
    }

}
