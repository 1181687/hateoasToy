package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicalArea.GeographicalArea;
import pt.ipp.isep.dei.project.model.GeographicalArea.GeographicalAreaList;

public class AddGeoAreaToAnotherGeoAreaController {
    private GeographicalAreaList geographicalAreaList;

    /**
     * construtor of the controller.
     * @param geographicalAreaList
     */
    public AddGeoAreaToAnotherGeoAreaController(GeographicalAreaList geographicalAreaList) {
        this.geographicalAreaList = geographicalAreaList;
    }

    /**
     * method that list the content of the list of geo areas.
     * @param useCriterion
     * @return the content of the list.
     */
    public String getListToString(boolean useCriterion) {
        return geographicalAreaList.getGeoAreaListToString(useCriterion);
    }

    /**
     * get the geographical area selected in the list.
     * @param selectedOption
     * @return the geographical area on list selected by position.
     */
    public GeographicalArea getGeoAreaInTheList(int selectedOption) {
        return geographicalAreaList.getGeographicalAreaInTheList(selectedOption);
    }

    /**
     * method that check if a geo area doesn't have an inserted area.
     * @param geoArea
     * @return null if a geo area doesn't have an inserted area.
     */
    public boolean isGeoAreaInsertedinAnotherArea(GeographicalArea geoArea) {
        return geographicalAreaList.checkIfGeoAreaDoesntHaveAnInsertedArea(geoArea);
    }

    /**
     * that method add a geo area to the list, in a specific position.
     * @param position
     * @param area
     */
    public void addGeoAreaInASpecificPosition(int position, GeographicalArea area) {
        geographicalAreaList.addGeoAreaInASpecificPosition(position, area);
    }

    /**
     * that method remove a geo area from the list of geo areas.
     * @param area
     */
    public void removeGeoArea(GeographicalArea area) {
        geographicalAreaList.removeGeoArea(area);
    }

    /**
     * method that gets the size of the list
     * @return integer
     */
    public int getListSize(){
        return geographicalAreaList.getSize();
    }
}

