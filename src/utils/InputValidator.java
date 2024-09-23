package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {
    private final Scanner sc;

    public InputValidator(Scanner sc) {
        this.sc = sc;
    }

    public int getValidIntInRange(int min, int max) {
        int input = 0;

        while (true) {
            try{
                input = sc.nextInt();
                if (input >= min && input <= max)
                    break;
                else
                    System.out.println(" Please enter a number between " + min + " and " + max);
            } catch (InputMismatchException e) {
                System.out.println(" Please enter a number between " + min + " and " + max);
                sc.next();
            }
        }
        return input;
    }

    public String getValidFileName(String fname) { return null;}
}
