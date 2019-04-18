package pt.ipp.isep.dei.project.model.readings;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class RoomReading {

    @EmbeddedId
    private RoomReadingId roomReadingId;
    private double value;

    /**
     * constructor that receives a value and a date
     *
     * @param value    value
     *
     */
    public RoomReading(RoomReadingId roomReadingId,double value) {
        this.value = value;
        this.roomReadingId=roomReadingId;
    }

    protected RoomReading() {
        // empty
    }

    /**
     * Get method
     *
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * gets roomReadingID
     * @return roomReadingId object
     */
    public RoomReadingId getRoomReadingId() {
        return roomReadingId;
    }

    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Method that creates a hashcode.
     *
     * @return Created hashcode.
     */
    @Override
    public int hashCode() {
        return 1;
    }

    /**
     * Method that determines if two readings are equal.
     *
     * @param obj Object used.
     * @return True or false.
     */
    /*@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeoAreaReading)) {
            return false;
        }
        RoomReading reading = (RoomReading) obj;
        Double valueOfTheReading = this.value;
        return valueOfTheReading.equals(reading.getValue()) && this.dateTime.equals(reading.getDateTime());
    }*/
}
