package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.services.GeoAreaService;

public class AddNewGeographicalAreaController {

    private GeoAreaService geoAreaService;

    public AddNewGeographicalAreaController(GeoAreaService geoAreaService) {
        this.geoAreaService = geoAreaService;
    }

    public void addGeographicalArea(String geoAreaId, String geoAreaTypeId, double latitude, double longitude,
                                    double elevation, String description, double width, double length) {
        geoAreaService.addGeographicalArea(geoAreaId, geoAreaTypeId, latitude, longitude, elevation, description, width, length);
    }


    /**
     * method that checks if a geo area exists from a list of geo areas.
     *
     * @param
     */
    public boolean isGeoAreaExistant(String geoAreaId, double latitude, double longitude, double elevation, String geoAreaTypeId) {
        return this.geoAreaService.isGeoAreaExistant(geoAreaId, latitude, longitude, elevation, geoAreaTypeId);
    }

}
