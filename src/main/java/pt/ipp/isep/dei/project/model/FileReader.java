package pt.ipp.isep.dei.project.model;

import java.util.List;
import java.util.Objects;

public interface FileReader {


    String getTypeName();

    List<Objects> readFile();

}
