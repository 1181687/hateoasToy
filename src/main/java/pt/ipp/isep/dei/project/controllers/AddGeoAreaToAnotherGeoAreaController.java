package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.*;
;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.util.List;

public class AddGeoAreaToAnotherGeoAreaController {
    private GeographicalAreaService geographicalAreaService;

    /**
     * constructor of the controller.
     *
     * @param geographicalAreaService
     */
    public AddGeoAreaToAnotherGeoAreaController(GeographicalAreaService geographicalAreaService) {
        this.geographicalAreaService = geographicalAreaService;
    }

    public List<GeoAreaIdDTO> getGeoAreaIdDTO() {
        return geographicalAreaService.getAllGeoAreaIdDTO();
    }

    public boolean addParentGeoAreaToMainGeoArea(GeoAreaIdDTO geoAreaIdDTO, GeoAreaIdDTO parentGeoAreaIdDTO) {
        return geographicalAreaService.addParentGeoAreaToMainGeoArea(geoAreaIdDTO, parentGeoAreaIdDTO);
    }

    public boolean isInsertedInNull(GeoAreaIdDTO geoAreaIdDTO) {
        return geographicalAreaService.isInsertedInNull(geoAreaIdDTO);
    }

    public GeoAreaIdDTO getParentGeoAreaId(GeoAreaIdDTO geoAreaIdDTO) {
        return geographicalAreaService.getParentGeoAreaId(geoAreaIdDTO);
    }
}

