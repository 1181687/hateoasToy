package pt.ipp.isep.dei.project.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public final class Utils {

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

    /**
     * Method that rounds a double to specified number of decimal places.
     *
     * @param value         Value to be rounded.
     * @param decimalPlaces Number of decimal places.
     * @return Double with the rounded value.
     */
    public static double round(double value, int decimalPlaces) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException("Please insert a positive value.");
        }
        long factor = (long) Math.pow(10, decimalPlaces);
        double newValue = value * factor;
        long tmp = Math.round(newValue);
        return (double) tmp / factor;
    }

    public static String readConfigFile(String file, String option) {
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            return ("There is no file with that filename.");
        }
        try {
            prop.load(in);
        } catch (IOException ex) {
            return ("No info was found.");
        }
        String property = prop.getProperty(option);
        if (property == null) {
            return ("Wrong Key");
        }
        return property;
    }

    public static List<String> readConfigFileToList(String file, String count, String property) {
        List<String> readingsList = new ArrayList<>();
        int numberOfProperties = Integer.parseInt(readConfigFile(file, count));
        for (int i = 1; i <= numberOfProperties; i++) {
            String deviceType = Utils.readConfigFile(file, property + "." + i);
            readingsList.add(deviceType);
        }
        return readingsList;
    }

    public static String getDataSeriesToString(Map<LocalDateTime, Double> map) {
        StringBuilder readingsMap = new StringBuilder();
        for (Map.Entry<LocalDateTime, Double> entry : map.entrySet())
            readingsMap.append("Date/hour: " + entry.getKey().toLocalDate().toString() + " " + entry.getKey().toLocalTime().toString() +
                    ", Energy Consumption: " + entry.getValue() + " kWh\n");
        return readingsMap.toString();
    }

    public static boolean isSameNumber(Number value1, Number value2) {
        return value1.equals(value2);
    }

    public static boolean isFirstDoubleBiggerThanSecondOne(Double value1, Double value2) {
        return value1 > value2;
    }
}
