package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapper;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.util.List;
import java.util.Objects;

public class AddGeoAreaToAnotherGeoAreaController {
    private GeographicalAreaService geographicalAreaService;

    /**
     * construtor of the controller.
     * @param geographicalAreaService
     */
    public AddGeoAreaToAnotherGeoAreaController(GeographicalAreaService geographicalAreaService) {
        this.geographicalAreaService = geographicalAreaService;
    }

    /**
     * method that list the content of the list of geo areas.
     * @param useCriterion
     * @return the content of the list.
     */
    public String getListToString(boolean useCriterion) {
        return geographicalAreaService.getGeoAreaListToString(useCriterion);
    }

    public List<GeographicalAreaDTO> getGeoAreaDTO(){
        return geographicalAreaService.getAllGeoAreaDTO();
    }

    /**
     * get the geographical area selected in the list.
     * @param selectedOption
     * @return the geographical area on list selected by position.
     */
    public GeographicalArea getGeoAreaInTheList(int selectedOption) {
        return geographicalAreaService.getGeographicalAreaInTheList(selectedOption);
    }

    /**
     * method that check if a geo area doesn't have an inserted area.
     * @param geoAreaDto
     * @return null if a geo area doesn't have an inserted area.
     */
    public boolean checkIfGeoAreaDoesntHaveAnInsertedArea(GeographicalAreaDTO geoAreaDto) {
        GeographicalArea geographicalArea = GeographicalAreaMapper.mapToEntity(geoAreaDto);
        return geographicalAreaService.checkIfGeoAreaDoesntHaveAnInsertedArea(geographicalArea);
    }

    /*/**
     * that method add a geo area to the list, in a specific position.
     * @param position
     * @param area
     */
    /*public void addGeoAreaInASpecificPosition(int position, GeographicalArea area) {
        geographicalAreaService.addGeoAreaInASpecificPosition(position, area);
    }*/


    public boolean addParentGeoAreaToMainGeoArea(GeographicalAreaDTO geoAreaDTO, GeographicalAreaDTO parentGeoAreaDTO){
        GeographicalArea geoArea = GeographicalAreaMapper.mapToEntity(geoAreaDTO);
        GeographicalArea parentGeoArea = GeographicalAreaMapper.mapToEntity(parentGeoAreaDTO);
        if(geographicalAreaService.addParentGeoAreaToMainGeoArea(geoArea, parentGeoArea)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * that method remove a geo area from the list of geo areas.
     * @param area
     */
    public void removeGeoArea(GeographicalArea area) {
        geographicalAreaService.removeGeoArea(area);
    }

    /**
     * method that gets the size of the list
     * @return integer
     */
    public int getListSize(){
        return geographicalAreaService.getSize();
    }

    public boolean saveGeoArea(GeographicalAreaDTO geoAreaDTO){
        return geographicalAreaService.saveGeoArea(GeographicalAreaMapper.mapToEntity(geoAreaDTO));
    }
}

