package utils;

import java.util.InputMismatchException;
import java.util.Locale;
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
                    System.out.print("\n Please enter a number between " + min + " and " + max + "\n\t\t-> ");
            } catch (InputMismatchException e) {
                System.out.print("\n Please enter a number between " + min + " and " + max + "\n\t\t-> ");
                sc.next();
            }
        }
        return input;
    }

    public String getYesOrNo() {
        String answer = "";

        while (true) {
            try {
                answer = sc.next().toLowerCase(Locale.ROOT);
                if (answer.equals("y") || answer.equals("n"))
                    break;
                else
                    System.out.print("\n Please enter a valid option.\n\t\t-> ");
            } catch (InputMismatchException e) {
                System.out.print("\n Please enter a valid option.\n\t\t-> ");
                sc.next();
            }
        }
        return answer;
    }
}
