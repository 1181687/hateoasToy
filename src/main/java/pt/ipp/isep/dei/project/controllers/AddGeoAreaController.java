package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.GeographicalArea;
import pt.ipp.isep.dei.project.model.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.GeographicalAreaTypeList;

import java.util.List;

public class AddGeoAreaController {
    private GeographicalAreaList mGeographicalAreaList;
    private GeographicalAreaTypeList mTGAList;

    public AddGeoAreaController(GeographicalAreaList geographicalAreaList, GeographicalAreaTypeList TGAList) {
        this.mGeographicalAreaList = geographicalAreaList;
        this.mTGAList = TGAList;
    }

    public boolean addNewGeoArea(GeographicalArea newGA) {
        return mGeographicalAreaList.addGeoArea(newGA);
    }

    public GeographicalAreaList getGeographicalAreaList() {
        return mGeographicalAreaList;
    }

    public List<String> getTGAList() {
        return mTGAList.getListOfGeoAreaTypes();
    }

    public GeographicalArea createNewGeoArea(String name, String typeName, double altitude, double longitude, double latitude, double width, double height) {
        return mGeographicalAreaList.newGeographicalArea(name, typeName, altitude, longitude, latitude, width, height);

    }
}
