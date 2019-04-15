package pt.ipp.isep.dei.project.model.geographicalarea;

import javax.persistence.Embeddable;

@Embeddable
public class GeoAreaTypeId {

    private String geoAreaTypeId;

    public GeoAreaTypeId(String geoAreaTypeId) {
        this.geoAreaTypeId = geoAreaTypeId;
    }

    public String getGeoAreaTypeId() {
        return geoAreaTypeId;
    }
}
