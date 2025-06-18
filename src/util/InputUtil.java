package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Utility class for handling user input
 */
public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    
    public static String getString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }
    
    public static String getRequiredString(String prompt) {
        String input;
        do {
            input = getString(prompt);
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while (input.isEmpty());
        return input;
    }
    
    public static int getInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static int getIntInRange(String prompt, int min, int max) {
        int input;
        do {
            input = getInt(prompt + " (" + min + "-" + max + ")");
            if (input < min || input > max) {
                System.out.println("Input must be between " + min + " and " + max + ". Please try again.");
            }
        } while (input < min || input > max);
        return input;
    }
    
    public static LocalDate getDate(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " (YYYY-MM-DD): ");
                String input = scanner.nextLine().trim();
                return LocalDate.parse(input, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }
    
    public static LocalTime getTime(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " (HH:MM): ");
                String input = scanner.nextLine().trim();
                return LocalTime.parse(input, TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please use HH:MM (24-hour format).");
            }
        }
    }

    public static LocalDateTime getDateTime(String datePrompt, String timePrompt) {
        LocalDate date = getDate(datePrompt);
        LocalTime time = getTime(timePrompt);
        return LocalDateTime.of(date, time);
    }
    
    public static boolean getYesNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (Y/N): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("Y")) {
                return true;
            } else if (input.equals("N")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }
    }
    
    public static int getMenuChoice(String prompt, String[] options) {
        System.out.println(prompt);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        return getIntInRange("Enter your choice", 1, options.length);
    }
}
