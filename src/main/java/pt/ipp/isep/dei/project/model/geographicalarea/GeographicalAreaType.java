package pt.ipp.isep.dei.project.model.geographicalarea;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GeographicalAreaType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String geoAreaType;

    /**
     * constructor that receives a type of geographical area.
     * @param geoAreaType
     */
    public GeographicalAreaType(String geoAreaType) {
        this.geoAreaType = geoAreaType;
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

    /**
     * get the geo area type.
     * @return a type of geo area.
     */
    public String getStringOfTypeOfGeoArea() {
        return geoAreaType;
    }

    /**
     * method that check if one type of geo area is equal to another type of geo area.
     * @param type
     * @return a boolean.
     */
    public boolean checkIfOneTypeOfGeoAreaIsEqualToAnotherType(String type) {
        return (this.getStringOfTypeOfGeoArea().equals(type));
    }

}
