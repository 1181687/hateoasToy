package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.repositories.RoomRepository;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

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
}
