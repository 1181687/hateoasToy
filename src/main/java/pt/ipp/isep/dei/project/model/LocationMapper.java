package pt.ipp.isep.dei.project.model;

public final class LocationMapper {

    protected LocationMapper() {
        // empty
    }

    public static LocationDTO newLocationDTO(){
        return new LocationDTO();
    }

    public static Location mapToEntity(LocationDTO locationDTO) {
        return new Location(locationDTO.getLatitude(), locationDTO.getLongitude(), locationDTO.getElevation());
    }

    public static LocationDTO mapToDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLatitude(location.getLatitude());
        locationDTO.setLongitude(location.getLongitude());
        locationDTO.setElevation(location.getElevation());
        return locationDTO;
    }
}
