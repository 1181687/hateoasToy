package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeList;


public class AddGeoAreaTypeController {
    private GeographicalAreaTypeList geographicalAreaTypeList;

    /**
     * this is the construtor of the addGeoAreaTypeController
     *
     * @param list
     */
    public AddGeoAreaTypeController(GeographicalAreaTypeList list) {
        this.geographicalAreaTypeList = list;
    }

    /**
     * method that add a type of geographical area.
     *
     * @param geoAreaType
     * @return true if a geographicalAreaType is added. If not, return false.
     */
    public boolean addTypeOfGeoAreaToTheList(String geoAreaType) {
        GeographicalAreaType newTypeOfGeoArea = geographicalAreaTypeList.newTypeOfGeoArea(geoAreaType);
        return geographicalAreaTypeList.addTypeOfGeoAreaToTheList(newTypeOfGeoArea);
    }

    /**
     * method that get the list of geo area types.
     */
    public GeographicalAreaTypeList getList() {
        return geographicalAreaTypeList;
    }
}

