package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.Location;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static java.util.Objects.isNull;

@Embeddable
public class GeoAreaId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Location location;

    private String id;

    private GeographicalAreaType geographicalAreaType;

    public GeoAreaId(@NotNull Location location, @NotNull String id, @NotNull GeographicalAreaType geographicalAreaType) {
        validateId(id);
        this.location = location;
        this.id = id;
        this.geographicalAreaType = geographicalAreaType;
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

    public GeographicalAreaType getGeographicalAreaType() {
        return geographicalAreaType;
    }

    public void setGeographicalAreaType(GeographicalAreaType geographicalAreaType) {
        this.geographicalAreaType = geographicalAreaType;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setId(String id) {
        this.id = id;
    }
}
