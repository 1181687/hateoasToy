package pt.ipp.isep.dei.project.model.house;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomDTO {

    private String id;
    private String description;
    private int houseFloor;
    private double width;
    private double length;
    private double height;

    private List<RoomDTO> rooms = new ArrayList<>();

    public RoomDTO() {
        // empty
    }

    public String getRoomId() {
        return id;
    }

    public void setRoomId(String id) {
        if (!id.isEmpty()) {
            this.id = id;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String roomName) {
        this.description = roomName;
    }

    public int getHouseFloor() {
        return houseFloor;
    }

    public void setHouseFloor(int houseFloor) {
        this.houseFloor = houseFloor;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }


    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void addRoom(RoomDTO room) {
        this.rooms.add(room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    /**
     * Equals method to determine if two Rooms are equal.
     * They are equals if name (id) are equal.
     * Names are case insensitive.
     *
     * @param obj receives an object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoomDTO)) {
            return false;
        }
        RoomDTO roomOne = (RoomDTO) obj;
        return this.id.equalsIgnoreCase(roomOne.getRoomId());
    }


}

