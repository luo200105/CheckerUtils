package app.cnode.utils;

import app.cnode.utils.constant.PwdFailReasonConst;
import app.cnode.utils.constant.PwdMsgConst;
import app.cnode.utils.dto.PasswordCheckerDto;

import java.util.List;

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
        } else if (args.length == 2) {
            password = args[0];
            min = Integer.parseInt(args[1]);
            max = 65535;
        } else if (args.length > 2) {
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
     * @throws NumberFormatException    If the limit argument is not a valid integer.
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
        } else if (args.length == 3) {
            password = args[0];
            limit = Integer.parseInt(args[1]);
            isReverse = Boolean.parseBoolean(args[2]);
        } else {
            throw new IllegalArgumentException("Too many arguments provided.");
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

    /**
     * Determines if a password contains more consecutive alphabetical characters than a specified limit, accounting for case insensitivity and optionally reverse sequences.
     * <p>
     * Accepts input arguments:
     * <ul>
     * <li>The password to check.</li>
     * <li>A string representation of the maximum number of allowed consecutive alphabetical characters.</li>
     * <li>An optional boolean indicating whether to check for consecutive characters in reverse alphabetical order (defaults to {@code false} if not provided).</li>
     * </ul>
     * This method performs case-insensitive checks by converting the password to lowercase. It validates whether the password contains sequences of consecutive alphabetical characters, either ascending (e.g., abc) or, if specified, descending (e.g., cba), that exceed the limit.
     * </p>
     * <p>
     * A sequence is considered consecutive if each character directly follows the previous character in the alphabet, with the option to also consider reverse sequences based on the input parameter.
     * </p>
     *
     * @param args An array of {@code String} objects containing the password, the consecutive character limit, and an optional argument to enable reverse sequence checks.
     * @return {@code true} if the password does not contain consecutive alphabetical sequences exceeding the specified limit, {@code false} otherwise.
     * @throws IllegalArgumentException If fewer than two arguments are provided or if an incorrect number of arguments is given.
     * @throws NumberFormatException    If the limit is not a valid integer.
     */
    public boolean checkConsecutiveAlphabets(String[] args) {
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
     * Validates whether a password contains sequences of identical digits longer than a specified limit.
     * <p>
     * This method checks the given password for sequences of the same digit (e.g., 111 in "a111b"). If any such sequence is longer than the 'limit' parameter specifies, the method returns {@code false}. The check is performed only on numeric characters; other characters are ignored.
     * </p>
     * <p>
     * The method expects two arguments:
     * <ul>
     * <li>The password to be checked.</li>
     * <li>A string representation of the limit for consecutive identical digits.</li>
     * </ul>
     * This enables specifying the maximum length of a sequence of identical digits that is considered acceptable within the password.
     * </p>
     *
     * @param args An array of {@code String} objects containing the password and the limit for consecutive identical digits.
     * @return {@code true} if the password does not contain sequences of the same digit exceeding the specified limit, {@code false} otherwise.
     * @throws IllegalArgumentException If fewer than two arguments are provided or if an incorrect number of arguments is given.
     * @throws NumberFormatException    If the limit is not a valid integer.
     */
    public boolean checkSameNumbers(String[] args) {
        String password;
        int limit;

        if (args.length < 2) {
            throw new IllegalArgumentException("At least two arguments are required: Password and limit.");
        } else if (args.length == 2) {
            password = args[0];
            limit = Integer.parseInt(args[1]);
        } else {
            throw new IllegalArgumentException("Too many arguments provided.");
        }

        // Check for sequences of the same number exceeding the limit
        int count = 1; // Start with a count of 1 to account for the current digit
        for (int i = 0; i < password.length() - 1; i++) {
            if (Character.isDigit(password.charAt(i))) {
                if (password.charAt(i) == password.charAt(i + 1)) {
                    // If the current digit is the same as the next, increment the count
                    count++;
                    if (count > limit) {
                        // If the count exceeds the limit, return false
                        return false;
                    }
                } else {
                    // Reset the count if the current digit is not the same as the next
                    count = 1;
                }
            }
        }

        return true; // Return true if no sequence of the same number exceeds the limit
    }

    /**
     * Validates whether a password contains sequences of the same alphabetical character longer than a specified limit.
     * <p>
     * This method inspects the provided password for sequences of identical letters (e.g., "aaa" in "baaart"). If any sequence of the same letter exceeds the length specified by the 'limit' parameter, the method returns {@code false}. The comparison is case-insensitive, as the password is converted to lowercase before processing.
     * </p>
     * <p>
     * The method expects two arguments:
     * <ul>
     * <li>The password to be checked.</li>
     * <li>A string representation of the limit for consecutive identical letters.</li>
     * </ul>
     * This setup allows defining the maximum acceptable length of a sequence of identical letters within the password.
     * </p>
     *
     * @param args An array of {@code String} objects containing the password and the limit for consecutive identical letters.
     * @return {@code true} if the password does not contain sequences of the same letter exceeding the specified limit, {@code false} otherwise.
     * @throws IllegalArgumentException If fewer than two arguments are provided or if an incorrect number of arguments is given.
     * @throws NumberFormatException    If the limit is not a valid integer.
     */
    public boolean checkSameAlphabets(String[] args) {
        String password;
        int limit;

        if (args.length < 2) {
            throw new IllegalArgumentException("At least two arguments are required: Password and limit.");
        } else if (args.length == 2) {
            password = args[0].toLowerCase(); // Normalize case to make comparison case-insensitive
            limit = Integer.parseInt(args[1]);
        } else {
            throw new IllegalArgumentException("Too many arguments provided.");
        }

        // Check for sequences of the same letter exceeding the limit
        int count = 1; // Start with a count of 1 to account for the current character
        for (int i = 0; i < password.length() - 1; i++) {
            // Check if the current character is alphabetic to ensure we're comparing letters
            if (Character.isLetter(password.charAt(i))) {
                if (password.charAt(i) == password.charAt(i + 1)) {
                    // If the current letter is the same as the next, increment the count
                    count++;
                    if (count > limit) {
                        // If the count exceeds the limit, return false
                        return false;
                    }
                } else {
                    // Reset the count if the current letter is not the same as the next
                    count = 1;
                }
            }
        }

        return true; // Return true if no sequence of the same letter exceeds the limit
    }

    /**
     * Determines if a password contains sequences of the same symbol longer than a specified limit.
     * <p>
     * This method checks the given password for sequences of identical non-alphanumeric characters (symbols). If any such sequence exceeds the length specified by the 'limit' parameter, the method returns {@code false}. This check ignores letters and digits, focusing solely on symbols.
     * </p>
     * <p>
     * The method expects two arguments:
     * <ul>
     * <li>The password to be checked.</li>
     * <li>A string representation of the limit for consecutive identical symbols.</li>
     * </ul>
     * By providing these arguments, it's possible to define the maximum length of a sequence of identical symbols that is deemed acceptable within the password.
     * </p>
     *
     * @param args An array of {@code String} objects containing the password and the limit for consecutive identical symbols.
     * @return {@code true} if the password does not contain sequences of the same symbol exceeding the specified limit, {@code false} otherwise.
     * @throws IllegalArgumentException If fewer than two arguments are provided or if an incorrect number of arguments is given.
     * @throws NumberFormatException    If the limit is not a valid integer.
     */
    public boolean checkSameSymbols(String[] args) {
        String password;
        int limit;

        if (args.length < 2) {
            throw new IllegalArgumentException("At least two arguments are required: Password and limit.");
        } else if (args.length == 2) {
            password = args[0]; // No need to normalize case for symbols
            limit = Integer.parseInt(args[1]);
        } else {
            throw new IllegalArgumentException("Too many arguments provided.");
        }

        // Check for sequences of the same symbol exceeding the limit
        int count = 1; // Start with a count of 1 to account for the current character
        for (int i = 0; i < password.length() - 1; i++) {
            char currentChar = password.charAt(i);
            char nextChar = password.charAt(i + 1);

            // Check if the current character is not a letter or digit (thus, a symbol)
            if (!Character.isLetterOrDigit(currentChar)) {
                if (currentChar == nextChar) {
                    // If the current symbol is the same as the next, increment the count
                    count++;
                    if (count > limit) {
                        // If the count exceeds the limit, return false
                        return false;
                    }
                } else {
                    // Reset the count if the current symbol is not the same as the next
                    count = 1;
                }
            }
        }

        return true; // Return true if no sequence of the same symbol exceeds the limit
    }

    /**
     * Validates whether a password contains linear sequences of characters, either forward or reverse, based on a specified keyboard layout, exceeding a given limit.
     * <p>
     * The method examines the password for linear sequences of alphabetical characters following the order of keys on a keyboard layout, such as "asdf" on a QWERTY layout. It checks for both forward (e.g., "abc") and, if specified, reverse (e.g., "cba") sequences against a limit. If any sequence exceeds this limit, the method returns {@code false}. The keyboard layout can be specified, with "QWERTY" being the default.
     * </p>
     * <p>
     * Expected arguments are:
     * <ul>
     * <li>The password to check, converted to lowercase for case-insensitive comparison.</li>
     * <li>A string representation of the limit for consecutive characters in a sequence.</li>
     * <li>An optional boolean indicating whether to check for reverse sequences (defaults to {@code false} if not provided).</li>
     * <li>An optional keyboard layout (e.g., "QWERTY", "AZERTY"). If not provided, "QWERTY" is the default layout.</li>
     * </ul>
     * Supported layouts include QWERTY, AZERTY, QWERTZ, DVORAK, and COLEMAK. An exception is thrown for unsupported layouts.
     * </p>
     *
     * @param args An array of {@code String} objects containing the password, the consecutive character limit, an optional boolean for reverse sequence checks, and an optional keyboard layout.
     * @return {@code true} if the password does not contain sequences exceeding the specified limit based on the chosen layout and direction(s), {@code false} otherwise.
     * @throws IllegalArgumentException If the number of arguments is incorrect, if the limit is not a valid integer, or if an unsupported keyboard layout is specified.
     */
    public boolean checkLinearAlphabet(String[] args) {
        String password;
        int limit;
        boolean isReverse;
        String layout;
        if (args.length < 2) {
            throw new IllegalArgumentException("At least two arguments are required: Password and Limit.");
        } else if (args.length == 2) {
            password = args[0].toLowerCase();
            limit = Integer.parseInt(args[1]);
            isReverse = false;
            layout = "QWERTY";
        } else if (args.length == 3) {
            password = args[0].toLowerCase();
            limit = Integer.parseInt(args[1]);
            isReverse = Boolean.parseBoolean(args[2]);
            layout = "QWERTY";
        } else if (args.length == 4) {
            password = args[0].toLowerCase();
            limit = Integer.parseInt(args[1]);
            isReverse = Boolean.parseBoolean(args[2]);
            layout = args[3].toUpperCase();
        } else {
            throw new IllegalArgumentException("Too many arguments provided.");
        }

        // Define keyboard rows for various layouts
        String[][] layouts = {
                {"qwertyuiop", "asdfghjkl", "zxcvbnm"},   // QWERTY layout
                {"azertyuiop", "qsdfghjklm", "wxcvbn"},   // AZERTY layout
                {"qwertzuiop", "asdfghjkl", "yxcvbnm"},   // QWERTZ layout
                {"pyfgcrl", "aoeuidhtns", "qjkxbmwvz"},   // DVORAK layout
                {"qwfpgjluy", "arstdhneio", "zxcvbkm"}    // COLEMAK layout
        };

        String[] keyboardRows;
        switch (layout) {
            case "QWERTY":
                keyboardRows = layouts[0];
                break;
            case "AZERTY":
                keyboardRows = layouts[1];
                break;
            case "QWERTZ":
                keyboardRows = layouts[2];
                break;
            case "DVORAK":
                keyboardRows = layouts[3];
                break;
            case "COLEMAK":
                keyboardRows = layouts[4];
                break;
            default:
                throw new IllegalArgumentException("Unsupported keyboard layout: " + layout);
        }

        // Check for sequences in the specified layout's rows
        for (String row : keyboardRows) {
            int forwardSequenceLength = 0;
            int reverseSequenceLength = 0;
            for (int i = 0; i < password.length() - 1; i++) {
                int currentIndex = row.indexOf(password.charAt(i));
                int nextIndex = row.indexOf(password.charAt(i + 1));

                // Check for forward sequence
                if (currentIndex != -1 && nextIndex == currentIndex + 1) {
                    forwardSequenceLength++;
                } else {
                    forwardSequenceLength = 0; // Reset if no forward sequence
                }

                if (isReverse) {
                    // Check for reverse sequence
                    if (currentIndex != -1 && nextIndex == currentIndex - 1) {
                        reverseSequenceLength++;
                    } else {
                        reverseSequenceLength = 0; // Reset if no reverse sequence
                    }
                }

                // Check if any sequence exceeds the limit
                if (forwardSequenceLength >= limit || reverseSequenceLength >= limit) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks if a password contains sequences of symbols based on the "QWERTY" keyboard layout longer than a specified limit, with an option to check in reverse order.
     * <p>
     * This method inspects the provided password for sequences of non-alphanumeric characters (symbols) that follow the order of symbols on a QWERTY keyboard layout. It considers both forward and, if specified through an argument, reverse sequences against a defined limit. If any sequence of symbols exceeds this limit, the method returns {@code false}.
     * </p>
     * <p>
     * Expected arguments are:
     * <ul>
     * <li>The password to check, with all letters converted to lowercase for consistency.</li>
     * <li>A string representation of the limit for consecutive symbols in a sequence.</li>
     * <li>An optional boolean indicating whether to check for reverse sequences (defaults to {@code false} if not provided).</li>
     * <li>An optional argument for the keyboard layout, which is currently only implemented for "QWERTY".</li>
     * </ul>
     * The method currently supports only the "QWERTY" layout for symbol sequence checks.
     * </p>
     *
     * @param args An array of {@code String} objects containing the password, the consecutive symbol limit, an optional boolean for reverse sequence checks, and an optional keyboard layout (not implemented beyond "QWERTY").
     * @return {@code true} if the password does not contain symbol sequences exceeding the specified limit based on the "QWERTY" layout and direction(s), {@code false} otherwise.
     * @throws IllegalArgumentException If the number of arguments is incorrect, if the limit is not a valid integer, or if an unsupported keyboard layout is specified.
     */
    public boolean checkContinueSymbol(String[] args) {
        String password;
        int limit;
        boolean isReverse;
        String layout;
        if (args.length < 2) {
            throw new IllegalArgumentException("At least two arguments are required: Password and Limit.");
        } else if (args.length == 2) {
            password = args[0].toLowerCase();
            limit = Integer.parseInt(args[1]);
            isReverse = false;
            layout = "QWERTY";
        } else if (args.length == 3) {
            password = args[0].toLowerCase();
            limit = Integer.parseInt(args[1]);
            isReverse = Boolean.parseBoolean(args[2]);
            layout = "QWERTY";
        } else if (args.length == 4) {
            password = args[0].toLowerCase();
            limit = Integer.parseInt(args[1]);
            isReverse = Boolean.parseBoolean(args[2]);
            layout = "QWERTY";
//            layout = args[3].toUpperCase();
        } else {
            throw new IllegalArgumentException("Too many arguments provided.");
        }

        // Define keyboard rows for various layouts
        String[][] layouts = {
                {"~!@#$%^&*()_+"}   // QWERTY layout
        };

        String[] keyboardRows;
        switch (layout) {
            case "QWERTY":
                keyboardRows = layouts[0];
                break;
            case "AZERTY":
                keyboardRows = layouts[1];
                break;
            case "QWERTZ":
                keyboardRows = layouts[2];
                break;
            case "DVORAK":
                keyboardRows = layouts[3];
                break;
            case "COLEMAK":
                keyboardRows = layouts[4];
                break;
            default:
                throw new IllegalArgumentException("Unsupported keyboard layout: " + layout);
        }

        // Check for sequences in the specified layout's rows
        for (String row : keyboardRows) {
            int forwardSequenceLength = 0;
            int reverseSequenceLength = 0;
            for (int i = 0; i < password.length() - 1; i++) {
                int currentIndex = row.indexOf(password.charAt(i));
                int nextIndex = row.indexOf(password.charAt(i + 1));

                // Check for forward sequence
                if (currentIndex != -1 && nextIndex == currentIndex + 1) {
                    forwardSequenceLength++;
                } else {
                    forwardSequenceLength = 0; // Reset if no forward sequence
                }

                if (isReverse) {
                    // Check for reverse sequence
                    if (currentIndex != -1 && nextIndex == currentIndex - 1) {
                        reverseSequenceLength++;
                    } else {
                        reverseSequenceLength = 0; // Reset if no reverse sequence
                    }
                }

                // Check if any sequence exceeds the limit
                if (forwardSequenceLength >= limit || reverseSequenceLength >= limit) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks if the given password contains any substring from a specified list of strings to avoid.
     * <p>
     * This method iterates through a list of strings, checking if the password contains any of these strings as substrings, irrespective of case. If the password contains any of the specified substrings, the method returns {@code false}, indicating the password is not valid according to the criteria. Otherwise, it returns {@code true}, indicating the password does not contain any of the strings to avoid and is considered valid.
     * </p>
     *
     * @param password The password to check for avoidance of specific strings.
     * @param avoidStringList A {@code List<String>} containing strings that should not appear in the password.
     * @return {@code true} if the password does not contain any of the specified strings to avoid; {@code false} otherwise.
     */
    public boolean checkAvoidString(String password, List<String> avoidStringList) {
        for (String avoidString : avoidStringList) {
            if (password.toLowerCase().contains(avoidString.toLowerCase())) {
                return false;
            }
        }

        return true;
    }


    public boolean checkRegexPolicy(String[] args) {
        String password;
        int regexType;
        String regex;
        if (args.length < 2) {
            throw new IllegalArgumentException("At least two arguments are required: Password and Regex.");
        } else if (args.length == 2) {
            password = args[0];
            regexType = Integer.parseInt(args[1]);
            regex = "";
        } else if (args.length == 3) {
            password = args[0];
            regexType = Integer.parseInt(args[1]);
            regex = args[2];
        } else {
            throw new IllegalArgumentException("Too many arguments provided.");
        }
        // some default password regex policies
        switch (regexType) {
            case 1:
                // 1. At least one digit or letter
                // 2. No whitespace allowed in the entire string
                // 3. Minimum length of 6 characters
                regex = "^(?=.*[0-9a-zA-Z])(?=\\S+$).{8,}$";
                break;
            case 2:
                // 1. At least one digit
                // 2. At least one letter
                // 3. No whitespace allowed in the entire string
                // 4. Minimum length of 8 characters
                regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,}$";
                break;
            case 3:
                // 1. At least one digit
                // 2. At least one lowercase letter
                // 3. At least one uppercase letter
                // 4. No whitespace allowed in the entire string
                // 5. Minimum length of 8 characters
                regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
                break;
            case 4:
                // 1. At least one digit
                // 2. At least one lowercase letter
                // 3. At least one uppercase letter
                // 4. At least one special character
                // 5. No whitespace allowed in the entire string
                // 6. Minimum length of 8 characters
                regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?=\\S+$).{8,}$";
                break;
            default:
                if (StringTools.usable(regex)) {
                    // TODO: check if Regex is valid
                    break;
                } else {
                    throw new IllegalArgumentException("Unsupported regex type: " + regexType);
                }
        }
        boolean result = password.matches(regex);
        return result;
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
