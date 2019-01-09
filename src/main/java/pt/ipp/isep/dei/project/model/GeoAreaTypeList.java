package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class GeoAreaTypeList {
    private List<GeoAreaType> mGeoAreaTypeList = new ArrayList<>();

    /**
     * constructor that receives a list of geographical areas type.
     * @param mGeoAreaTypeList
     */
    public GeoAreaTypeList(List<GeoAreaType> mGeoAreaTypeList) {
        this.mGeoAreaTypeList = mGeoAreaTypeList;
    }

    /**
     * empty constructor of geographical areas type.
     */
    public GeoAreaTypeList() {
    }

    /**
     * method that get a geo area type list.
     * @return a geographical areas types list
     */
    public List<GeoAreaType> getmGeoAreaTypeList() {
        return mGeoAreaTypeList;
    }

    /**
     * method that add a type of geographical area.
     * @param geoAreaType
     * @return true if a geoAreaType is added. If not, return false.
     */
    public boolean addTypeOfGeoAreaToTheList(GeoAreaType geoAreaType) {
        if (!(mGeoAreaTypeList.contains(geoAreaType))) {
            mGeoAreaTypeList.add(geoAreaType);
            return true;
        }
        return false;
    }

    /**
     * method that create a new type of geo area.
     * @param newType
     * @return a new type of geo area.
     */
    public GeoAreaType newTypeOfGeoArea(String newType) {
        return new GeoAreaType(newType);
    }

    /**
     * method that get the list of geo area types
     * @return a list of geo area types.
     */
    public List<String> getListOfGeoAreaTypes() {
        List<String> listOfGeoAreaTypes = new ArrayList<>();
        for (GeoAreaType object : mGeoAreaTypeList) {
            listOfGeoAreaTypes.add(object.getStringOfTypeOfGeoArea());
        }
        return listOfGeoAreaTypes;
    }

}

