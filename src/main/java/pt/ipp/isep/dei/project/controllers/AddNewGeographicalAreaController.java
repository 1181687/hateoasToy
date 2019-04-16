package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaType;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeMapper;
import pt.ipp.isep.dei.project.services.GeoAreaService;

import java.util.ArrayList;
import java.util.List;

public class AddNewGeographicalAreaController {

    private GeoAreaService geoAreaService;

    public AddNewGeographicalAreaController(GeoAreaService geoAreaService) {
        this.geoAreaService = geoAreaService;
    }

    public void addGeographicalArea(String geoAreaId, String geoAreaTypeId, double latitude, double longitude,
                                    double elevation, String description, double width, double length) {
        geoAreaService.addGeographicalArea(geoAreaId, geoAreaTypeId, latitude, longitude, elevation, description, width, length);
    }


    /**
     * method that checks if a geo area exists.
     *
     * @param
     */
    public boolean isGeoAreaExistant(String geoAreaId, double latitude, double longitude, double elevation, String geoAreaTypeId) {
        return this.geoAreaService.isGeoAreaExistant(geoAreaId, latitude, longitude, elevation, geoAreaTypeId);
    }

    public List<GeographicalAreaTypeDTO> getGeoAreaTypeList() {
        List<GeographicalAreaType> geoAreaTypes = this.geoAreaService.listOfGeoAreaTypes();
        List<GeographicalAreaTypeDTO> geoAreaTypesDTOS = new ArrayList<>();
        for (GeographicalAreaType geoAreaType : geoAreaTypes) {
            GeographicalAreaTypeDTO geographicalAreaTypeDTO = GeographicalAreaTypeMapper.mapToDTO(geoAreaType);
            geoAreaTypesDTOS.add(geographicalAreaTypeDTO);
        }
        return geoAreaTypesDTOS;
    }
}
