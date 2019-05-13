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
     * @return GeoAreaDTO.
     */
    public static GeographicalAreaDTO newDTO() {

        GeographicalAreaDTO geoDTO = new GeographicalAreaDTO();
        return geoDTO;
    }

    /**
     * Method that creates a GeoAreaDTO based on a existing GeoArea.
     *
     * @param geographicalArea GeoArea to be used.
     * @return GeoAreaDTO.
     */
    public static GeographicalAreaDTO mapToDTOwithSensors(GeographicalArea geographicalArea) {
        if (Objects.isNull(geographicalArea)) {
            return null;
        }
        GeographicalAreaDTO geoDTO = new GeographicalAreaDTO();
        geoDTO.setId(geographicalArea.getId().getId());
        geoDTO.setDescription(geographicalArea.getDescription());
        geoDTO.setType(geographicalArea.getGeoAreaType().getTypeId());
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
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId(geographicalAreaDTO.getType());
        GeographicalAreaType geoType = new GeographicalAreaType(geoAreaTypeId);
        Location loc = new Location(geographicalAreaDTO.getLatitude(), geographicalAreaDTO.getLongitude(), geographicalAreaDTO.getElevation());
        AreaShape areaShape = new AreaShape(geographicalAreaDTO.getWidth(), geographicalAreaDTO.getLength());
        GeographicalArea mainGeoArea = new GeographicalArea(geographicalAreaDTO.getId(), geographicalAreaDTO.getDescription(), geoType, loc, areaShape);
        while (Objects.nonNull(geographicalAreaDTO.getParentGeoArea())) {
            mainGeoArea.setInsertedIn((mapToEntity(geographicalAreaDTO.getParentGeoArea())));
            geographicalAreaDTO.setParentGeoArea(null);
        }
        return mainGeoArea;
    }

    public static GeographicalAreaDTO mapToDTO(GeographicalArea geographicalArea) {
        GeographicalAreaDTO geographicalAreaDTO = newGeoAreaDTO();
        geographicalAreaDTO.setId(geographicalArea.getId().getId());
        geographicalAreaDTO.setLongitude(geographicalArea.getLocation().getLongitude());
        geographicalAreaDTO.setLatitude(geographicalArea.getLocation().getLatitude());
        geographicalAreaDTO.setElevation(geographicalArea.getLocation().getElevation());
        geographicalAreaDTO.setDescription(geographicalArea.getDescription());
        geographicalAreaDTO.setLength(geographicalArea.getAreaShape().getLength());
        geographicalAreaDTO.setWidth(geographicalArea.getAreaShape().getWidth());
        geographicalAreaDTO.setType(geographicalArea.getId().getGeographicalAreaType().getTypeId());
        while (Objects.nonNull(geographicalArea.getInsertedIn())) {
            geographicalAreaDTO.setParentGeoArea(mapToDTO(geographicalArea.getInsertedIn()));
            geographicalArea.setInsertedIn(null);
        }
        return geographicalAreaDTO;
    }

}
