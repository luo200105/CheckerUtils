package app.cnode.utils;

import app.cnode.utils.constant.PwdFailReasonConst;
import app.cnode.utils.constant.PwdMsgConst;
import app.cnode.utils.dto.PasswordCheckerDto;

public class PasswordCheckerService {

    /**
     * Checks the validity of a password based on various criteria encapsulated within the {@link PasswordCheckerDto} object.
     * The method first checks if the password is null or empty using a utility method from {@code StringTools}. If the password
     * is found to be unusable (either null or empty), the result, message, and failed reason fields of the DTO are updated
     * accordingly, and the DTO is returned.
     * <p>
     * If the password passes the initial null or empty check, the method then checks the password length if the length check
     * is enabled in the DTO. If the password fails the length check, the result, message, and failed reason fields are updated
     * to indicate the failure reason, and the DTO is returned.
     * <p>
     * If the password passes all checks, the result is set to true, and the message is updated to indicate success.
     *
     * @param dto The {@link PasswordCheckerDto} object containing the password to check and the criteria for validation.
     * @return The {@link PasswordCheckerDto} object with the result, message, and failed reason fields updated based on the outcome of the checks.
     */
    public PasswordCheckerDto checkPassword(PasswordCheckerDto dto) {

        // Check if the password is empty or null
        if (StringTools.usable(dto.getPassword())) {
            dto.setResult(false);
            dto.setMessage(PwdMsgConst.EMPTY.getMsg());
            dto.setFailedReason(PwdFailReasonConst.EMPTY.getMsg());
            return dto;
        }

        if (dto.getLengthChecker()) {
            String[] args = {dto.getPassword(), String.valueOf(dto.getMinLength()), String.valueOf(dto.getMaxLength())};
            if (checkLength(args)) {
                dto.setResult(false);
                dto.setMessage(PwdMsgConst.LENGTH_FAIL.getMsg());
                dto.setFailedReason(PwdFailReasonConst.LENGTH_FAIL.getMsg());
                return dto;
            }
        }

        dto.setResult(true);
        dto.setMessage(PwdMsgConst.OK.getMsg());

        return dto;
    }


    /**
     * Checks if the length of a given password is within specified minimum and maximum bounds.
     *
     * @param args An array of {@code String} where:
     *             - args[0] should be the password to check,
     *             - args[1] should be the minimum length as a string,
     *             - args[2] (optional) should be the maximum length as a string. If not provided, defaults to 65535.
     * @return {@code true} if the password's length is within the specified bounds; {@code false} otherwise.
     * @throws IllegalArgumentException If the number of arguments is less than 2 or argument conversion fails.
     */
    public boolean checkLength(String[] args) {
        String password;
        int min;
        int max;
        if (args.length < 2) {
            throw new IllegalArgumentException("At least two arguments are required.");
        }
        else if (args.length == 2) {
            password = args[0];
            min = Integer.parseInt(args[1]);
            max = 65535;
        }
        else if (args.length > 2) {
            password = args[0];
            min = Integer.parseInt(args[1]);
            max = Integer.parseInt(args[2]);
            if (min > max) {
                max = 65535;
            }
        } else {
            throw new IllegalArgumentException("Internal error occurred. Please try again.");
        }
        if (password.length() < min || password.length() > max) {
            return false;
        }
        return true;
    }



