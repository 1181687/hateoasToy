package pt.ipp.isep.dei.project;

import org.springframework.data.repository.CrudRepository;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridId;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, RoomId> {

    List<Room> findAllByHouseGridIdEquals(HouseGridId houseGridId);
}
