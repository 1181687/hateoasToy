package pt.ipp.isep.dei.project.io.ui;

import java.util.Scanner;

public class InputValidator {

    /**
     * method that keeps displaying the string label, till the input is a int number
     *
     * @param label string that is showed to the user, indicating what type of input he need to introduce
     * @return a int number
     */
    public static int getInt(String label){
        Scanner in = new Scanner(System.in);
        int x = 0;
        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                x = in.nextInt();
            } catch (Exception e) {
                System.out.println("Error! Please insert a valid number.");
                flag = true;
                in.next();
            }
        } while(flag);
        return x;
    }

    /**
     * method that keeps displaying the string label, till the input is a int positive number
     * @param label string that is showed to the user, indicating what type of input he need to introduce
     * @return a int positive number
     */
    public static int getIntPos(String label){
        Scanner in = new Scanner(System.in);
        int x = -1;
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
                System.out.println("Error! Please insert a positive integer number.");
                flag = true;
                in.next();
            }
        } while (flag);
        return x;
    }

    /**
     * method that keeps displaying the string label, till the input is a double positive number greater
     * than zero
     * @param label string that is showed to the user, indicating what type of input he need to introduce
     * @return a double positive number
     */
    public static double getDoublePos(String label) {
        Scanner in = new Scanner(System.in);
        double x = -1;
        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                x = in.nextDouble();
                if (x <= 0) {
                    throw new Exception();
                }

            } catch (Exception e) {
                System.out.println("Error! Please insert a positive number.");
                flag = true;
                in.next();
            }
        }while(flag);
        return x;
    }

    /**
     * method that keeps displaying the string label, till the input is a integer number between the min and max range
     * @param label string that is showed to the user, indicating what type of input he need to introduce
     * @param min min range that the input introduced could be
     * @param max max range that the input introduced could be
     * @return a int number between min and max
     */
    public static int getIntRange(String label, int min , int max){
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
                System.out.println("Error! Please insert a integer number between " + min + " and " + max +".");
                flag = true;
                in.next();
            }
        }while(flag);

        return x;
    }

    /**
     * method that keeps displaying the string label, till the input is a double number between the min and max range
     * @param label string that is showed to the user, indicating what type of input he need to introduce
     * @param min min range that the input introduced could be
     * @param max max range that the input introduced could be
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
     * method that keeps displaying the string label, till the input is a string not empty.
     * if the user write spaces in the beginning or end, the method will trim those spaces.
     * @param label string that is showed to the user, indicating what type of input he need to introduce
     * @return a string that is not empty
     */
    public static String getString(String label) {
        Scanner in = new Scanner(System.in);
        String str="";
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
                System.out.println("Error! Empty names are not valid. Please insert a valid name");
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
        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                str = in.nextLine();
                str = str.trim();
                if (!"y".equals(str) && !"Y".equals(str) && !"n".equals(str) && !"N".equals(str)) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Error! Please, use 'y' or 'n' to answer.");
                flag = true;
            }
        } while (flag);
        return str;
    }
}