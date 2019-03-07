package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicalArea;
import pt.ipp.isep.dei.project.model.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.GeographicalAreaMapping;

public class ReadJSONfileController {

    private GeographicalArea geographicalArea;

    public ReadJSONfileController(GeographicalArea geographicalArea) {
        this.geographicalArea = geographicalArea;
    }

    /* public GeographicalArea getGeographicalArea() {
        this.geographicalArea = this.geographicalArea
        return geographicalArea;
    } */

    public void setGeographicalArea(GeographicalAreaDTO geographicalAreaDTO) {
        GeographicalAreaMapping.mapToEntity(geographicalAreaDTO, this.geographicalArea);
    }
}
