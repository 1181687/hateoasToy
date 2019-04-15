package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.sensor.SensorId;

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
    public static GeoAreaReading mapToEntity(ReadingDTO readingDTO) {
        if (Objects.isNull(readingDTO)) {
            return null;
        }
        SensorId sensorId = new SensorId(readingDTO.getId());
        return new GeoAreaReading(sensorId,readingDTO.getDateTime(), readingDTO.getValue());
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
