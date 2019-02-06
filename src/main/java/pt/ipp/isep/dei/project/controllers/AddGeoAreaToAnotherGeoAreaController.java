package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicalArea;
import pt.ipp.isep.dei.project.model.GeographicalAreaList;

public class AddGeoAreaToAnotherGeoAreaController {
    private GeographicalAreaList mList;

    /**
     * TODO
     *
     * @param geographicalAreaList
     */
    public AddGeoAreaToAnotherGeoAreaController(GeographicalAreaList geographicalAreaList) {
        this.mList = geographicalAreaList;
    }

    /**
     * TODO
     *
     * @param useCriterion
     * @return
     */
    public String getListToString(boolean useCriterion) {
        return mList.getGeoAreaListToString(useCriterion);
    }

    /**
     * TODO
     *
     * @param selectedOption
     * @return
     */
    public GeographicalArea getGeoAreaInTheList(int selectedOption) {
        return mList.getGeographicalAreaInTheList(selectedOption);
    }

    /**
     * TODO
     *
     * @param geoArea
     * @return
     */
    public boolean checkIfGeoAreaDoesntHaveAnInsertedArea(GeographicalArea geoArea) {
        return mList.checkIfGeoAreaDoesntHaveAnInsertedArea(geoArea);
    }

    /**
     * TODO
     *
     * @param position
     * @param area
     */
    public void addGeoAreaInASpecificPosition(int position, GeographicalArea area) {
        mList.addGeoAreaInASpecificPosition(position, area);
    }

    public void removeGeoArea(GeographicalArea area) {
        mList.removeGeoArea(area);
    }

    public int getListSize(){
        return mList.getSize();
    }
}

