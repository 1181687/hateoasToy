package pt.ipp.isep.dei.project.utils;

public final class Utils {

    private Utils() {
        //intentionally empty
    }

    /**
     * method that compares two double values.
     * @param value1
     * @param value2
     * @return the comparison between two values.
     */
    public static boolean isSameDouble(double value1, double value2) {
        Double d1 = value1;
        Double d2 = value2;
        return d1.equals(d2);
    }

    /**
     * Method that rounds a double to specified number of decimal places.
     *
     * @param value         Value to be rounded.
     * @param decimalPlaces Number of decimal places.
     * @return Double with the rounded value.
     */
    public static double round(double value, int decimalPlaces) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException();
        }
        long factor = (long) Math.pow(10, decimalPlaces);
        double newValue = value * factor;
        long tmp = Math.round(newValue);
        return (double) tmp / factor;
    }
}
