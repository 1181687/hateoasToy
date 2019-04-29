package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.LocationMapper;

import java.util.Objects;

public final class GeoAreaIdMapper {

    public GeoAreaIdMapper() {
    }

    public static GeoAreaIdDTO newDTO() {
        return new GeoAreaIdDTO();
    }

    public static GeoAreaIdDTO mapToDTO(GeoAreaId id) {
        GeoAreaIdDTO dto = newDTO();
        dto.setId(id.getId());
        dto.setGeoAreaType(id.getGeographicalAreaType().getTypeId());
        dto.setLocationDTO(LocationMapper.mapToDTO(id.getLocation()));
        return dto;
    }

    public static GeoAreaId mapToEntity(GeoAreaIdDTO geoAreaIdDTO) {
        if (Objects.isNull(geoAreaIdDTO)) {
            return null;
        }

        LocationDTO locationDTO = new LocationDTO();
        Location location = LocationMapper.mapToEntity(locationDTO);
        GeographicalAreaType geoAreaType = new GeographicalAreaType();

        return new GeoAreaId(location, geoAreaIdDTO.getId(), geoAreaType);

    }
}
