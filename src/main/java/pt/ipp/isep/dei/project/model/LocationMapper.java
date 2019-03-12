package pt.ipp.isep.dei.project.model;

public class LocationMapper {

    /**
     * Method that creates a new SensorDTO.
     *
     * @return SensorDTO.
     */
    public static LocationDTO newLocationDTO(double latitude, double longitude, double elevation) {
        return new LocationDTO(latitude, longitude, elevation);
    }

    public static Location mapToEntity(LocationDTO locationDTO) {
        return new Location(locationDTO.getLatitude(), locationDTO.getLongitude(), locationDTO.getElevation());
    }

    public static LocationDTO mapToDTO(Location location) {
        return new LocationDTO(location.getLatitude(), location.getLongitude(), location.getElevation());
    }
}
