package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class GeoAreaList {

    private List<GeographicalArea> mGeoAreaList;

    public GeoAreaList() {
        this.mGeoAreaList = new ArrayList<>();
    }

    public List<GeographicalArea> getmGeoAreaList() {
        return mGeoAreaList;
    }

    public boolean addGeoAreaToTheList(GeographicalArea GeoArea) {
        if (!(mGeoAreaList.contains(GeoArea))) {
            mGeoAreaList.add(GeoArea);
            return true;
        }
        return false;
    }

    public GeographicalArea getGeographicalArea(GeographicalArea geographicalArea) {
        for (GeographicalArea area : mGeoAreaList) {
            if (area.equals(geographicalArea)) {
                return area;
            }
        }
        return null;
    }

    public String getGeographicalAreaNameByPosition(int position) {
        return this.mGeoAreaList.get(position).getNameOfGeoArea();
    }

    public List<String> getListOfGeographicalAreasByType(String type) {
        List<String> GeoAreaListWithSameType = new ArrayList<>();
        for (GeographicalArea areaGeo : mGeoAreaList) {
            if (areaGeo.getGeoAreaType().checkIfOneTypeOfGeoAreaIsEqualToAnotherType(type)) {
                GeoAreaListWithSameType.add(areaGeo.getNameOfGeoArea());
            }
        }
        return GeoAreaListWithSameType;
    }

    public GeographicalArea getGeographicalAreaInTheList(int position) {
        return mGeoAreaList.get(position);
    }

    public boolean checkIfGeoAreaDoesntHaveAnInsertedArea(GeographicalArea area) {
        return area.getInsertedIn() == null;
    }

    public boolean removeGeoAreaFromTheList(GeographicalArea geoArea) {
        return mGeoAreaList.remove(geoArea);
    }

    public void addGeoAreaToTheListInASpecificPosition(int position, GeographicalArea geoArea) {
        mGeoAreaList.add(position, geoArea);
    }

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

    public GeographicalArea newGeographicalArea(String geoAreaName, String geoAreaTypeName, double latitude, double longitude, double altitude, double height, double length) {
        GeoAreaType geoAreaType = new GeoAreaType(geoAreaTypeName);
        Location location = new Location(latitude, longitude, altitude);
        RectangleArea rectangleArea = new RectangleArea(height, length, location);
        return new GeographicalArea(geoAreaName, geoAreaType, location, rectangleArea);
    }
}