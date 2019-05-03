package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.house.RoomMapper;
import pt.ipp.isep.dei.project.repositories.RoomRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    //nao se deve usar este metodo mas o abaixo que retorna RoomDTos
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public boolean isListOfRoomsEmpty(){
        return getAllRooms().isEmpty();
    }

    /**
     * Method that checks if a room exists in the repo by its id.
     *
     * @param roomId Id to be used.
     * @return True or false.
     */
    public boolean roomExists(RoomId roomId) {
        return this.roomRepository.existsById(roomId);
    }

    /**
     * gets all Rooms in roomRepository and map them to List<RoomDtos>
     *
     * @return List<RoomDtos>
     */
    public List<RoomDTO> getAllRoomsDTO() {
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : roomRepository.findAll()) {
            roomDTOList.add(RoomMapper.mapToDTO(room));
        }
        return roomDTOList;
    }
}
