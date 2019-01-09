package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class GeoAreaList {

    private List<GeographicalArea> mGeoAreaList;

    /**
     * constructor that receives a new list of Geographical Areas.
     */
    public GeoAreaList() {
        this.mGeoAreaList = new ArrayList<>();
    }

    /**
     * method that get a geographical areas list.
     * @return a list of geographical areas
     */
    public List<GeographicalArea> getmGeoAreaList() {
        return mGeoAreaList;
    }

    /**
     * method that add a geographical area to the list of geographical areas.
     * @param GeoArea
     * @return
     */
    public boolean addGeoArea(GeographicalArea GeoArea) {
        if (!(mGeoAreaList.contains(GeoArea))) {
            mGeoAreaList.add(GeoArea);
            return true;
        }
        return false;
    }

    /**
     * get a geographical area of a geographical areas list.
     * @param geographicalArea
     * @return a geoArea if exists on the list. If not, return null.
     */
    public GeographicalArea getGeographicalArea(GeographicalArea geographicalArea) {
        for (GeographicalArea area : mGeoAreaList) {
            if (area.equals(geographicalArea)) {
                return area;
            }
        }
        return null;
    }

    /**
     * get the name of a geographicalArea by the position selected on the list of geographical areas
     * @param position
     * @return the name of a geoArea that is on the position selected on the list.
     */
    public String getGeographicalAreaNameByPosition(int position) {
        return this.mGeoAreaList.get(position).getNameOfGeoArea();
    }

    /**
     * get the list of the geo area type by name.
     * @param geoAreaType
     * @return the geo area type list.
     */
    public List<String> getListOfGeographicalAreasByType(String geoAreaType) {
        List<String> geoAreaListWithSameType = new ArrayList<>();
        for (GeographicalArea areaGeo : mGeoAreaList) {
            if (areaGeo.getGeoAreaType().checkIfOneTypeOfGeoAreaIsEqualToAnotherType(geoAreaType)) {
                geoAreaListWithSameType.add(areaGeo.getNameOfGeoArea());
            }
        }
        return geoAreaListWithSameType;
    }

    /**
     * get the geographical area selected in the list.
     * @param position
     * @return the geographical area on list selected by position.
     */
    public GeographicalArea getGeographicalAreaInTheList(int position) {
        return mGeoAreaList.get(position);
    }

    /**
     * method that check if a geo area doesn't have an inserted area.
     * @param area
     * @return null if a geo area doesn't have an inserted area.
     */
    public boolean checkIfGeoAreaDoesntHaveAnInsertedArea(GeographicalArea area) {
        return area.getInsertedIn() == null;
    }
    /**
     * that method remove a geo area from the list of geo areas.
     * @param geoArea
     */
    public boolean removeGeoArea(GeographicalArea geoArea) {
        return mGeoAreaList.remove(geoArea);
    }

    /**
     * that method add a geo area to the list, in a specific position.
     * @param position
     * @param geoArea
     */
    public void addGeoAreaInASpecificPosition(int position, GeographicalArea geoArea) {
        mGeoAreaList.add(position, geoArea);
    }

    /**
     * method that check if a geo area is inserted in another geo area.
     * @param selection1
     * @param selection2
     * @return true if a geo area is inserted in another. Return false if not.
     */
    public boolean checkIfGeoAreaIsinsertedInAnother(int selection1, int selection2) {
        GeographicalArea firstGeoArea = mGeoAreaList.get(selection1);
        GeographicalArea secondGeoArea = mGeoAreaList.get(selection2);
        while (firstGeoArea.getInsertedIn() != null) {
            if (!firstGeoArea.getInsertedIn().equals(secondGeoArea)) {
                firstGeoArea = firstGeoArea.getInsertedIn();
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * method that list the content of the list of geo areas.
     * @param useCriteria
     * @return the content of the list.
     */
    public String listContent(boolean useCriteria) {
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= mGeoAreaList.size(); i++) {
            content.append(i + " - Nome: " + mGeoAreaList.get(i - 1).getNameOfGeoArea());
            content.append(", Tipo: " + mGeoAreaList.get(i - 1).getGeoAreaType().getStringOfTypeOfGeoArea());
            content.append(", Latitude: " + mGeoAreaList.get(i - 1).getLocation().getmLatitude());
            content.append(", Longitude: " + mGeoAreaList.get(i - 1).getLocation().getmLongitude());
            if (useCriteria && !checkIfGeoAreaDoesntHaveAnInsertedArea(mGeoAreaList.get(i - 1))) {
                content.append(", Inserido Em: " + mGeoAreaList.get(i - 1).getInsertedIn().getGeoAreaType().getStringOfTypeOfGeoArea());
                content.append(" " + mGeoAreaList.get(i - 1).getInsertedIn().getNameOfGeoArea());
            }
            content.append("\n");
        }
        return content.toString();
    }

    /**
     * method that add a new geographical area with a name, a type, a latitude, a longitue, a altitude, a height, a length.
     * @param geoAreaName
     * @param geoAreaTypeName
     * @param latitude
     * @param longitude
     * @param altitude
     * @param height
     * @param length
     * @return a new geographical area.
     */
    public GeographicalArea newGeographicalArea(String geoAreaName, String geoAreaTypeName, double latitude, double longitude, double altitude, double height, double length) {
        GeoAreaType geoAreaType = new GeoAreaType(geoAreaTypeName);
        Location location = new Location(latitude, longitude, altitude);
        AreaShape rectangleArea = new AreaShape(height, length, location);
        return new GeographicalArea(geoAreaName, geoAreaType, location, rectangleArea);
    }
}