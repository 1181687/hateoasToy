package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.ReadJSONfileController;
import pt.ipp.isep.dei.project.model.GeographicalArea;
import pt.ipp.isep.dei.project.model.GeographicalAreaDTO;

public class ReadJSONfileUI {

    private ReadJSONfileController controller;
    private GeographicalAreaDTO geographicalAreaDTO;

    public ReadJSONfileUI(GeographicalArea geographicalArea) {
        this.controller = new ReadJSONfileController(geographicalArea);
    }

    public void run() {



    }
}
