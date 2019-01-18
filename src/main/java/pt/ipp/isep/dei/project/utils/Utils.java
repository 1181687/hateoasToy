package pt.ipp.isep.dei.project.utils;

public class Utils {

    private Utils() {
        //intentionally empty
    }

    /**
     * method that compares two double values.
     *
     * @param value1
     * @param value2
     * @return the comparison between two values.
     */
    public static boolean isSameDouble(double value1, double value2) {
        Double d1 = value1;
        Double d2 = value2;
        return d1.equals(d2);
    }

}
