package pt.ipp.isep.dei.project.model.house.powersource;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PowerSourceId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String powerSourceId;

    public PowerSourceId(String powerSourceId) {
        this.powerSourceId = powerSourceId;
    }

    protected PowerSourceId() {
        // empty
    }

    public String getPowerSourceId() {
        return powerSourceId;
    }
}
