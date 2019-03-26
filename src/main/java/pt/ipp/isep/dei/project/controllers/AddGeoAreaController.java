package pt.ipp.isep.dei.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeList;

import java.util.List;

public class AddGeoAreaController {
    @Autowired
    private GeographicalAreaList geographicalAreaList;
    @Autowired
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
    public List<String> getGeoAreaList() {
        return geographicalAreaTypeList.getListOfGeoAreaTypes();
    }

    /**
     * method that add a new geographical area with a name, a type, a latitude, a longitue, a altitude, a height, a length.
     * @param description
     * @param typeName
     * @param location
     * @param height
     * @param length
     * @return a new geographical area.
     */
    public GeographicalArea createNewGeoArea(String id, String description, String typeName, Location location, double height, double length) {
        return geographicalAreaList.newGeographicalArea(id, description, typeName, location, height, length);

    }

    public Location createLocation(double latitude, double longitude, double elevation) {
        return new Location(latitude, longitude, elevation);

    }
}
