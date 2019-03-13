package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.Location;

public class GeographicalAreaMapping {

    public GeographicalAreaMapping() {
        // empty
    }

    public static GeographicalAreaDTO newGeoAreaDTO() {
        return new GeographicalAreaDTO();
    }

    public static GeographicalAreaDTO mapToDTO(String geoAreaName, String description, String geographicalAreaType, double width, double length, double latitude, double longitude, double altitude) {
        GeographicalAreaDTO geoDTO = new GeographicalAreaDTO();
        geoDTO.setId(geoAreaName);
        geoDTO.setSensorName(description);
        geoDTO.setGeographicalAreaType(geographicalAreaType);
        geoDTO.setWidth(width);
        geoDTO.setLength(length);
        geoDTO.setLatitude(latitude);
        geoDTO.setLongitude(longitude);
        geoDTO.setAltitude(altitude);
        return geoDTO;
    }

    public static GeographicalArea mapToEntityGeoArea(GeographicalAreaDTO geographicalAreaDTO) {
        GeographicalAreaType geoType = new GeographicalAreaType(geographicalAreaDTO.getGeographicalAreaType());
        Location loc = new Location(geographicalAreaDTO.getLatitude(),geographicalAreaDTO.getLongitude(),geographicalAreaDTO.getAltitude());
        AreaShape areaShape = new AreaShape(geographicalAreaDTO.getWidth(),geographicalAreaDTO.getLength(),loc);
        return new GeographicalArea(geographicalAreaDTO.getId(), geographicalAreaDTO.getSensorName(), geoType, loc, areaShape);
    }

}
