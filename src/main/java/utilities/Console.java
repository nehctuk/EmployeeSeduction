package utilities;

import menu.Menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
    private static Scanner sc = new Scanner(System.in);

    public static String getString(String prompt) {
        sc = new Scanner(System.in);
        System.out.println(prompt);
        return sc.nextLine();
    }

    public static double getDouble(String prompt) {
        do {
            try {
                sc = new Scanner(System.in);
                System.out.println(prompt);
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                Console.print("Please enter a number.");
                continue;
            }
        }while (true);
    }

    public static int getInt(String prompt) {
        do {
            try {
                sc = new Scanner(System.in);
                System.out.println(prompt);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                Console.print("Please enter a number.");
                continue;
            }
        }while (true);
    }

    public static void print(String input) {
        System.out.println(input);
    }

    public static void quitHRApp() {
        System.exit(0);
    }

    public static void goBack(Menu aMenu) {
        aMenu.display();
    }

    public static String checkYesOrNo() {
        String addMoreInfo;
        do {
            addMoreInfo = Console.getString(
                    "Would you like to add Salary, Benefits, Department, " +
                            "and Position to this employee?('Yes' or 'No')");
            try {
                YesNoException.throwYesNoException(addMoreInfo);
            }catch (Exception e) {
                Console.print("You didn't enter 'Yes' or 'No'.\n");
            }
        } while (!("Yes".equalsIgnoreCase(addMoreInfo) || "No".equalsIgnoreCase(addMoreInfo)));
        return addMoreInfo;
    }

}

