package pt.ipp.isep.dei.project.model.readings;

import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;

import java.time.LocalDateTime;
import java.util.Objects;

public final class ReadingMapper {

    /**
     * Constructor.
     */
    protected ReadingMapper() {
        // empty
    }

    /**
     * Method that creates a new ReadingDTO.
     * @return ReadingDTO.
     */
    public static ReadingDTO newReadingDTO() {
        return new ReadingDTO();
    }

    /**
     * Method that turns a ReadingDTO into a GeoAreaReading.
     * @param readingDTO GeoAreaReading to be used.
     * @return GeoAreaReading with the required information.
     */
    public static GeoAreaReading mapToGeoAraReadingEntity(ReadingDTO readingDTO) {
        if (Objects.isNull(readingDTO)) {
            return null;
        }
        GeoAreaSensorId geoAreaSensorId = new GeoAreaSensorId(readingDTO.getId());
        return new GeoAreaReading(geoAreaSensorId,readingDTO.getDateTime(), readingDTO.getValue());
    }

    /**
     * Method that turns a ReadingDTO into a RoomReading.
     * @param readingDTO GeoAreaReading to be used.
     * @return GeoAreaReading with the required information.
     */
    public static RoomReading mapToRoomReadingEntity(ReadingDTO readingDTO) {
        if (Objects.isNull(readingDTO)) {
            return null;
        }
        RoomSensorId roomSensorId = new RoomSensorId(readingDTO.getId());
        RoomReadingId roomReadingId = new RoomReadingId(roomSensorId,readingDTO.getDateTime());
        return new RoomReading(roomReadingId, readingDTO.getValue());
    }

    /**
     * Method that creates a ReadingDTO based on a existing GeoAreaReading.
     *
     * @param geoAreaReading GeoAreaReading to be used.
     * @return ReadingDTO.
     */
    public static ReadingDTO mapToDTO(GeoAreaReading geoAreaReading) {
        if (Objects.isNull(geoAreaReading)) {
            return null;
        }
        ReadingDTO dto = newReadingDTO();
        dto.setDateTime(geoAreaReading.getDateTime());
        dto.setValue(geoAreaReading.getValue());
        return dto;
    }

    public static ReadingDTO mapToDTOwithIDandUnits(String id, LocalDateTime date, double value, String units) {
        ReadingDTO dto = newReadingDTO();
        dto.setId(id);
        dto.setDateTime(date);
        dto.setValue(value);
        dto.setUnits(units);
        return dto;
    }
}
