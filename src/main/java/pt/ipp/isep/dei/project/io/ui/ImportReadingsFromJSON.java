package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.importReadingsFromJSONController.ImportReadingsFromJSONController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;

public class ImportReadingsFromJSON {

    private ImportReadingsFromJSONController controller;
    private GeographicalAreaDTO geographicalAreaDTO;

    public ImportReadingsFromJSON(GeographicalAreaList geoList) {
        this.controller = new ImportReadingsFromJSONController(geoList);
    }

    public void run() {



    }
}