    /**
     * Validates if a password contains sequences of consecutive numeric characters, in either increasing or decreasing order, exceeding a specified limit.
     * <p>
     * The method requires at least two input arguments:
     * <ul>
     * <li>The password to be checked.</li>
     * <li>A string representation of the limit for consecutive numeric characters allowed.</li>
     * </ul>
     * An optional third argument can be provided to enable or disable checking for decreasing sequences of numbers:
     * <ul>
     * <li>If the third argument is {@code true} (or if no third argument is provided), the method checks for consecutive numbers in both increasing and decreasing order.</li>
     * <li>If the third argument is {@code false}, the method checks only for increasing sequences of numbers.</li>
     * </ul>
     * If the password contains a sequence of consecutive numeric characters (in the specified direction(s)) longer than the allowed limit, the method returns {@code false}. Otherwise, it returns {@code true}.
     * </p>
     *
     * @param args An array of {@code String} objects containing the password, the limit for consecutive numeric characters, and an optional boolean to check for decreasing sequences.
     * @return {@code true} if the password's sequence of consecutive numeric characters does not exceed the specified limit, {@code false} otherwise.
     * @throws IllegalArgumentException If fewer than two input arguments are provided or if there's an internal error in argument parsing.
     * @throws NumberFormatException If the limit argument is not a valid integer.
     */
    public boolean checkConsecutiveNumbers(String[] args) {
        String password;
        int limit;
        boolean isReverse;

        if (args.length < 2) {
            throw new IllegalArgumentException("At least two arguments are required: Password and limit. The third argument isReverse is optional.");
        } else if (args.length == 2) {
            password = args[0];
            limit = Integer.parseInt(args[1]);
            isReverse = false; // Assuming default should be false for simplicity
        } else {
            password = args[0];
            limit = Integer.parseInt(args[1]);
            isReverse = Boolean.parseBoolean(args[2]);
        }

        // Check for consecutive numbers within the limit
        int count = 1;
        for (int i = 0; i < password.length() - 1; i++) {
            if (Character.isDigit(password.charAt(i)) && Character.isDigit(password.charAt(i + 1))) {
                int diff = password.charAt(i + 1) - password.charAt(i);
                if (diff == 1 || (isReverse && diff == -1)) {
                    count++;
                    if (count > limit) {
                        return false;
                    }
                } else {
                    count = 1; // Reset count if not consecutive
                }
            } else {
                count = 1; // Reset count if either character is not a digit
            }
        }

        return true; // Return true if the password does not exceed the limit of consecutive numbers
    }

    public boolean checkConsecutiveAlphabet(String[] args) {
        String password;
        int limit;
        boolean isReverse;

        if (args.length < 2) {
            throw new IllegalArgumentException("At least two arguments are required: Password and limit.");
        } else if (args.length == 2) {
            password = args[0].toLowerCase(); // Convert password to lowercase to ignore case
            limit = Integer.parseInt(args[1]);
            isReverse = false; // Assuming default should be false for simplicity
        } else if (args.length == 3) {
            password = args[0].toLowerCase(); // Convert password to lowercase to ignore case
            limit = Integer.parseInt(args[1]);
            isReverse = Boolean.parseBoolean(args[2]);
        } else {
            throw new IllegalArgumentException("Too many arguments provided.");
        }

        // Check for continuous alphabet characters within the limit, considering reverse if specified
        int count = 1;
        for (int i = 0; i < password.length() - 1; i++) {
            char currentChar = password.charAt(i);
            char nextChar = password.charAt(i + 1);
            int charDiff = nextChar - currentChar;

            // Check if both characters are alphabetic and either consecutive or reverse consecutive
            if (Character.isLetter(currentChar) && Character.isLetter(nextChar) &&
                    (charDiff == 1 || (isReverse && charDiff == -1))) {
                count++;
                if (count > limit) {
                    return false;
                }
            } else {
                count = 1; // Reset count if current and next characters are not consecutive alphabetically
            }
        }
        return true; // Return true if the password does not exceed the limit of consecutive alphabet characters
    }

    /**
     * Checks if the provided password is found in the KALI password database.
     * This method is a placeholder for the actual implementation that would query the KALI database.
     *
     * @param password The password to check against the KALI database.
     * @return {@code true} if the password is not found in the KALI database; {@code false} otherwise.
     */
    public boolean checkRedis(String password) {
//        boolean hasKey = redisServices.hasKey(password);
        if (false) {
            //与KALI密码库重合
            return false;
        }
        return true;
    }
}
