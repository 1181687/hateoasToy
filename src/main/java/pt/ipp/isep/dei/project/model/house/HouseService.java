package pt.ipp.isep.dei.project.model.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.HouseGridRepository;
import pt.ipp.isep.dei.project.RoomRepository;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;

@Service
public class HouseService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HouseGridRepository houseGridRepository;

    public void mapToEntity(HouseDTO houseDTO, House house) {
        Address houseAddress = AddressMapper.mapToEntity(houseDTO.getAddressDTO());
        house.setAddress(houseAddress);

        for (RoomDTO roomDTO : houseDTO.getRoomDTOList()) {
            Room room = RoomMapper.mapToEntity(roomDTO);
            house.addRoom(room);
            roomRepository.save(room);
        }
        for (HouseGridDTO houseGridDTO : houseDTO.getHouseGridDTOList()) {
            HouseGrid houseGrid = HouseGridMapper.mapToEntity(houseGridDTO);
            house.addGrid(houseGrid);
            houseGridRepository.save(houseGrid);
            for (Room room : houseGrid.getRoomList().getListOfRooms()) {
                addRoomToHouseGrid(houseGrid.getHouseGridName(), room);
            }

        }

    }

    public boolean addRoomToHouseGrid(HouseGridId houseGridId, Room room) {
        if (houseGridRepository.existsById(houseGridId)) {
            room.setHouseGridId(houseGridId);
            roomRepository.save(room);
            return true;
        }
        return false;


    }

    public boolean roomExists(RoomId roomId) {
        return roomRepository.existsById(roomId);
    }

    public boolean gridExists(HouseGridId gridId) {
        return houseGridRepository.existsById(gridId);
    }

}
