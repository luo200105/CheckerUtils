package app.cnode;

import app.cnode.utils.PasswordCheckerService;
import app.cnode.utils.completer.MethodNameCompleter;
import app.cnode.utils.dto.PasswordCheckerDto;
import jdk.nashorn.internal.runtime.regexp.joni.constants.Arguments;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;
public class Main {

    private static PasswordCheckerService passwordCheckerService = new PasswordCheckerService();

    public static void main(String[] args) {
        try {
            Main main = new Main();
            Terminal terminal = TerminalBuilder.terminal();
            LineReader lineReader = LineReaderBuilder.builder().terminal(terminal).build();
            LineReader commandReader = LineReaderBuilder.builder().terminal(terminal).build();

            PasswordCheckerDto dto = new PasswordCheckerDto();

            while (true) {
                String input = lineReader.readLine("Enter a password to check, Or type in Command('list' for list all): ");

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
                    String methodName = main.getLineReader(PasswordCheckerService.class,terminal).readLine("Enter method: ");
                    String arguments = lineReader.readLine("Enter arguments separated by comma: ");
                    // Assuming the method to be invoked has a String parameter for simplicity
                    String[] argsArray = arguments.isEmpty() ? new String[0] : arguments.split(",");
                    // Adjust dynamicMethodInvocation to pass methodName and argsArray
                    Object result = main.dynamicMethodInvocation(methodName, argsArray);
                    System.out.println("Result: " + result);
                    continue;
                }

                // Your password check logic here
                dto.setPassword(input);
                // Assuming passwordCheckerService.checkPassword(dto) is your method to check the password
                dto = passwordCheckerService.checkPassword(dto);

                // Display the result
                System.out.println("Password check result: " + dto.getResult());
                System.out.println("Message: " + dto.getMessage());
                if (!dto.getResult()) {
                    System.out.println("Failed Reason: " + dto.getFailedReason());
                }
                System.out.println(); // Print an empty line for better readability
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public LineReader getLineReader(Class<?> className,Terminal terminal) {
        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(new MethodNameCompleter(className))
                .build();
        return lineReader;
    }
}