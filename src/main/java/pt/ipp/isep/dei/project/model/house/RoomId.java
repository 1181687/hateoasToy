package pt.ipp.isep.dei.project.model.house;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class RoomId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;


    public RoomId(String id) {
        this.id = id;
    }

    protected RoomId() {
        //empty
    }

    public String getId() {
        return id;
    }
}
