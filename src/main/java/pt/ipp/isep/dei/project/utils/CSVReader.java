package pt.ipp.isep.dei.project.utils;

import pt.ipp.isep.dei.project.model.ProjectFileReader;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class CSVReader implements ProjectFileReader {
    private static final char DEFAULT_SEPARATOR = ',';
    private String readerName = "csv";

    public CSVReader() {
        // empty
    }

    @Override
    public String getTypeName() {
        return this.readerName;
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
                result.add(charSet.toString().trim());
                charSet = new StringBuilder();
            } else {
                charSet.append(character);
            }
        }
        result.add(charSet.toString().trim());
        return result;
    }

    /**
     * Method that creates a scanner based on a given file.
     *
     * @param file File to be used in the scanner creation.
     * @return Scanner.
     */
    public Scanner createScanner(File file) {
        Scanner scanner;
        try {
            scanner = new Scanner(file);

        } catch (FileNotFoundException e) {
            scanner = null;
        }
        return scanner;
    }

    /**
     * Method that reads all the content of a CSV file and stores the information in a list.
     *
     * @param file File with the information needed.
     * @return List with lists of Strings corresponding to the information of each line in the file.
     */
    @Override
    public List<Object> readFile(File file) {
        Scanner scanner = createScanner(file);
        if (Objects.isNull(scanner)) {
            return Collections.emptyList();
        }
        List<Object> readingDTOList = new ArrayList<>();
        scanner.nextLine();
        List<List<String>> allLines = new ArrayList<>();
        while (scanner.hasNext()) {
            List<String> line = parseLine((scanner.nextLine()));
            if (!line.isEmpty()) {
                allLines.add(line);
            }
        }
        if (!allLines.isEmpty()) {
            for (List<String> line : allLines) {
                String sensorId = line.get(0);
                String dateTime = line.get(1);
                String value = line.get(2);
                String unit = line.get(3);
                LocalDateTime readingDateTime;
                if (sensorId.contains("RF")) {
                    LocalDate readingDate = LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/uuuu"));
                    readingDateTime = readingDate.atStartOfDay();
                } else {
                    ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTime);
                    readingDateTime = zonedDateTime.toLocalDateTime();
                }
                double readingValue = Double.parseDouble(value);
                ReadingDTO readingDTO = ReadingMapper.mapToDTOwithIDandUnits(sensorId, readingDateTime, readingValue, unit);
                readingDTOList.add(readingDTO);
            }
        }
        return readingDTOList;
    }
}
