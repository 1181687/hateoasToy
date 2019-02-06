package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicalArea;
import pt.ipp.isep.dei.project.model.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.GeographicalAreaTypeList;

import java.util.List;

public class AddGeoAreaController {
    private GeographicalAreaList mGeographicalAreaList;
    private GeographicalAreaTypeList mTGAList;

    /**
     * TODO
     *
     * @param geographicalAreaList
     * @param TGAList
     */
    public AddGeoAreaController(GeographicalAreaList geographicalAreaList, GeographicalAreaTypeList TGAList) {
        this.mGeographicalAreaList = geographicalAreaList;
        this.mTGAList = TGAList;
    }

    /**
     * TODO
     * @param newGA
     * @return
     */
    public boolean addNewGeoArea(GeographicalArea newGA) {
        return mGeographicalAreaList.addGeoArea(newGA);
    }

    /**
     * TODO
     * @return
     */
    public GeographicalAreaList getGeographicalAreaList() {
        return mGeographicalAreaList;
    }

    /**
     * TODO
     * @return
     */
    public List<String> getTGAList() {
        return mTGAList.getListOfGeoAreaTypes();
    }

    /**
     * TODO
     * @param name
     * @param typeName
     * @param altitude
     * @param longitude
     * @param latitude
     * @param width
     * @param height
     * @return
     */
    public GeographicalArea createNewGeoArea(String name, String typeName, double altitude, double longitude, double latitude, double width, double height) {
        return mGeographicalAreaList.newGeographicalArea(name, typeName, altitude, longitude, latitude, width, height);

    }
}
