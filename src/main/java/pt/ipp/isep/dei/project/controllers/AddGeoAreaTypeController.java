package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.GeographicalAreaTypeList;


public class AddGeoAreaTypeController {
    private GeographicalAreaTypeList mList;

    /**
     * TODO
     *
     * @param list
     */
    public AddGeoAreaTypeController(GeographicalAreaTypeList list) {
        this.mList = list;
    }

    /**
     * TODO
     *
     * @param geoAreaType
     * @return
     */
    public boolean addTypeOfGeoAreaToTheList(String geoAreaType) {
        GeographicalAreaType newTypeOfGeoArea = mList.newTypeOfGeoArea(geoAreaType);
        return mList.addTypeOfGeoAreaToTheList(newTypeOfGeoArea);
    }

    /**
     * TODO
     *
     * @return
     */
    public GeographicalAreaTypeList getList() {
        return mList;
    }
}

