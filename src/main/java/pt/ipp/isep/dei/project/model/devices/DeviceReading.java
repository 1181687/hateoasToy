package pt.ipp.isep.dei.project.model.devices;

import java.time.LocalDateTime;

public class DeviceReading {
    private double value;
    private LocalDateTime localDateTime;

    public DeviceReading(double value, LocalDateTime localDateTime) {
        this.value = value;
        this.localDateTime = localDateTime;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
