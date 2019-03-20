package pt.ipp.isep.dei.project.utils;

import pt.ipp.isep.dei.project.model.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CSVReader implements FileReader {
    private static final char DEFAULT_SEPARATOR = ',';

    private CSVReader() {
        // empty
    }

    @Override
    public String getTypeName() {
        return "";
    }

    @Override
    public List<List<String>> readFile() {
        return null;
    }

    /**
     * Method that checks if the name of a file corresponds to a CSV file.
     *
     * @param fileName Name of the file.
     * @return True or False.
     */
    public boolean isCSVFile(String fileName) {
        return fileName.endsWith(".csv");
    }

    /**
     * Method that parses a CSV line into a list of strings.
     *
     * @param csvLine String corresponding to a line/row in a CSV file.
     * @return List of Strings corresponding to the information of each column.
     */
    private List<String> parseLine(String csvLine) {
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

    /**
     * Method that reads all the content of a CSV file and stores the information in a list.
     *
     * @param scanner Scanner with the information of the file.
     * @return List with lists of Strings corresponding to the information of each line in the file.
     */
    public List<List<String>> readFile(Scanner scanner) {
        List<List<String>> allLines = new ArrayList<>();
        scanner.nextLine();
        while (scanner.hasNext()) {
            List<String> line = parseLine((scanner.nextLine()));
            allLines.add(line);
        }
        return allLines;
    }
}
