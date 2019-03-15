package pt.ipp.isep.dei.project.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class CSVReader {
    private static final char DEFAULT_SEPARATOR = ',';

    private CSVReader() {
        // empty
    }

    /**
     * Method that parses a CSV line into a list of strings.
     *
     * @param csvLine String corresponding to a line/row in a CSV file.
     * @return List of Strings corresponding to the information of each column.
     */
    public static List<String> parseLine(String csvLine) {
        List<String> result = new ArrayList<>();
        if (Objects.isNull(csvLine) || csvLine.isEmpty()) {
            return result;
        }
        StringBuilder charSet = new StringBuilder();
        char[] chars = csvLine.toCharArray();
        for (char character : chars) {
            if (character == DEFAULT_SEPARATOR) {
                    result.add(charSet.toString());
                    charSet = new StringBuilder();
                } else {
                    charSet.append(character);
                }
            }
        result.add(charSet.toString());
        return result;
    }
}
