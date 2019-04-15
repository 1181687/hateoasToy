package pt.ipp.isep.dei.project.model.house.powersource;

import javax.persistence.Embeddable;

@Embeddable
public class PowerSourceTypeId {
    private String powerSourceTypeId;

    public PowerSourceTypeId(String powerSourceTypeId) {
        this.powerSourceTypeId = powerSourceTypeId;
    }

    public String getPowerSourceTypeId() {
        return powerSourceTypeId;
    }
}
