package pt.ipp.isep.dei.project.model.house;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


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

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoomId)) {
            return false;
        }
        RoomId roomId = (RoomId) obj;
        return this.id.equalsIgnoreCase(roomId.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
