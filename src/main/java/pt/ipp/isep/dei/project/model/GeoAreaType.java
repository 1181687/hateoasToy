package pt.ipp.isep.dei.project.model;

public class GeoAreaType {
    private String mGeoAreaType;

    public GeoAreaType(String mGeoAreaType) {
        this.mGeoAreaType = mGeoAreaType;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeoAreaType)) {
            return false;
        }
        GeoAreaType tag = (GeoAreaType) obj;
        return this.mGeoAreaType.equals(tag.mGeoAreaType);

    }

    public String getStringOfTypeOfGeoArea() {
        return mGeoAreaType;
    }

    public boolean checkIfOneTypeOfGeoAreaIsEqualToAnotherType(String type) {
        return (this.getStringOfTypeOfGeoArea().equals(type));
    }

}
