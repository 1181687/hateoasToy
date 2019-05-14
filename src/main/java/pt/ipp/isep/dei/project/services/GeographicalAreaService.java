package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomMapper;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorList;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.repositories.GeoAreaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GeographicalAreaService {

    private List<GeographicalArea> geoAreaList;

    @Autowired
    private GeoAreaRepository geoAreaRepository;

    /**
     * constructor that receives a new list of Geographical Areas.
     */
    public GeographicalAreaService() {
        this.geoAreaList = new ArrayList<>();
    }

    /**
     * method that get a geographical areas list.
     *
     * @return a list of geographical areas
     */
    public List<GeographicalArea> getGeoAreaList() {
        return (List<GeographicalArea>) this.geoAreaRepository.findAll();
    }

    /**
     * method that add a geographical area to the list of geographical areas.
     *
     * @param geoArea
     * @return boolean
     */
    public boolean addGeoArea(GeographicalArea geoArea) {
        if (!(geoAreaRepository.existsById(geoArea.getId()))) {
            geoAreaList.add(geoArea);
            geoAreaRepository.save(geoArea);
            return true;
        }
        return false;
    }

    /**
     * get a geographical area of a geographical areas list.
     *
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
     * get the list of the geo area type by name.
     *
     * @param geoAreaType
     * @return the geo area type list.
     */
    public List<String> getListOfGeoAreasTypeToString(String geoAreaType) {
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId(geoAreaType);
        List<String> geoAreaListWithSameType = new ArrayList<>();
        for (GeographicalArea areaGeo : geoAreaRepository.findAll()) {
            if (areaGeo.getGeoAreaType().equals(geoAreaTypeId)) {
                geoAreaListWithSameType.add(areaGeo.getGeoAreaType().getTypeId());
            }
        }
        return geoAreaListWithSameType;
    }

    /**
     * get the geographical area selected in the list.
     *
     * @param position
     * @return the geographical area on list selected by position.
     */
    public GeographicalArea getGeographicalAreaInTheList(int position) {
        return geoAreaList.get(position);
    }

    /**
     * method that check if a geo area doesn't have an inserted area.
     *
     * @param area
     * @return null if a geo area doesn't have an inserted area.
     */
    public boolean checkIfGeoAreaDoesntHaveAnInsertedArea(GeographicalArea area) {
        return area.getInsertedIn() == null;
    }

    /**
     * that method remove a geo area from the list of geo areas.
     *
     * @param geoArea
     */
    public boolean removeGeoArea(GeographicalArea geoArea) {
        return geoAreaList.remove(geoArea);
    }

    /**
     * method that check if a geo area is inserted in another geo area.
     *
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
     *
     * @param useCriteria
     * @return the content of the list.
     */
    public String getGeoAreaListToString(boolean useCriteria) {
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= geoAreaList.size(); i++) {
            content.append(i + " - ID: " + geoAreaList.get(i - 1).getId().getId());
            content.append(", Description: " + geoAreaList.get(i - 1).getDescription());
            content.append(", Type: " + geoAreaList.get(i - 1).getGeoAreaType().getTypeId());
            content.append(", Latitude: " + geoAreaList.get(i - 1).getLocation().getLatitude());
            content.append(", Longitude: " + geoAreaList.get(i - 1).getLocation().getLongitude());
            if (useCriteria && !checkIfGeoAreaDoesntHaveAnInsertedArea(geoAreaList.get(i - 1))) {
                content.append(", Inserted in: " + geoAreaList.get(i - 1).getInsertedIn().getGeoAreaType().getTypeId());
                content.append(" " + geoAreaList.get(i - 1).getInsertedIn().getDescription());
            }
            content.append("\n");
        }
        return content.toString();
    }

    /**
     * method that add a new geographical area with a name, a type, a location, a height, a length.
     *
     * @param geoAreaName
     * @param geoAreaTypeName
     * @param location
     * @param height
     * @param length
     * @return a new geographical area.
     */
    public GeographicalArea newGeographicalArea(String geoID, String geoAreaName, String geoAreaTypeName, Location location, double height, double length) {
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId(geoAreaTypeName);
        GeographicalAreaType geographicalAreaType = new GeographicalAreaType(geoAreaTypeId);
        AreaShape rectangleArea = new AreaShape(height, length);
        GeographicalArea geoArea = new GeographicalArea(geoID, geoAreaName, geographicalAreaType, location, rectangleArea);
        addGeoArea(geoArea);
        return geoArea;
    }

    /**
     * method that gets the size of the list
     *
     * @return integer
     */
    public int getSize() {
        return this.geoAreaList.size();
    }

    /**
     * Method that returns all the sensors present in the all the geo areas in the list.
     *
     * @return GeoAreaSensorList with all the sensors.
     */
    public GeoAreaSensorList getAllSensors() {
        GeoAreaSensorList geoAreaSensorList = new GeoAreaSensorList();
        for (GeographicalArea geoArea : geoAreaRepository.findAll()) {
            geoAreaSensorList.getListOfSensors().addAll(geoArea.getSensorListInTheGeographicArea().getListOfSensors());
        }
        return geoAreaSensorList;
    }

    public boolean checkIfGeoAreaExistsById(String geoAreaId) {
        for (GeographicalArea geoArea : geoAreaRepository.findAll()) {
            if (geoArea.getId().getId().equals(geoAreaId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that returns a geographical area by searching for it by its id. If it's not on the list it returns null.
     *
     * @param geoAreaIdDTO Id of the geographical area.
     * @return Geographical area corresponding to the id (or null).
     */
    public GeographicalArea getGeoAreaById(GeoAreaIdDTO geoAreaIdDTO) {
        GeoAreaId geoAreaId = GeoAreaIdMapper.mapToEntity(geoAreaIdDTO);
        for (GeographicalArea geographicalArea : geoAreaRepository.findAll()) {
            if (geographicalArea.getId().equals(geoAreaId)) {
                return geographicalArea;
            }
        }
        return null;
    }

    public GeoAreaSensor getSensorById(SensorId id) {
        return getAllSensors().getSensorById(id);
    }


    /**
     * Method that saves a list of geographical areas in the repo.
     *
     * @param geoAreaDTOs List of geographical areas to be added.
     */
    public void saveGeoAreas(List<GeographicalAreaDTO> geoAreaDTOs) {
        List<GeographicalArea> geoAreas = new ArrayList<>();
        for (GeographicalAreaDTO geoAreaDTO : geoAreaDTOs) {
            GeographicalArea geoArea = GeographicalAreaMapper.mapToEntity(geoAreaDTO);
            geoAreas.add(geoArea);
        }
        geoAreaRepository.saveAll(geoAreas);
    }

    public List<GeographicalArea> getAllGeoAreas() {
        Iterable<GeographicalArea> geoAreaIterables = this.geoAreaRepository.findAll();
        List<GeographicalArea> geographicalAreaList = new ArrayList<>();
        geoAreaIterables.forEach(geographicalAreaList::add);
        return geographicalAreaList;
    }

    /**
     * method that verify if the grid repository is empty, or not
     *
     * @return a boolean
     */
    public boolean isGeoAreaRepositoryEmpty() {
        return this.geoAreaRepository.count() == 0;
    }

    public boolean isGeoAreaExistant(String geoAreaId, double latitude, double longitude, double elevation, String geoAreaTypeId) {
        Location geoLocation = new Location(latitude, longitude, elevation);
        GeoAreaTypeId geoAreaTypeId1 = new GeoAreaTypeId(geoAreaTypeId);
        return geoAreaRepository.existsById(new GeoAreaId(geoLocation, geoAreaId, new GeographicalAreaType(geoAreaTypeId1)));
    }

    /**
     * Method that checks if a geographical area exists in the repo by its id.
     *
     * @param geoAreaIdDTO Id to the geographical area.
     * @return True or false.
     */
    public boolean geoAreaExists(GeoAreaIdDTO geoAreaIdDTO) {
        GeoAreaId geoAreaId = GeoAreaIdMapper.mapToEntity(geoAreaIdDTO);
        return this.geoAreaRepository.existsById(geoAreaId);
    }

    public List<GeoAreaIdDTO> getAllGeoAreaIdDTO() {
        List<GeoAreaIdDTO> geoAreaDTOIdList = new ArrayList<>();
        for (GeographicalArea geoArea : this.geoAreaRepository.findAll()) {
            geoAreaDTOIdList.add(GeoAreaIdMapper.mapToDTO(geoArea.getId()));
        }
        return geoAreaDTOIdList;
    }

    public boolean isInsertedInNull(GeoAreaIdDTO geoAreaIdDTO) {
        GeoAreaId geoAreaId = GeoAreaIdMapper.mapToEntity(geoAreaIdDTO);
        GeographicalArea geoArea = this.geoAreaRepository.findById(geoAreaId).orElse(null);
        if (Objects.isNull(geoArea.getInsertedIn())) {
            return true;
        } else {
            return false;
        }
    }

    public GeoAreaIdDTO getParentGeoAreaId(GeoAreaIdDTO geoAreaIdDTO) {
        GeoAreaId geoAreaId = GeoAreaIdMapper.mapToEntity(geoAreaIdDTO);
        GeographicalArea geoArea = this.geoAreaRepository.findById(geoAreaId).orElse(null);
        if (Objects.isNull(geoArea) || Objects.isNull(geoArea.getInsertedIn())) {
            return null;
        } else {
            return GeoAreaIdMapper.mapToDTO(geoArea.getInsertedIn().getId());
        }
    }

    public boolean addParentGeoAreaToMainGeoArea(GeoAreaIdDTO geoAreaIdDTO, GeoAreaIdDTO parentGeoAreaIdDTO) {
        if (isInsertedInNull(geoAreaIdDTO)) {
            GeoAreaId geoAreaId = GeoAreaIdMapper.mapToEntity(geoAreaIdDTO);
            GeoAreaId geoParentAreaId = GeoAreaIdMapper.mapToEntity(parentGeoAreaIdDTO);
            GeographicalArea geoArea = geoAreaRepository.findById(geoAreaId).orElse(null);
            GeographicalArea parentGeoArea = geoAreaRepository.findById(geoParentAreaId).orElse(null);
            if (Objects.nonNull(parentGeoArea.getInsertedIn()) && parentGeoArea.getInsertedIn().equals(geoArea)) {
                return false;
            } else {
                geoArea.setInsertedIn(parentGeoArea);
                geoAreaRepository.save(geoArea);
                return true;
            }
        }
        return false;
    }
}