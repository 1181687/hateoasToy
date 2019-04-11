package pt.ipp.isep.dei.project.model.sensor;

import javax.persistence.Embeddable;

@Embeddable
public class SensorState {
    private boolean isActive;

    public SensorState() {
        this.isActive = true;
    }

    public void deactivateSensor() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }
}
