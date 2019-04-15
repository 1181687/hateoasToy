package pt.ipp.isep.dei.project.model.geographicalarea;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class GeographicalAreaType {

    @EmbeddedId
    private GeoAreaTypeId geoAreaType;

    /**
     * constructor that receives a type of geographical area.
     * @param geoAreaType
     */
    public GeographicalAreaType(String geoAreaType) {
        this.geoAreaType = new GeoAreaTypeId(geoAreaType);
    }

    protected GeographicalAreaType() {
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
        return this.geoAreaType.equals(tag.geoAreaType);
    }

//
//    public void setStringOfTypeOfGeoArea(String geoAreaType) {
//        this.geoAreaType = geoAreaType;
//    }
//
//
//    /**
//     * method that check if one type of geo area is equal to another type of geo area.
//     * @param type
//     * @return a boolean.
//     */
//    public boolean checkIfOneTypeOfGeoAreaIsEqualToAnotherType(String type) {
//        return (this.getStringOfTypeOfGeoArea().equals(type));
//    }

}
