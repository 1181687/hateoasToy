package pt.ipp.isep.dei.project.model.house.powersource;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PowerSourceTypeId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String powerSourceTypeId;

    public PowerSourceTypeId(String powerSourceTypeId) {
        this.powerSourceTypeId = powerSourceTypeId;
    }

    public String getPowerSourceTypeId() {
        return powerSourceTypeId;
    }
}
