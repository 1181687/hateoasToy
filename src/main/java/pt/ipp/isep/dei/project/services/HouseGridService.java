package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;
import pt.ipp.isep.dei.project.repositories.HouseGridRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseGridService {

    @Autowired
    HouseGridRepository houseGridRepository;


    /**
     * gets all Rooms in roomRepository and map them to List<RoomDtos>
     *
     * @return List<RoomDtos>
     */
    public List<HouseGridDTO> getAllHouseGridDTO() {
        List<HouseGridDTO> houseGridDTOList = new ArrayList<>();
        for (HouseGrid houseGrid : houseGridRepository.findAll()) {
            houseGridDTOList.add(HouseGridMapper.mapToDTO(houseGrid));
        }
        return houseGridDTOList;
    }

    public boolean newHouseGrid(HouseGrid houseGrid) {
        if (!houseGridRepository.existsById(houseGrid.getHouseGridName())) {
            houseGridRepository.save(houseGrid);
            return true;
        }
        return false;
    }

}
