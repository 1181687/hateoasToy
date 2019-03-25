package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.utils.JSONReader_v2;

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
        String pathJSONFile = InputValidator.getString("Please specify the name of the JSON file to import.");
        File file = new File(pathJSONFile);
        FileReader reader = checkIfFileExistsAndCreateFileReader(file);
        List<Object> objList = JSONReader_v2.readJSONReadingFileToList(reader);
        for (Object object : objList) {
            System.out.println(object);
        }
    }
}
