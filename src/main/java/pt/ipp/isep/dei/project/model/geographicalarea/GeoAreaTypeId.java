package pt.ipp.isep.dei.project.model.geographicalarea;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GeoAreaTypeId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String typeId;

    public GeoAreaTypeId(String typeId) {
        this.typeId = typeId;
    }

    protected GeoAreaTypeId() {
        //empty
    }

    public String getTypeId() {
        return this.typeId;
    }

    public void seTypeId(String typeId) {
        this.typeId = typeId;
    }
}
