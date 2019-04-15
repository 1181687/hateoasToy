package pt.ipp.isep.dei.project.model.house.housegrid;


public class HouseGridDTO {

    private String id;
    //private List<RoomDTO> roomDTOS = new ArrayList<>();

    public HouseGridDTO() {
        //Intentionally empty
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (!id.isEmpty()) {
            this.id = id;
        }
    }

    /*
    public List<RoomDTO> getRoomDTOS() {
        return roomDTOS;
    }

    public void setRoomDTOS(List<RoomDTO> roomDTOS) {
        this.roomDTOS = roomDTOS;
    }

    public void addRoomDTO(RoomDTO room) {
        roomDTOS.add(room);
    }
    */
}
