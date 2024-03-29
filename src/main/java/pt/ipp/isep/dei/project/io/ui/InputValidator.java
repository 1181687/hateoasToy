package pt.ipp.isep.dei.project.io.ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public final class InputValidator {

    private InputValidator() {
    }

    /**
     * Method that keeps displaying the string label until the input is a int.
     *
     * @param label string that is showed to the user, indicating what type of input he need to introduce
     * @return a int number
     */
    public static int getInt(String label) {
        Scanner in = new Scanner(System.in);
        int x = -1;
        String errorMessage = "Error! Please insert a integer number.";
        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                x = in.nextInt();
            } catch (Exception e) {
                System.out.println(errorMessage);
                flag = true;
                in.nextLine();
            }
        } while (flag);
        return x;
    }

    /**
     * Method that keeps displaying the string label until the input is a positive int
     *
     * @param label string that is showed to the user, indicating what type of input he need to introduce
     * @return a int positive number
     */
    public static int getIntPos(String label) {
        Scanner in = new Scanner(System.in);
        int x = -1;
        String errorMessageInteger = "Error! Please insert a positive integer number.";
        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                x = in.nextInt();
                if (x < 0) {
                    throw new Exception();
                }

            } catch (Exception e) {
                System.out.println(errorMessageInteger);
                flag = true;
                in.nextLine();
            }
        } while (flag);
        return x;
    }

    /**
     * method that keeps displaying the string label, till the input is a double positive number greater
     * than zero
     *
     * @param label string that is showed to the user, indicating what type of input he need to introduce
     * @return a double positive number
     */
    public static double getDoublePos(String label) {
        Scanner in = new Scanner(System.in);
        double x = -1;
        String positiveNumberMessage = "Error! Please insert a positive number.";
        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                x = in.nextDouble();
                if (x < 0) {
                    throw new Exception();
                }

            } catch (Exception e) {
                System.out.println(positiveNumberMessage);
                flag = true;
                in.nextLine();
            }
        } while (flag);
        return x;
    }

    /**
     * method that keeps displaying the string label, till the input is a integer number between the min and max range
     *
     * @param label string that is showed to the user, indicating what type of input he need to introduce
     * @param min   min range that the input introduced could be
     * @param max   max range that the input introduced could be
     * @return a int number between min and max
     */
    public static int getIntRange(String label, int min, int max) {
        Scanner in = new Scanner(System.in);
        int x = -1;
        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                x = in.nextInt();
                if (x < min || x > max) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Error! Please insert a integer number between " + min + " and " + max + ".");
                flag = true;
                in.nextLine();
            }
        } while (flag);

        return x;
    }

    /**
     * method that keeps displaying the string label, till the input is a double number between the min and max range
     *
     * @param label string that is showed to the user, indicating what type of input he need to introduce
     * @param min   min range that the input introduced could be
     * @param max   max range that the input introduced could be
     * @return a double number between min and max
     */
    public static double getDoubleRange(String label, double min, double max) {
        Scanner in = new Scanner(System.in);
        double x = -1;
        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                x = in.nextDouble();
                if (x < min || x > max) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Error! Please insert a number between " + min + " and " + max + ".");
                flag = true;
                in.nextLine();
            }
        } while (flag);

        return x;
    }

    /**
     * Method that keeps displaying the string label until the input is a non-empty string.
     * if the user write spaces in the beginning or end, the method will trim those spaces.
     *
     * @param label string that is showed to the user, indicating what type of input he need to introduce
     * @return a string that is not empty
     */
    public static String getString(String label) {
        Scanner in = new Scanner(System.in);
        String str = "";
        String emptyNamesMessage = "Error! Empty names are not valid. Please insert a valid name";
        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                str = in.nextLine();
                str = str.trim();
                if (str.length() == 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(emptyNamesMessage);
                flag = true;
            }
        } while (flag);
        return str;
    }

    /**
     * Method to validate the act of confirmation of an action by writing 'y' or 'n'.
     *
     * @param label String that is showed to the user, indicating what shall be written.
     * @return String explaining that only 'y' or 'n' are allowed.
     */
    public static String confirmValidation(String label) {
        Scanner in = new Scanner(System.in);
        String str = "";
        String confirmationMessage = "Error! Please, use 'y' or 'n' to answer.";
        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                str = in.nextLine();
                str = str.trim();
                if (!"y".equalsIgnoreCase(str) && !"n".equalsIgnoreCase(str)) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(confirmationMessage);
                flag = true;
            }
        } while (flag);
        return str;
    }

    /**
     * Method method that keeps displaying the string label, till the input of the date is correct, as in the date
     * format or in a valid date.
     *
     * @param label
     * @return the valid date converted from a String to LocalDate.
     */
    public static LocalDate getStringDate(String label) {
        Scanner in = new Scanner(System.in);
        String str = "";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate returnDate = null;
        format.setLenient(false);

        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                str = in.nextLine();
                str = str.trim();
                format.parse(str);
                returnDate = LocalDate.parse(str);

            } catch (DateTimeParseException | ParseException e) {
                System.out.println("Date " + str + " is not valid. Please insert a valid date according to (" +
                        ((SimpleDateFormat) format).toPattern() + ") pattern.");
                flag = true;
            }

        } while (flag);
        return returnDate;
    }

    /**
     * Method that keeps displaying the string label, till the input of the date and hour is correct.
     *
     * @param label
     * @return the valid date and hour converted from a String to LocalDateTime.
     */

    public static LocalDateTime getStringDateTime(String label) {
        Scanner in = new Scanner(System.in);
        String str = "";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        simpleDateFormat.setLenient(false);

        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                str = in.nextLine();
                str = str.trim();
                simpleDateFormat.parse(str);
            } catch (Exception e) {
                System.out.println("Date " + str + " is not valid. Please insert a valid date according to (yyyy-MM-dd HH:mm) pattern.");
                flag = true;
            }
        } while (flag);

        return LocalDateTime.parse(str, format);
    }


    /**
     * Method that keeps displaying the string label until the input is a double.
     *
     * @param label string that is showed to the user, indicating what type of input he need to introduce
     * @return a int number
     */
    public static double getDouble(String label) {
        Scanner in = new Scanner(System.in);
        double x = -1;
        String errorMessageDouble = "Error! Please insert a decimal number.";
        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                x = in.nextDouble();
            } catch (Exception e) {
                System.out.println(errorMessageDouble);
                flag = true;
                in.nextLine();
            }
        } while (flag);
        return x;
    }

    public static Number getNumber(String label, String dataType) {
        Scanner in = new Scanner(System.in).useLocale(Locale.ENGLISH);
        Number x = -1;
        String errorMessage = "This is an invalid number! Please insert another one.";
        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                String integerValue = "Integer";
                if (dataType.equals(integerValue)) {
                    x = in.nextInt();
                }
                String doubleValue = "Double";
                if (dataType.equals((doubleValue))) {
                    x = in.nextDouble();
                }
            } catch (Exception e) {
                System.out.println(errorMessage);
                flag = true;
                in.nextLine();
            }
        } while (flag);
        return x;
    }
}