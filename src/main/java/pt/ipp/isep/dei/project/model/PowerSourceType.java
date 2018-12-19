package pt.ipp.isep.dei.project.model;

public class PowerSourceType {
    private String mPowerSourceType;

    public PowerSourceType(String mPowerSourceType) {
        this.mPowerSourceType = mPowerSourceType;
    }

    public String getPowerSourceType() {
        return mPowerSourceType;
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
        if (!(obj instanceof PowerSourceType)) {
            return false;
        }

        PowerSourceType type = (PowerSourceType) obj;
        return this.mPowerSourceType.equals(type.mPowerSourceType);

    }
}
