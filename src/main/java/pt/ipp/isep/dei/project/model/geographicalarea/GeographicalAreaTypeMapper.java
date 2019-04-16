package pt.ipp.isep.dei.project.model.geographicalarea;

public final class GeographicalAreaTypeMapper {

    /**
     * Constructor.
     */
    protected GeographicalAreaTypeMapper() {
        // empty
    }

    public static GeographicalAreaTypeDTO mapToDTO(GeographicalAreaType geographicalAreaType) {
        return new GeographicalAreaTypeDTO(geographicalAreaType.getGeoAreaType().getGeoAreaTypeId());
    }

    public static GeographicalAreaType mapToEntity(GeographicalAreaTypeDTO geographicalAreaTypeDTO) {
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId(geographicalAreaTypeDTO.getGeoAreaType());
        return new GeographicalAreaType(geoAreaTypeId);
    }
}
