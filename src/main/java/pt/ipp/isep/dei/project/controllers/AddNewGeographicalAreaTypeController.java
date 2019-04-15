package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.services.GeoAreaTypeService;

public class AddNewGeographicalAreaTypeController {

    private GeoAreaTypeService geoAreaTypeService;

    public AddNewGeographicalAreaTypeController(GeoAreaTypeService geoAreaTypeService) {
        this.geoAreaTypeService = geoAreaTypeService;
    }

    /**
     * Creates a new geo area type and adds it to the list of available geo area types.
     *
     * @param geoAreaType type
     * @return true or false
     */
    public boolean createAndAddGeoAreaType(String geoAreaType) {
        return geoAreaTypeService.createAndAddGeoAreaType(geoAreaType);
    }
}
