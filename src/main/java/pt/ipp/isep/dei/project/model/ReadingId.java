package pt.ipp.isep.dei.project.model;
/*
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class ReadingId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "SENSOR_ID")
    private String readingId;

    @Column(name = "DATE_TIME")
    private LocalDateTime readingDateTime;

    protected ReadingId(){
        // empty
    }

    public ReadingId(String readingId, LocalDateTime readingDateTime){
        this.readingId = readingId;
        this.readingDateTime = readingDateTime;
    }

    public String getReadingId() {
        return this.readingId;
    }

    public LocalDateTime getReadingDateTime(){
        return this.readingDateTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReadingId)) {
            return false;
        }
        ReadingId readingId = (ReadingId) obj;
        return this.readingId.equalsIgnoreCase(readingId.getReadingId()) && this.readingDateTime.equals(readingId.getReadingDateTime());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

 */
