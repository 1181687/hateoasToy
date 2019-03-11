package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicalArea;
import pt.ipp.isep.dei.project.model.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.utils.JSONReader;

import java.util.List;

public class ImportReadingsFromJSONController {

    private GeographicalArea geographicalArea;

    public ImportReadingsFromJSONController() {
        // empty
    }

    public List<GeographicalAreaDTO> readGeoAreaJson(String path) {
        return JSONReader.readJSONFileToList(path);
    }
/*
    public GeographicalAreaDTO setGeographicalArea(List<Object> geoAreaObjects) {
        for (Object geoObject:geoAreaObjects) {
            geoObject.toString();
        }

        GeographicalAreaMapping.mapToEntity(geographicalAreaDTO, this.geographicalArea);
    }
*/
}
