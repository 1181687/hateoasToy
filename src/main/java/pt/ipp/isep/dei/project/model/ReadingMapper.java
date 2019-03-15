package pt.ipp.isep.dei.project.model;

import java.util.Objects;

public class ReadingMapper {

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

    public static ReadingDTO mapToDTO(Reading reading) {
        ReadingDTO dto = newReadingDTO();
        dto.setDateTime(reading.getDateTime());
        dto.setValue(reading.getValue());
        return dto;
    }
}
