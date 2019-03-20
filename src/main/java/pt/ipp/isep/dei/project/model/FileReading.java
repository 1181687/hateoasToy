package pt.ipp.isep.dei.project.model;

import java.util.List;

public interface FileReading {


    String getTypeName();

    List<String> readFile();

}
