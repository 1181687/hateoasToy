package pt.ipp.isep.dei.project.model;

public class LocationMapper {

    private LocationMapper(){}

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
