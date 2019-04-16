package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaRepository;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
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
}