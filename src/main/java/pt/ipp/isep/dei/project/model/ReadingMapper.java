package pt.ipp.isep.dei.project.model;

public class ReadingMapper {

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
    public static Reading mapToDTO(ReadingDTO readingDTO) {
        return new Reading(readingDTO.getValue(), readingDTO.getDateTime());
    }
}
