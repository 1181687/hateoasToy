package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaRepository;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.util.ArrayList;
import java.util.List;


@Service
public class GeoAreaService {

    // Repository
    @Autowired
    private GeoAreaRepository geoAreaRepository;

    // Services
    @Autowired
    private GeoAreaTypeService geoAreaTypeService;
    @Autowired
    private GeoAreaSensorService geoAreaSensorService;


    /**
     * construtor of the geoAreaService
     */
    public GeoAreaService() {
        // empty
    }


    public boolean addGeographicalArea(String geoAreaId, String geoAreaTypeId, double latitude, double longitude, double elevation, String description, double width, double length) {
        Location geoLocation = new Location(latitude, longitude, elevation);
        GeoAreaTypeId geographicalAreaTypeId = new GeoAreaTypeId(geoAreaTypeId);
        GeoAreaId geographicalAreaId = new GeoAreaId(geoAreaId, geoLocation, geographicalAreaTypeId);
        AreaShape areaShape = new AreaShape(width, length);
        GeographicalArea geoArea = new GeographicalArea(geographicalAreaId, description, areaShape);
        if (!geoAreaRepository.existsById(new GeoAreaId(geoAreaId, geoLocation, geographicalAreaTypeId))) {
            geoAreaRepository.save(geoArea);
            return true;
        }
        return false;
    }


    public boolean isGeoAreaExistant(String geoAreaId, double latitude, double longitude, double elevation, String geoAreaTypeId) {
        Location geoLocation = new Location(latitude, longitude, elevation);
        GeoAreaTypeId geographicalAreaTypeId = new GeoAreaTypeId(geoAreaTypeId);
        return geoAreaRepository.existsById(new GeoAreaId(geoAreaId, geoLocation, geographicalAreaTypeId));

    }

    /*
     *//**
     * method that add a geographical area to the list of geographical areas.
     * @param geoArea
     * @return boolean
     *//*
    public boolean addGeoArea(GeographicalArea geoArea) {
        if (!(geoAreaRepository.existsById(geoArea.getId()))) {
            geoAreaList.add(geoArea);
            geoAreaRepository.save(geoArea);
            return true;
        }
        return false;
    }

    */

    /**
     * get a geographical area of a geographical areas list.
     *
     * @param
     * @return a geoArea if exists on the list. If not, return null.
     *//*
    public GeographicalArea getGeographicalArea(GeographicalArea geographicalArea) {
        for (GeographicalArea area : geoAreaList) {
            if (area.equals(geographicalArea)) {
                return area;
            }
        }
        return null;
    /**
     * method that get a list of all geographical areas
     *
     * @return a list of geo areas
     */
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
    public boolean isGridRepositoryEmpty() {
        return this.geoAreaRepository.count() == 0;
    }

    /**
     * method that get a list of sensor types
     *
     * @return a list of sensor types.
     */
    public List<SensorType> getSensorTypeList() {
        return geoAreaSensorService.getSensorTypeList();
    }

    /**
     * method that gel a list of all geo area types
     *
     * @return a list of geo area types
     */
    public List<GeographicalAreaType> getListOfGeoAreaTypes() {
        return geoAreaTypeService.getListOfGeoAreaTypes();
    }

    /**
     * method that get a list of geo areas by type
     *
     * @param type
     * @return a list of geo areas by type
     */
    public List<GeographicalArea> getGeoAreasByType(String type) {
        List<GeographicalArea> geographicalAreas = new ArrayList<>();
        for (GeographicalArea geographicalArea : geoAreaRepository.findAll()) {
            if (geographicalArea.getId().getGeographicalAreaType().getGeoAreaTypeId().equals(type)) {
                geographicalAreas.add(geographicalArea);
            }
        }
        return geographicalAreas;
    }

    public List<GeographicalAreaType> listOfGeoAreaTypes() {
        return geoAreaTypeService.getListOfGeoAreaTypes();
    }
}