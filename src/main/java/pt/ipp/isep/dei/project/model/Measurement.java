package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;

public class Measurement {
    private double mValue;
    private LocalDateTime mDateTime;

    public Measurement(double mValue, LocalDateTime mDateTime) {
        this.mValue = mValue;
        this.mDateTime = mDateTime;
    }

    public double getmValue() {
        return mValue;
    }

    public LocalDateTime getmDateTime() {
        return mDateTime;
    }

}
