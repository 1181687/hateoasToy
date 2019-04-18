package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaRepository;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaTypeId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReading;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReadingId;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.*;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReading;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReadingId;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensor;
import pt.ipp.isep.dei.project.model.sensor.GeoAreaSensorId;
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
    @Autowired
    private GeoAreaSensorReadingsService geoAreaSensorReadingsService;


    /**
     * construtor of the geoAreaService
     */
    public GeoAreaService() {
        // empty
    }

    /**
     * ESTE METODO DEVERIA ESTAR A ADICIONAR JÁ UM OBJECTO. E NÃO A CRIAR UM COM UM COMBOIO DE INFORMAÇÕES
     *
     * NA UI DEVE SER CRIADO UM DTO E FEITO OS SETS NECESSÁRIOS, E DEPOIS PASSAR PARA O CONTROLLER O DTO.
     *
     * NO CONTROLLER PODEMOS ENTÃO MAPEAR PARA UM OBJECTO DO MODELO E TENTAR ADICIONAR.
     *
     * @param geoAreaId
     * @param geoAreaTypeId
     * @param latitude
     * @param longitude
     * @param elevation
     * @param description
     * @param width
     * @param length
     * @return
     */
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

    /**
     * Method that adds a geographical area to the geoAreaRepository.
     * If it doesn't exist in the repository, it adds the area and return true.
     * If it does, then it just returns true
     * @param geographicalArea
     * @return
     */
    public boolean addGeoArea(GeographicalArea geographicalArea){
        if (!geoAreaRepository.existsById(geographicalArea.getId())) {
            geoAreaRepository.save(geographicalArea);
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
    public boolean isGeoAreaRepositoryEmpty() {
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

    /*public boolean addSensorDTO(String id, String sensorName, String sensorTypeId, LocationDTO location, String units) {
        return geoAreaSensorService.addSensorDTO(id, sensorName, sensorTypeId, location, units);
    }*/

    public boolean isNameExistant(String id) {
        return geoAreaSensorService.isNameExistant(id);
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
        GeoAreaTypeId geoAreaTypeId = new GeoAreaTypeId(type);
        return geoAreaRepository.findById_GeographicalAreaTypeId(geoAreaTypeId);
    }

    public GeoAreaSensor getSensorById(GeoAreaSensorId geoAreaSensorId){
        return geoAreaSensorService.getSensorById(geoAreaSensorId);
    }

    public boolean isReadingDuplicated(GeoAreaReadingId geoAreaReadingId){
        return geoAreaSensorReadingsService.isReadingDuplicated(geoAreaReadingId);
    }

    public boolean addReading(GeoAreaReading geoAreaReading){
        return geoAreaSensorReadingsService.addReading(geoAreaReading);
    }


    public List<GeographicalAreaType> listOfGeoAreaTypes() {
        return geoAreaTypeService.getListOfGeoAreaTypes();
    }

    public boolean addGeoAreaSensor(GeoAreaSensor geoAreaSensor){
        return geoAreaSensorService.addSensor(geoAreaSensor);
    }

    public void addAll(List<GeoAreaReading> geoAreaReadings){
        geoAreaSensorReadingsService.addAll(geoAreaReadings);
    }
}