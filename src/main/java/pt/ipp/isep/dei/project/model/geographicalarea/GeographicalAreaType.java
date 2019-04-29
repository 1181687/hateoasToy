package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.roles.Root;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class GeographicalAreaType implements Root {

    @EmbeddedId
    private GeoAreaTypeId geoAreaTypeId;

    /**
     * constructor that receives a type of geographical area.
     * @param geoAreaTypeId
     */
    public GeographicalAreaType(GeoAreaTypeId geoAreaTypeId) {
        this.geoAreaTypeId = geoAreaTypeId;
    }

    protected GeographicalAreaType() {
    }

    public GeoAreaTypeId getGeoAreaType() {
        return geoAreaTypeId;
    }


    /**
     * method that creates the hashcode to geoAreaType.
     */
    @Override
    public int hashCode() {
        return 1;
    }

    /**
     * Equals method to determine if two GeographicalAreaType are equal.
     * They are equals if the atributtes (geoAreaType) are equal.
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeographicalAreaType)) {
            return false;
        }
        GeographicalAreaType tag = (GeographicalAreaType) obj;
        return this.geoAreaTypeId.equals(tag.geoAreaTypeId);
    }

    /**
     * get the geo area type.
     *
     * @return a type of geo area.
     */
    public String getStringOfTypeOfGeoArea() {
        return this.geoAreaTypeId.getTypeId();
    }

    public void setStringOfTypeOfGeoArea(String geoAreaType) {
        this.geoAreaTypeId = new GeoAreaTypeId(geoAreaType);
    }


    /**
     * method that check if one type of geo area is equal to another type of geo area.
     *
     * @param type
     * @return a boolean.
     */
    public boolean checkIfOneTypeOfGeoAreaIsEqualToAnotherType(String type) {
        return (this.getStringOfTypeOfGeoArea().equals(type));
    }

}
