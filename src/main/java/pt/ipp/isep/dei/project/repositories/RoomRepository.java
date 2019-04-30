package pt.ipp.isep.dei.project.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, RoomId> {
    List<Room> findAll();
}
