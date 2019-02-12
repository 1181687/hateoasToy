package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.GeographicalAreaTypeList;


public class AddGeoAreaTypeController {
    private GeographicalAreaTypeList mList;

    /**
     * this is the construtor of the addGeoAreaTypeController
     * @param list
     */
    public AddGeoAreaTypeController(GeographicalAreaTypeList list) {
        this.mList = list;
    }

    /**
     * method that add a type of geographical area.
     * @param geoAreaType
     * @return true if a geographicalAreaType is added. If not, return false.
     */
    public boolean addTypeOfGeoAreaToTheList(String geoAreaType) {
        GeographicalAreaType newTypeOfGeoArea = mList.newTypeOfGeoArea(geoAreaType);
        return mList.addTypeOfGeoAreaToTheList(newTypeOfGeoArea);
    }

    /**
     * method that get the list of geo area types.
     */
    public GeographicalAreaTypeList getList() {
        return mList;
    }
}

