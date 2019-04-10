package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.house.Room;

public interface RoomRepository extends CrudRepository<Room, String> {
}
