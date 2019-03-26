package pt.ipp.isep.dei.project.model;

import java.util.List;

public interface ProjectFileReader {

    String getTypeName();

    List<List<String>> readFile();

}
