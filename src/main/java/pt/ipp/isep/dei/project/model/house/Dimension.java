package pt.ipp.isep.dei.project.model.house;

import pt.ipp.isep.dei.project.utils.Utils;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Dimension {
    private double height;
    private double length;
    private double width;

    /**
     * constructor that receives height, length, width and
     * throw an exception if any of the parameters is invalid.
     * Invalid parameters if they are doubleNan or equal or less than zero
     *
     * @param height given height
     * @param length given length
     * @param width  given width
     */
    public Dimension(double height, double length, double width) {
        validateHeight(height);
        validateLength(length);
        validateWidth(width);
        this.height = height;
        this.length = length;
        this.width = width;
    }

    public Dimension() {
        // empty
    }

    /**
     * method that receives a height and validates it. It can not be doubleNan or equal or less than zero
     * throw an exception if the height is invalid
     *
     * @param height given height
     */
    private static void validateHeight(double height) {
        if (Double.isNaN(height) || Utils.isFirstDoubleSmallerThanOrEqualToSecondOne(height, 0.0)) {
            throw new RuntimeException("Please enter a valid height. Height should be greater than zero");
        }
    }

    /**
     * method that receives a length and validates it. It can not be doubleNan or equal or less than zero
     * throw an exception if the length is invalid
     *
     * @param length given length
     */
    private static void validateLength(double length) {
        if (Double.isNaN(length) || Utils.isFirstDoubleSmallerThanOrEqualToSecondOne(length, 0.0)) {
            throw new RuntimeException("Please enter a valid length. Length should be greater than zero");
        }
    }

    /**
     * method that receives a width and validates it. It can not be doubleNan or equal or less than zero
     * throw an exception if the width is invalid
     *
     * @param width given width
     */
    private static void validateWidth(double width) {
        if (Double.isNaN(width) || Utils.isFirstDoubleSmallerThanOrEqualToSecondOne(width, 0.0)) {
            throw new RuntimeException("Please enter a valid width. Width should be greater than zero");
        }
    }

    /**
     * method that get the height of a dimension
     * @return a height
     */
    public double getHeight() {
        return height;
    }

    /**
     * set the heigth of the dimension
     *
     * @param height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * method that get the length of a dimension
     * @return a length
     */
    public double getLength() {
        return length;
    }

    /**
     * set the length of the dimension
     *
     * @param length
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * method that get the length of a dimension
     * @return a width
     */
    public double getWidth() {
        return width;
    }

    /**
     * set the width of the dimension
     * @param width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * method that creates the same hashcode to dimensions with the same attributes: height, length, and width.
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(height, length, width);
    }


    /**
     * Equals method to determine if two Dimension are equal.
     *
     * @param obj receives an object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Dimension)) {
            return false;
        }
        Dimension dim = (Dimension) obj;
        Double comparableHeight = height;
        Double comparableLength = length;
        Double comparableWidth = width;
        Double comparableDimensionsHeight = dim.height;
        Double comparableDimensionsLength = dim.length;
        Double comparableDimensionsWidth = dim.width;
        return comparableDimensionsHeight.equals(comparableHeight)
                && comparableDimensionsLength.equals(comparableLength)
                && comparableDimensionsWidth.equals(comparableWidth);
    }
}
