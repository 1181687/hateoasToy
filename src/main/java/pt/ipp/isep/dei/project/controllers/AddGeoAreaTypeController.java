package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.services.GeoAreaTypeService;

public class AddGeoAreaTypeController {

    private GeoAreaTypeService geoAreaTypeService;

    public AddGeoAreaTypeController(GeoAreaTypeService geoAreaTypeService) {
        this.geoAreaTypeService = geoAreaTypeService;
    }

    /**
     * Method that creates a new geo area type and adds it to the list of available geo area types.
     *
     * @param geoAreaTypeId Id
     * @return true or false
     */
    public boolean createGeoAreaType(String geoAreaTypeId) {
        return geoAreaTypeService.createGeoAreaType(geoAreaTypeId);
    }

}