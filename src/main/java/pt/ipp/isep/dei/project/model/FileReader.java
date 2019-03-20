package pt.ipp.isep.dei.project.model;

import java.util.List;

public interface FileReader {


    String getTypeName();

    List<List<String>> readFile();

}
