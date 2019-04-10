package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.Location;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class GeoAreaId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Location location;

    private String id;

    private GeographicalAreaType geographicalAreaType;

    public GeoAreaId(@NotNull Location location, @NotNull String id, @NotNull GeographicalAreaType geographicalAreaType) {
        this.location = location;
        this.id = id;
        this.geographicalAreaType = geographicalAreaType;
    }

    protected GeoAreaId(){
        //empty
    }

    public Location getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public GeographicalAreaType getGeographicalAreaType() {
        return geographicalAreaType;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGeographicalAreaType(GeographicalAreaType geographicalAreaType) {
        this.geographicalAreaType = geographicalAreaType;
    }
}
