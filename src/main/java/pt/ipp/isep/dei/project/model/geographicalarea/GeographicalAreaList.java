package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.GeoAreaService;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;

import java.util.ArrayList;
import java.util.List;

public class GeographicalAreaList {

    private List<GeographicalArea> geoAreaList;


    /**
     * constructor that receives a new list of Geographical Areas.
     */
    public GeographicalAreaList() {
        this.geoAreaList = new ArrayList<>();
    }

    /**
     * method that get a geographical areas list.
     * @return a list of geographical areas
     */
    public List<GeographicalArea> getGeoAreaList() {
        return geoAreaList;
    }

    /**
     * method that add a geographical area to the list of geographical areas.
     * @param geoArea
     * @return boolean
     */
    public boolean addGeoArea(GeographicalArea geoArea) {
        if (!(geoAreaList.contains(geoArea))) {
            geoAreaList.add(geoArea);
            return true;
        }
        return false;
    }

    public boolean addGeoAreaToRepository(GeographicalArea geoArea) {
        if (addGeoArea(geoArea)) {

            GeoAreaService.getInstance().getGeoAreaRepository().save(geoArea);
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
        for (GeographicalArea area : geoAreaList) {
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
        return this.geoAreaList.get(position).getId();
    }

    /**
     * get the list of the geo area type by name.
     * @param geoAreaType
     * @return the geo area type list.
     */
    public List<String> getListOfGeographicalAreasByType(String geoAreaType) {
        List<String> geoAreaListWithSameType = new ArrayList<>();
        for (GeographicalArea areaGeo : geoAreaList) {
            if (areaGeo.getGeoAreaType().checkIfOneTypeOfGeoAreaIsEqualToAnotherType(geoAreaType)) {
                geoAreaListWithSameType.add(areaGeo.getDescription());
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
        return geoAreaList.get(position);
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
        return geoAreaList.remove(geoArea);
    }

    /**
     * that method add a geo area to the list, in a specific position.
     * @param position
     * @param geoArea
     */
    public void addGeoAreaInASpecificPosition(int position, GeographicalArea geoArea) {
        geoAreaList.add(position, geoArea);
    }

    /**
     * method that check if a geo area is inserted in another geo area.
     * @param selection1
     * @param selection2
     * @return true if a geo area is inserted in another. Return false if not.
     */
    public boolean checkIfGeoAreaIsInsertedInAnother(int selection1, int selection2) {
        GeographicalArea firstGeoArea = geoAreaList.get(selection1);
        GeographicalArea secondGeoArea = geoAreaList.get(selection2);
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
    public String getGeoAreaListToString(boolean useCriteria) {
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= geoAreaList.size(); i++) {
            content.append(i + " - ID: " + geoAreaList.get(i - 1).getId());
            content.append(", Description: " + geoAreaList.get(i - 1).getDescription());
            content.append(", Type: " + geoAreaList.get(i - 1).getGeoAreaType().getStringOfTypeOfGeoArea());
            content.append(", Latitude: " + geoAreaList.get(i - 1).getLocation().getLatitude());
            content.append(", Longitude: " + geoAreaList.get(i - 1).getLocation().getLongitude());
            if (useCriteria && !checkIfGeoAreaDoesntHaveAnInsertedArea(geoAreaList.get(i - 1))) {
                content.append(", Inserted in: " + geoAreaList.get(i - 1).getInsertedIn().getGeoAreaType().getStringOfTypeOfGeoArea());
                content.append(" " + geoAreaList.get(i - 1).getInsertedIn().getDescription());
            }
            content.append("\n");
        }
        return content.toString();
    }

    /**
     * method that add a new geographical area with a name, a type, a location, a height, a length.
     * @param geoAreaName
     * @param geoAreaTypeName
     * @param location
     * @param height
     * @param length
     * @return a new geographical area.
     */
    public GeographicalArea newGeographicalArea(String geoID, String geoAreaName, String geoAreaTypeName, Location location, double height, double length) {
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeName);
        AreaShape rectangleArea = new AreaShape(height, length, location);
        return new GeographicalArea(geoID, geoAreaName, geographicalAreaType, location, rectangleArea);
    }

    /**
     * method that gets the size of the list
     * @return integer
     */
    public int getSize() {
        return this.geoAreaList.size();
    }

    /**
     * Method that returns all the sensors present in the all the geo areas in the list.
     *
     * @return SensorList with all the sensors.
     */
    public SensorList getAllSensors() {
        SensorList sensorList = new SensorList();
        for (GeographicalArea geoArea : geoAreaList) {
            sensorList.getListOfSensors().addAll(geoArea.getSensorListInTheGeographicArea().getListOfSensors());
        }
        return sensorList;
    }

    public boolean checkIfGeoAreaExistsById(String geoAreaId) {
        for (GeographicalArea geoArea : geoAreaList) {
            if (geoArea.getId().equals(geoAreaId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that returns a geographical area by searching for it by its id. If it's not on the list it returns null.
     *
     * @param geoAreaId Id of the geographical area.
     * @return Geographical area corresponding to the id (or null).
     */
    public GeographicalArea getGeoAreaById(String geoAreaId) {
        for (GeographicalArea geographicalArea : geoAreaList) {
            if (geographicalArea.getId().equals(geoAreaId)) {
                return geographicalArea;
            }
        }
        return null;
    }

    public Sensor getSensorById(String id) {
        return getAllSensors().getSensorById(id);
    }

    public void updateRepository() {
        for (GeographicalArea geoArea : this.geoAreaList) {
            GeoAreaService.getInstance().getGeoAreaRepository().save(geoArea);
        }
    }
}