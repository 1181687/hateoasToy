package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.model.house.AddressDTO;
import pt.ipp.isep.dei.project.model.house.AddressMapper;
import pt.ipp.isep.dei.project.services.HouseService;

import java.util.ArrayList;
import java.util.List;

public class ConfigureHouseLocationController {

    private HouseService houseService;

    public ConfigureHouseLocationController(HouseService houseService) {
        this.houseService = houseService;
    }

    public void configureHouseLocation(AddressDTO addressDTO) {
        houseService.setAddress(AddressMapper.mapToEntity(addressDTO));
    }


    public boolean isGeoAreaRepositoryEmpty() {
        return this.houseService.isGeoAreaRepositoryEmpty();
    }

    public List<GeographicalAreaDTO> getGeoAreaList() {
        List<GeographicalArea> geoAreas = this.houseService.getAllGeoAreas();
        List<GeographicalAreaDTO> geoAreaDTOS = new ArrayList<>();
        for (GeographicalArea geoArea : geoAreas) {
            GeographicalAreaDTO geographicalAreaDTO = GeographicalAreaMapper.mapToDTO(geoArea);
            geoAreaDTOS.add(geographicalAreaDTO);
        }
        return geoAreaDTOS;
    }
}


