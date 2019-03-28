package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.Location;

import java.util.Objects;

public final class GeographicalAreaMapper {

    /**
     * Constructor.
     */
    protected GeographicalAreaMapper() {
        // empty
    }

    /**
     * Method that creates a new GeoAreaDTO.
     *
     * @return GeoAreaDTO.
     */
    public static GeographicalAreaDTO newGeoAreaDTO() {
        return new GeographicalAreaDTO();
    }

    /**
     * Method that creates a GeoAreaDTO based on a set of information.
     *
     * @param geoAreaId            Id to be used.
     * @param description          Description to be used.
     * @param geographicalAreaType Type to be used.
     * @param width                Width to be used.
     * @param length               Length to be used.
     * @param latitude             Latitude to be used.
     * @param longitude            Longitude to be used.
     * @param elevation            Elevation to be used.
     * @return GeoAreaDTO.
     */
    public static GeographicalAreaDTO mapToDTO(String geoAreaId, String description, String geographicalAreaType, double width, double length, double latitude, double longitude, double elevation) {
        GeographicalAreaDTO geoDTO = new GeographicalAreaDTO();
        geoDTO.setId(geoAreaId);
        geoDTO.setDescription(description);
        geoDTO.setType(geographicalAreaType);
        geoDTO.setWidth(width);
        geoDTO.setLength(length);
        geoDTO.setLatitude(latitude);
        geoDTO.setLongitude(longitude);
        geoDTO.setElevation(elevation);
        return geoDTO;
    }

    /**
     * Method that creates a GeoAreaDTO based on a existing GeoArea.
     *
     * @param geographicalArea GeoArea to be used.
     * @return GeoAreaDTO.
     */
    public static GeographicalAreaDTO mapToDTO(GeographicalArea geographicalArea) {
        if (Objects.isNull(geographicalArea)) {
            return null;
        }
        GeographicalAreaDTO geoDTO = new GeographicalAreaDTO();
        geoDTO.setId(geographicalArea.getId());
        geoDTO.setDescription(geographicalArea.getDescription());
        geoDTO.setType(geographicalArea.getGeoAreaType().getStringOfTypeOfGeoArea());
        geoDTO.setWidth(geographicalArea.getAreaShape().getWidth());
        geoDTO.setLength(geographicalArea.getAreaShape().getLength());
        geoDTO.setLatitude(geographicalArea.getLocation().getLatitude());
        geoDTO.setLongitude(geographicalArea.getLocation().getLongitude());
        geoDTO.setElevation(geographicalArea.getLocation().getElevation());
        geoDTO.addAllSensors(geographicalArea.getSensorListInTheGeographicArea().getListOfSensors());
        return geoDTO;
    }

    /**
     * Method that turns a GeoAreaDTO into a GeoArea.
     *
     * @param geographicalAreaDTO GeoAreaDTO to be used.
     * @return GeoArea with the required information.
     */
    public static GeographicalArea mapToEntity(GeographicalAreaDTO geographicalAreaDTO) {
        if (Objects.isNull(geographicalAreaDTO)) {
            return null;
        }
        GeographicalAreaType geoType = new GeographicalAreaType(geographicalAreaDTO.getType());
        Location loc = new Location(geographicalAreaDTO.getLatitude(), geographicalAreaDTO.getLongitude(), geographicalAreaDTO.getElevation());
        AreaShape areaShape = new AreaShape(geographicalAreaDTO.getWidth(), geographicalAreaDTO.getLength(), loc);
        return new GeographicalArea(geographicalAreaDTO.getId(), geographicalAreaDTO.getDescription(), geoType, loc, areaShape);
    }


}
