package pt.ipp.isep.dei.project.model;

import java.io.File;
import java.util.List;

public interface ProjectFileReader {

    String getTypeName();

    List<Object> readFile(File file);

}
