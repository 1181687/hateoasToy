package pt.ipp.isep.dei.project.model;

public class DeviceType {
    private String mDeviceType;

    public DeviceType(String deviceType) {
        this.mDeviceType = deviceType;
    }

    public String getDeviceType() {
        return mDeviceType;
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
        if (!(obj instanceof DeviceType)) {
            return false;
        }

        DeviceType type = (DeviceType) obj;
        return this.mDeviceType.equals(type.mDeviceType);

    }
}
