package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.LocationMapper;

public final class GeoAreaIdMapper {

    public GeoAreaIdMapper() {
    }

    public static GeoAreaIdDTO newDTO(){
        return new GeoAreaIdDTO();
    }

    public static GeoAreaIdDTO mapToDTO(GeoAreaId id){
        GeoAreaIdDTO dto = newDTO();
        dto.setId(id.getId());
        dto.setGeoAreaType(id.getGeographicalAreaType().getGeoAreaTypeId());
        dto.setLocationDTO(LocationMapper.mapToDTO(id.getLocation()));
        return dto;
    }
}
