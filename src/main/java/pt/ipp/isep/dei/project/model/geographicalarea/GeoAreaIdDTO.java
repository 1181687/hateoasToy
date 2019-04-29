package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.LocationDTO;

import java.util.Objects;

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
        return this.geoAreaType;
    }

    public void setGeoAreaType(String geoAreaType) {
        this.geoAreaType = geoAreaType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeoAreaIdDTO)) {
            return false;
        }
        GeoAreaIdDTO geoAreaId = (GeoAreaIdDTO) obj;
        return this.id.equals(geoAreaId.id) && this.locationDTO.equals(geoAreaId.locationDTO) && this.geoAreaType.equals(geoAreaId.geoAreaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.geoAreaType, this.locationDTO);
    }
}
