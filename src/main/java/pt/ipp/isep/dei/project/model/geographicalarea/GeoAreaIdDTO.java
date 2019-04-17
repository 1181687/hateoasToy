package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.LocationDTO;

public class GeoAreaIdDTO {
    private String id;
    private LocationDTO locationDTO;
    private String geoAreaType;

    public GeoAreaIdDTO() {
        //intentionally empty
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }

    public String getGeoAreaType() {
        return geoAreaType;
    }

    public void setGeoAreaType(String geoAreaType) {
        this.geoAreaType = geoAreaType;
    }
}
