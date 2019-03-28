package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;
import java.util.Objects;

public final class ReadingMapper {

    /**
     * Constructor.
     */
    private ReadingMapper() {
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
     * Method that turns a ReadingDTO into a Reading.
     * @param readingDTO Reading to be used.
     * @return Reading with the required information.
     */
    public static Reading mapToEntity(ReadingDTO readingDTO) {
        if (Objects.isNull(readingDTO)) {
            return null;
        }
        return new Reading(readingDTO.getValue(), readingDTO.getDateTime());
    }

    /**
     * Method that creates a ReadingDTO based on a existing Reading.
     *
     * @param reading Reading to be used.
     * @return ReadingDTO.
     */
    public static ReadingDTO mapToDTO(Reading reading) {
        if (Objects.isNull(reading)) {
            return null;
        }
        ReadingDTO dto = newReadingDTO();
        dto.setDateTime(reading.getDateTime());
        dto.setValue(reading.getValue());
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
