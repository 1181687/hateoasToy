package pt.ipp.isep.dei.project.model.geographicalarea;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GeoAreaTypeId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String geoAreaTypeId;

    public GeoAreaTypeId(String geoAreaTypeId) {
        this.geoAreaTypeId = geoAreaTypeId;
    }

    protected GeoAreaTypeId() {
    }

    public String getGeoAreaTypeId() {
        return geoAreaTypeId;
    }

    public void setGeoAreaTypeId(String geoAreaTypeId) {
        this.geoAreaTypeId = geoAreaTypeId;
    }
}
