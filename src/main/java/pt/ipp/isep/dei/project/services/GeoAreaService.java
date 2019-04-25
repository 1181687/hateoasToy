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




    public List<GeoAreaId> getAllGeoAreasId() {
        Iterable<GeographicalArea> geoAreas = this.geoAreaRepository.findAll();
        List<GeoAreaId> geoAreaIdList = new ArrayList<>();
        for (GeographicalArea geoArea : geoAreas) {
            geoAreaIdList.add(geoArea.getId());
        }
        return geoAreaIdList;
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
/*
    public boolean addGeoAreaSensor(GeoAreaSensor geoAreaSensor) {
        return geoAreaSensorService.addGeoAreaSensor(geoAreaSensor);
    }
    */


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

    public GeoAreaSensor getSensorById(GeoAreaSensorId geoAreaSensorId) {
        return geoAreaSensorService.getSensorById(geoAreaSensorId);
    }

    public boolean isReadingDuplicated(GeoAreaReadingId geoAreaReadingId) {
        return geoAreaSensorReadingsService.isReadingDuplicated(geoAreaReadingId);
    }

    public boolean addReading(GeoAreaReading geoAreaReading) {
        return geoAreaSensorReadingsService.addReading(geoAreaReading);
    }


    public List<GeographicalAreaType> listOfGeoAreaTypes() {
        return geoAreaTypeService.getListOfGeoAreaTypes();
    }


    public GeographicalArea getGeoAreaById(GeoAreaId geoAreaId){
        return geoAreaRepository.findById(geoAreaId).orElse(null);
    }

}