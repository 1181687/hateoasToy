package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.Location;

public class GeographicalAreaMapping {

    public GeographicalAreaMapping() {
        // empty
    }

    public static GeographicalAreaDTO mapToDTO(String geoAreaName, String description, String geographicalAreaType, double width, double lenght, double latitude, double longitude, double altitude) {
        GeographicalAreaDTO geoDTO = new GeographicalAreaDTO();
        geoDTO.setGeoAreaName(geoAreaName);
        geoDTO.setDescription(description);
        geoDTO.setGeographicalAreaType(geographicalAreaType);
        geoDTO.setWidth(width);
        geoDTO.setLenght(lenght);
        geoDTO.setLatitude(latitude);
        geoDTO.setLongitude(longitude);
        geoDTO.setAltitude(altitude);
        return geoDTO;
    }

    public static GeographicalArea mapToEntityGeoArea(GeographicalAreaDTO geographicalAreaDTO) {
        GeographicalAreaType geoType = new GeographicalAreaType(geographicalAreaDTO.getGeographicalAreaType());
        Location loc = new Location(geographicalAreaDTO.getLatitude(),geographicalAreaDTO.getLongitude(),geographicalAreaDTO.getAltitude());
        AreaShape areaShape = new AreaShape(geographicalAreaDTO.getWidth(),geographicalAreaDTO.getLenght(),loc);
        return new GeographicalArea(geographicalAreaDTO.getGeoAreaName(), geographicalAreaDTO.getDescription(), geoType, loc, areaShape);
    }

}
