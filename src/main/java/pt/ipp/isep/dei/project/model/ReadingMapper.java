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

    /**
     * Method that creates a ReadingDTO based on a set of information.
     *
     * @param dateTime Local Date Time to be used.
     * @param value    Double to be used.
     * @return ReadingDTO
     */
    public static ReadingDTO mapToDTO(LocalDateTime dateTime, double value) {
        ReadingDTO readingDTO = new ReadingDTO();
        readingDTO.setDateTime(dateTime);
        readingDTO.setValue(value);
        return readingDTO;
    }
}
