package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.utils.JSONReaderReadings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class ImportReadingsFromJSONCSVXML {
    /**
     * Method that checks if a file is valid (if it exists) and creates a scanner based on it.
     *
     * @param fileJSON Path of the JSON file.
     * @return Null scanner if there's no such file with the specified name; or a valid scanner if the file exists.
     */
    public FileReader checkIfFileExistsAndCreateFileReader(File fileJSON) {
        FileReader file;
        try {
            file = new FileReader(fileJSON);
        } catch (FileNotFoundException e) {
            file = null;
        }
        return file;
    }

    public void run() {
        String pathFile = InputValidator.getString("Please specify the name of the file you would like to import (extensions accepted: json, csv, xml).");
        File file = new File(pathFile);
        FileReader reader = checkIfFileExistsAndCreateFileReader(file);
        List<Object> objList = JSONReaderReadings.readJSONReadingFileToList(reader);
        System.out.println(objList);
    }
}
