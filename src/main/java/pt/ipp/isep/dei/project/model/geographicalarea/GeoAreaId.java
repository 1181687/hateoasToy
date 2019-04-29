package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.Location;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

import static java.util.Objects.isNull;

@Embeddable
public class GeoAreaId implements Serializable {
    private static final long serialVersionUID = 1L;
    @Embedded
    private Location location;
    private String id;
    @Embedded
    private GeoAreaTypeId typeId;

    public GeoAreaId(@NotNull Location location, @NotNull String id, @NotNull GeographicalAreaType geographicalAreaType) {
        validateId(id);
        this.location = location;
        this.id = id;
        this.typeId = geographicalAreaType.getGeoAreaTypeId();
    }


    /**
     * method that receives a name and validates it. It can not be null or empty
     * throw an exception if the name is invalid
     *
     * @param id given name
     */
    private static void validateId(String id) {
        if (isNull(id) || id.trim().length() == 0) {
            throw new RuntimeException("Please enter a valid id. Name should not be empty");
        }
    }

    protected GeoAreaId() {
        //empty
    }

    public Location getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public GeoAreaTypeId getGeographicalAreaType() {
        return typeId;
    }

    public void setGeographicalAreaType(GeographicalAreaType geographicalAreaType) {
        this.typeId = geographicalAreaType.getGeoAreaTypeId();
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeoAreaId)) {
            return false;
        }
        GeoAreaId ag = (GeoAreaId) obj;
        return this.id.equals(ag.id);
    }
}
