package app.cnode;

import app.cnode.utils.PasswordCheckerService;
import app.cnode.utils.dto.PasswordCheckerDto;
import jdk.nashorn.internal.runtime.regexp.joni.constants.Arguments;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;
public class Main {

    private static PasswordCheckerService passwordCheckerService = new PasswordCheckerService();

    public static void main(String[] args) {

        Main main = new Main();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a password to check, type 'exit' to quit, type 'setup' to set up a Password Policy:");
            String input = scanner.nextLine();
            PasswordCheckerDto dto = new PasswordCheckerDto();

            // Exit condition
            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Exiting...");
                break;
            }

            // Setup Password Policy (assuming this is a placeholder for actual implementation)
            if ("setup".equalsIgnoreCase(input)) {
                // Placeholder: Implement your password policy setup logic here
                System.out.println("Setup Password Policy (Not Implemented)");
                continue;
            }

            if ("invoke".equalsIgnoreCase(input)) {
                System.out.println("Enter method:");
                String methodName = scanner.nextLine();
                System.out.println("Enter arguments separated by comma:");
                String arguments = scanner.nextLine();
                // Assuming the method to be invoked has a String parameter for simplicity
                String[] argsArray = arguments.isEmpty() ? new String[0] : arguments.split(",");
                // Adjust dynamicMethodInvocation to pass methodName and argsArray
                Object result = main.dynamicMethodInvocation(methodName, argsArray);
                System.out.println("Result: " + result);
                continue;
            }

            // Assuming a method in your PasswordCheckerService to process dto
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

    public Object dynamicMethodInvocation(String methodName, String[] args) {
        try {
            // Assuming all methods take a single String argument for simplicity
            Method method = PasswordCheckerService.class.getMethod(methodName, String[].class);
            Method[] methods = PasswordCheckerService.class.getMethods();
            // Adjusted to support variable number of arguments; for now, we pass the first argument for demonstration
            Object result = method.invoke(passwordCheckerService, (Object)args);
            System.out.println("Method " + methodName + " invoked successfully.");
            return result;
        } catch (NoSuchMethodException e) {
            System.out.println("The method " + methodName + " does not exist.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}