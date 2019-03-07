package pt.ipp.isep.dei.project.model;

public class GeographicalAreaDTO {

    private String geoAreaName;
    private GeographicalAreaType geographicalAreaType;
    private GeographicalArea insertedIn;
    private Location location;
    private AreaShape areaShape;

    public GeographicalAreaDTO() {
        // empty
    }

    public String getGeoAreaName() {
        return geoAreaName;
    }

    public void setGeoAreaName(String geoAreaName) {
        this.geoAreaName = geoAreaName;
    }

    public GeographicalAreaType getGeographicalAreaType() {
        return geographicalAreaType;
    }

    public void setGeographicalAreaType(GeographicalAreaType geographicalAreaType) {
        this.geographicalAreaType = geographicalAreaType;
    }

    public GeographicalArea getInsertedIn() {
        return insertedIn;
    }

    public void setInsertedIn(GeographicalArea insertedIn) {
        this.insertedIn = insertedIn;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public AreaShape getAreaShape() {
        return areaShape;
    }

    public void setAreaShape(AreaShape areaShape) {
        this.areaShape = areaShape;
    }
}
