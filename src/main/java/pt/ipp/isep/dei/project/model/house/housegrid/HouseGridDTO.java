package pt.ipp.isep.dei.project.model.house.housegrid;


public class HouseGridDTO {

    private String id;

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

}
