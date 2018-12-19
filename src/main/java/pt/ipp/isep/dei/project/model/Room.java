package pt.ipp.isep.dei.project.model;

public class Room {
    private String mName;
    private int mHouseFloor;
    private Dimensions mDimensions;

    public Room(String mName, int mHouseFloor, Dimensions dimensions) {
        this.mName = mName;
        this.mHouseFloor = mHouseFloor;
        this.mDimensions = dimensions;
    }

    public String getmName() {
        return mName;
    }

    public int getmHouseFloor() {
        return mHouseFloor;
    }

    public Dimensions getmDimensions() {
        return mDimensions;
    }

    public String getRoomDisplay() {
        StringBuilder conteudo = new StringBuilder();
            conteudo.append(" - Name: " + getmName());
            conteudo.append(", House Floor: " + getmHouseFloor());
            conteudo.append(", Dimensions - Height: " + getmDimensions().getmHeight());
            conteudo.append(", Dimensions - Length: " + getmDimensions().getmLength());
            conteudo.append(", Dimensions - Width: " + getmDimensions().getmWidth());
        return conteudo.toString();
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmHouseFloor(int mHouseFloor) {
        this.mHouseFloor = mHouseFloor;
    }
}
