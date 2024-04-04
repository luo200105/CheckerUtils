package app.cnode;

import app.cnode.utils.PasswordCheckerService;
import app.cnode.utils.dto.PasswordCheckerDto;

import java.util.Scanner;

public class Main {

    static PasswordCheckerService passwordCheckerService = new PasswordCheckerService();
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a password to check, type 'exit' to quit, type 'setup' to set up an Password Policy:");
            String input = scanner.nextLine();
            PasswordCheckerDto dto = new PasswordCheckerDto();

            // Exit condition
            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Exiting...");
                break;
            }

            // Check the password
            dto.setPassword(input);
            dto = passwordCheckerService.checkPassword(dto);

            // Display the result
            System.out.println("Password check result: " + dto.getResult());
            System.out.println("Message: " + dto.getMessage());
            if (!dto.getResult()) {
                System.out.println("Failed Reason: " + dto.getFailedReason());
            }
            System.out.println(); // Print an empty line for better readability
        }

        scanner.close();

    }
}