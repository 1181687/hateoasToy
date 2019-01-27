package pt.ipp.isep.dei.project.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

    public static int getGridMeteringPeriod() {
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream("MeteringGridConfiguration.properties");
        } catch (FileNotFoundException ex) {
            System.out.println("There is no file with that filename.");
        }
        try {
            if (in != null) {
                prop.load(in);
                in.close();
            } else {
                System.out.println("There is no file with that filename.");
            }
        } catch (IOException ex) {
            System.out.println("No info was found.");
        }
        int meteringPeriod = Integer.parseInt(prop.getProperty("MeteringPeriod"));

        if (!(1440 % meteringPeriod == 0)) {
            return -1;
        }
        return meteringPeriod;
    }

    public static boolean isGridMeteringPeriodValid() {
        return 1440 % getGridMeteringPeriod() == 0;
    }

}

/**
 * public static int getDeviceMeteringPeriod() {
 * Properties prop = new Properties();
 * InputStream in = null;
 * try {
 * in = new FileInputStream("MeteringDeviceConfiguration.properties");
 * } catch (FileNotFoundException ex) {
 * System.out.println("There is no file with that filename.");
 * }
 * try {
 * if (in != null) {
 * prop.load(in);
 * in.close();
 * } else {
 * System.out.println("There is no file with that filename.");
 * }
 * } catch (IOException ex) {
 * System.out.println("No info was found.");
 * }
 * int meteringPeriodDevice = Integer.parseInt(prop.getProperty("MeteringPeriodDevice"));
 * int meteringPeriodGrid = Integer.parseInt(prop.getProperty("MeteringPeriod"));
 * <p>
 * if ((!(1440 % meteringPeriodDevice == 0)) && (!(meteringPeriodDevice % meteringPeriodGrid == 0))){
 * return -1;
 * }
 * <p>
 * <p>
 * return meteringPeriodDevice;
 * }
 * <p>
 * public static boolean isDeviceMeteringPeriodValidDay() {
 * return 1440 % getDeviceMeteringPeriod()== 0;
 * }
 * <p>
 * public static boolean isDeviceMeteringPeriodValidGrid() {
 * return getGridMeteringPeriod() % getDeviceMeteringPeriod()== 0;
 * }
 * }
 */
