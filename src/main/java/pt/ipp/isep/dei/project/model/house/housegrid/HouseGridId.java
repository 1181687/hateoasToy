package pt.ipp.isep.dei.project.model.house.housegrid;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class HouseGridId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String housegridId;

    public HouseGridId(String housegridId) {
        this.housegridId = housegridId;
    }

    protected HouseGridId() {
        //empty
    }

    public String getHousegridId() {
        return housegridId;
    }
}
