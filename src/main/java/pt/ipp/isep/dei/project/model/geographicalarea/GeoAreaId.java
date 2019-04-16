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

    private String id;

    private Location location;

    @Embedded
    private GeoAreaTypeId geographicalAreaTypeId;

    public GeoAreaId(@NotNull String id, @NotNull Location location, @NotNull GeoAreaTypeId geographicalAreaTypeId) {
        validateId(id);
        this.location = location;
        this.id = id;
        this.geographicalAreaTypeId = geographicalAreaTypeId;
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
        return geographicalAreaTypeId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeographicalArea)) {
            return false;
        }
        GeoAreaId geoAreaId = (GeoAreaId) obj;
        return this.id.equals(geoAreaId.id) && this.geographicalAreaTypeId.equals(geoAreaId.geographicalAreaTypeId) && this.location.equals(geoAreaId.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
