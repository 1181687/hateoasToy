package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicalArea;
import pt.ipp.isep.dei.project.model.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.GeographicalAreaTypeList;

import java.util.List;

public class AddGeoAreaController {
    private GeographicalAreaList geographicalAreaList;
    private GeographicalAreaTypeList geographicalAreaTypeList;

    /**
     * construtor of the controller with geoAreaList and types of GeoAreaList.
     * @param geographicalAreaList
     * @param typesGeoAreaList
     */
    public AddGeoAreaController(GeographicalAreaList geographicalAreaList, GeographicalAreaTypeList typesGeoAreaList) {
        this.geographicalAreaList = geographicalAreaList;
        this.geographicalAreaTypeList = typesGeoAreaList;
    }

    /**
     * method that add a geographical area to the list of geographical areas.
     * @param newGA
     * @return boolean
     */
    public boolean addNewGeoArea(GeographicalArea newGA) {
        return geographicalAreaList.addGeoArea(newGA);
    }

    /**
     * method that get the geo area list.
     */
    public GeographicalAreaList getGeographicalAreaList() {
        return geographicalAreaList;
    }

    /**
     * method that get the list of geo area types
     * @return a list of geo area types.
     */
    public List<String> getTGAList() {
        return geographicalAreaTypeList.getListOfGeoAreaTypes();
    }

    /**
     * method that add a new geographical area with a name, a type, a latitude, a longitue, a altitude, a height, a length.
     * @param name
     * @param typeName
     * @param altitude
     * @param longitude
     * @param latitude
     * @param width
     * @param height
     * @return a new geographical area.
     */
    public GeographicalArea createNewGeoArea(String name, String typeName, double altitude, double longitude, double latitude, double width, double height) {
        return geographicalAreaList.newGeographicalArea(name, typeName, altitude, longitude, latitude, width, height);

    }
}
