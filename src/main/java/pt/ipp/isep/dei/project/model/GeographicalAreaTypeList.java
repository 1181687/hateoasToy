package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class GeographicalAreaTypeList {
    private List<GeographicalAreaType> mGeographicalAreaTypeList = new ArrayList<>();

    /**
     * constructor that receives a list of geographical areas type.
     *
     * @param geographicalAreaTypeList
     */
    public GeographicalAreaTypeList(List<GeographicalAreaType> geographicalAreaTypeList) {
        this.mGeographicalAreaTypeList = geographicalAreaTypeList;
    }

    /**
     * empty constructor of geographical areas type.
     */
    public GeographicalAreaTypeList() {
    }

    /**
     * method that get a geo area type list.
     *
     * @return a geographical areas types list
     */
    public List<GeographicalAreaType> getGeoAreaTypeList() {
        return mGeographicalAreaTypeList;
    }

    /**
     * method that add a type of geographical area.
     * @param geographicalAreaType
     * @return true if a geographicalAreaType is added. If not, return false.
     */
    public boolean addTypeOfGeoAreaToTheList(GeographicalAreaType geographicalAreaType) {
        if (!(mGeographicalAreaTypeList.contains(geographicalAreaType))) {
            mGeographicalAreaTypeList.add(geographicalAreaType);
            return true;
        }
        return false;
    }

    /**
     * method that create a new type of geo area.
     *
     * @param newType
     * @return a new type of geo area.
     */
    public GeographicalAreaType newTypeOfGeoArea(String newType) {
        return new GeographicalAreaType(newType);
    }

    /**
     * method that get the list of geo area types
     * @return a list of geo area types.
     */
    public List<String> getListOfGeoAreaTypes() {
        List<String> listOfGeoAreaTypes = new ArrayList<>();
        for (GeographicalAreaType object : mGeographicalAreaTypeList) {
            listOfGeoAreaTypes.add(object.getStringOfTypeOfGeoArea());
        }
        return listOfGeoAreaTypes;
    }

}

