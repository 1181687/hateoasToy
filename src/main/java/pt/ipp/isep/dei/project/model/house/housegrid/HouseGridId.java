package pt.ipp.isep.dei.project.model.house.housegrid;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class HouseGridId implements Serializable {

    private static final long serialVersionUID = 1L;
    private String houseGridId;


    public HouseGridId(String houseGridId) {
        this.houseGridId = houseGridId;
    }

    protected HouseGridId() {
        //empty
    }

    public String getHouseGridId() {
        return houseGridId;
    }
}
