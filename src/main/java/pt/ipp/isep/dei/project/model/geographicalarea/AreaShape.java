package pt.ipp.isep.dei.project.model.geographicalarea;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.roles.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class AreaShape implements ValueObject {

    private double length;

    private double width;


    /**
     * constructor of AreaShape that receives a width, a length and a locationAreaShape.
     * @param width
     * @param length
     */
    public AreaShape(double width, double length) {
        this.length = length;
        this.width = width;
    }

    protected AreaShape() {
    }

    /**
     * Get method for Width.
     *
     * @return
     */
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Get method for Length.
     *
     * @return
     */
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }


    /**
     * method that creates the hashcode to address
     */
    public int hashCode() {
        return 1;
    }

    /**
     * Equals method to determine if two AreaShape are equal.
     * They are equals if all atributtes (length, width and locationAreaShape) are equal.
     * @param obj
     * @return boolean
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AreaShape)) {
            return false;
        }

        AreaShape local = (AreaShape) obj;
        Double comparablemLength = length;
        Double comparablemWidth = width;

        Double comparableAreaShapemLength = local.length;
        Double comparableAreaShapemWidth = local.width;

        return comparableAreaShapemLength.equals(comparablemLength)
                && comparableAreaShapemWidth.equals(comparablemWidth);
    }

}