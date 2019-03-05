package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.Location;

public class GeographicalAreaDTO {

    private String geoAreaName;
    private GeographicalAreaType geographicalAreaType;
    private GeographicalArea insertedIn;
    private Location location;
    private AreaShape areaShape;

    public GeographicalAreaDTO(String geoAreaName, GeographicalAreaType geographicalAreaType, GeographicalArea insertedIn, Location location, AreaShape areaShape) {
        this.geoAreaName = geoAreaName;
        this.geographicalAreaType = geographicalAreaType;
        this.insertedIn = insertedIn;
        this.location = location;
        this.areaShape = areaShape;
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
