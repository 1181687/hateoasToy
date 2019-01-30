package pt.ipp.isep.dei.project.model;

public class GeographicalAreaType {
    private String mGeoAreaType;

    /**
     * constructor that receives a type of geographical area.
     * @param geoAreaType
     */
    public GeographicalAreaType(String geoAreaType) {
        this.mGeoAreaType = geoAreaType;
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
     * They are equals if the atributtes (mGeoAreaType) are equal.
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
        return this.mGeoAreaType.equals(tag.mGeoAreaType);
    }

    /**
     * get the geo area type.
     * @return a type of geo area.
     */
    public String getStringOfTypeOfGeoArea() {
        return mGeoAreaType;
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