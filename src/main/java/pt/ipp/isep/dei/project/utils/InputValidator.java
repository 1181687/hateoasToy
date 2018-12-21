package pt.ipp.isep.dei.project.utils;

import java.util.Scanner;

public class InputValidator {

    public static int getInt( String label){
        Scanner in = new Scanner(System.in);
        int x = 0;
        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                x = in.nextInt();
            } catch (Exception e) {
                System.out.println("Erro input!!!");
                flag = true;
                in.next();
            }
        }while(flag);
        return x;
    }
    public static int getIntPos( String label){
        Scanner in = new Scanner(System.in);
        int x = -1;
        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                x = in.nextInt();
            } catch (Exception e) {
                System.out.println("Erro input!!!");
                flag = true;
                in.next();
            }
        }while(flag || x < 0);
        return x;
    }

    public static int getIntRange( String label, int min , int max){
        Scanner in = new Scanner(System.in);
        int x = -1;
        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                x = in.nextInt();
                if(x<min || x>max){
                    System.out.println("Insert number between " + min + " and " + max);
                }
            } catch (Exception e) {
                System.out.println("Erro input!!!");
                flag = true;
                in.next();
            }
        }while(flag || x < min || x > max);

        return x;
    }

    public static String getString( String label) {
        Scanner in = new Scanner(System.in);
        String x="";
        boolean flag;
        do {
            System.out.println(label);
            try {
                flag = false;
                x = in.nextLine();
            } catch (Exception e) {
                System.out.println("Erro input!!!");
                flag = true;
                in.next();
            }
        } while (flag);
        return x;
    }
}
