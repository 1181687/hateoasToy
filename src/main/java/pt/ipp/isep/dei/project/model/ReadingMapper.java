package pt.ipp.isep.dei.project.model;

public class ReadingMapper {

    /**
     * @return
     */
    public static ReadingDTO newReadingDTO() {
        return new ReadingDTO();
    }

    /**
     * @param readingDTO
     * @return
     */
    public static Reading mapToEntity(ReadingDTO readingDTO) {
        return new Reading(readingDTO.getValue(), readingDTO.getDateTime());
    }
}
