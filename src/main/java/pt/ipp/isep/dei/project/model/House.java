package pt.ipp.isep.dei.project.model;

public class House {
    private RoomList mRoomList;





    public House(RoomList mRoomList) {
        this.mRoomList = mRoomList;
    }

    public boolean addRoomToHouse(Room room){
        return this.mRoomList.addRoomToRoomList(room);
    }

    /**
     *
     * @return
     */
    public RoomList getListOfRoom(){
        return this.mRoomList;
    }

}
