package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class GeoAreaTypeList {
    private List<GeoAreaType> mGeoAreaTypeList = new ArrayList<>();

    public GeoAreaTypeList(List<GeoAreaType> mGeoAreaTypeList) {
        this.mGeoAreaTypeList = mGeoAreaTypeList;
    }

    public GeoAreaTypeList() {
    }

    public List<GeoAreaType> getmGeoAreaTypeList() {
        return mGeoAreaTypeList;
    }

    public boolean addTypeOfGeoAreaToTheList(GeoAreaType GeoAreaType) {
        if (!(mGeoAreaTypeList.contains(GeoAreaType))) {
            mGeoAreaTypeList.add(GeoAreaType);
            return true;
        }
        return false;
    }

    public GeoAreaType newTypeOfGeoArea(String newType) {
        return new GeoAreaType(newType);
    }

    public List<String> getListOfGeoAreaTypes() {
        List<String> listOfGeoAreaTypes = new ArrayList<>();
        for (GeoAreaType object : mGeoAreaTypeList) {
            listOfGeoAreaTypes.add(object.getStringOfTypeOfGeoArea());
        }
        return listOfGeoAreaTypes;
    }

}

