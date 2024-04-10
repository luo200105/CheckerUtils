package app.cnode.utils.dto;

import java.util.List;

/**
 * The {@code PasswordCheckerDto} class encapsulates the result of a password check, including various details such as the outcome,
 * password itself, reason for failure, and specific criteria used in the check. This class supports the configuration
 * of various password policies and checks, such as minimum length, existence in a predefined list, sequential character
 * checks, and more.
 *
 * <p>It offers flexibility in defining what constitutes a valid password through a variety of boolean flags and parameters,
 * enabling checks against patterns like sequential numbers, letters, symbols, and checks for repeats of the same character.
 * Additionally, it can be configured to avoid passwords found in a predefined list or match specific length requirements.</p>
 *
 * <p>The class is designed with the intention of being used in scenarios where comprehensive password validation is required,
 * providing both the result of the validation and any relevant details about why a password may have failed the checks.</p>
 *
 * <h3>Usage Examples:</h3>
 * <p>To use this class, create an instance of {@code PasswordCheckerDto} and configure the desired checks through its setters. After
 * setting up, invoke the password check logic (not provided within this class) that utilizes these configurations to validate
 * a password. Based on the check's outcome, the relevant properties of the {@code PasswordCheckerDto} instance can be updated to reflect
 * the result.</p>
 *
 * @param type The constructor parameter 'type' is not used within the constructor body. If intended for future use,
 *             its purpose should be documented or the parameter should be utilized.
 *
 *             <h3>Attributes:</h3>
 *             <ul>
 *             <li>{@code result} - Indicates whether the password passed the checks.</li>
 *             <li>{@code password} - The password that was checked.</li>
 *             <li>{@code message} - A general message about the check result.</li>
 *             <li>{@code failedReason} - Specific reason why the check failed, if applicable.</li>
 *             <li>{@code lengthChecker} - Flag to enable checking the password length.</li>
 *             <li>{@code minLength} - The minimum length the password must be.</li>
 *             <li>{@code redisChecker} - Flag to enable checking if the password exists in a Redis key.</li>
 *             <li>{@code continueLength}, {@code continueNumberChecker}, {@code continueAlphabetChecker}, {@code continueSymbolChecker},
 *                 {@code sameNumberChecker}, {@code sameAlphabetChecker}, {@code sameSymbolChecker}, {@code linearAlphabetChecker},
 *                 {@code reverseChecker} - Various flags for enabling specific password pattern checks.</li>
 *             <li>{@code passwordPolicy} - Defines the overall password policy to be enforced.</li>
 *             <li>{@code avoidStringList} - A list of strings that the password should not contain.</li>
 *             </ul>
 *
 *             <h3>Methods:</h3>
 *             <p>Setter and getter methods for all attributes, facilitating the configuration and retrieval of check details.</p>
 */
public class PasswordCheckerDto {
    /**
     * Indicates if the password meets the defined validation criteria.
     */
    public boolean result;

    /**
     * The password that was evaluated.
     */
    public String password;

    /**
     * General message about the validation outcome.
     */
    public String message;

    /**
     * Detailed reason for validation failure, if applicable.
     */
    public String failedReason;

    /**
     * Flag to determine if password length should be checked.
     */
    public boolean lengthChecker;

    /**
     * Minimum required length for the password.
     */
    public int minLength;

    /**
     * Maximum allowed length for the password.
     */
    public int maxLength;

    /**
     * Flag to check if the password exists in a Redis key, implying it's not allowed.
     */
    public boolean redisChecker;

    /**
     * Specifies the maximum allowed length of consecutive character sequences.
     */
    public int continueLength;

    /**
     * Flag to enable checking for consecutive numeric sequences in the password.
     */
    public boolean continueNumberChecker;

    /**
     * Flag to enable checking for consecutive alphabetical sequences in the password.
     */
    public boolean continueAlphabetChecker;

    /**
     * Flag to enable checking for consecutive symbol sequences in the password.
     */
    public boolean continueSymbolChecker;

    /**
     * Flag to enable checking for repeated numbers in the password.
     */
    public boolean sameNumberChecker;

    /**
     * Flag to enable checking for repeated letters in the password.
     */
    public boolean sameAlphabetChecker;

    /**
     * Flag to enable checking for repeated symbols in the password.
     */
    public boolean sameSymbolChecker;

    /**
     * Flag to enable checking for linear alphabetical sequences in the password.
     */
    public boolean linearAlphabetChecker;

    /**
     * Flag to enable reverse order checking of character sequences in the password.
     */
    public boolean reverseChecker;

    /**
     * Defines the regex policy to be applied during validation.
     */
    public int regexPolicy;

    /**
     * List of strings that the password should not match or contain.
     */
    public List<String> avoidStringList;

    /**
     * Ranking of the password.
     */
    public int ranking;

    /**
     * Default constructor for PasswordCheckerDto class.
     */
    public PasswordCheckerDto() {
    }


    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason;
    }

    public boolean getLengthChecker() {
        return lengthChecker;
    }

    public void setLengthChecker(boolean lengthChecker) {
        this.lengthChecker = lengthChecker;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public boolean getRedisChecker() {
        return redisChecker;
    }

    public void setRedisChecker(boolean redisChecker) {
        this.redisChecker = redisChecker;
    }

    public int getContinueLength() {
        return continueLength;
    }

    public void setContinueLength(int continueLength) {
        this.continueLength = continueLength;
    }

    public boolean getContinueNumberChecker() {
        return continueNumberChecker;
    }

    public void setContinueNumberChecker(boolean continueNumberChecker) {
        this.continueNumberChecker = continueNumberChecker;
    }

    public boolean getContinueAlphabetChecker() {
        return continueAlphabetChecker;
    }

    public void setContinueAlphabetChecker(boolean continueAlphabetChecker) {
        this.continueAlphabetChecker = continueAlphabetChecker;
    }

    public boolean getContinueSymbolChecker() {
        return continueSymbolChecker;
    }

    public void setContinueSymbolChecker(boolean continueSymbolChecker) {
        this.continueSymbolChecker = continueSymbolChecker;
    }

    public boolean getSameNumberChecker() {
        return sameNumberChecker;
    }

    public void setSameNumberChecker(boolean sameNumberChecker) {
        this.sameNumberChecker = sameNumberChecker;
    }

    public boolean getSameAlphabetChecker() {
        return sameAlphabetChecker;
    }

    public void setSameAlphabetChecker(boolean sameAlphabetChecker) {
        this.sameAlphabetChecker = sameAlphabetChecker;
    }

    public boolean getSameSymbolChecker() {
        return sameSymbolChecker;
    }

    public void setSameSymbolChecker(boolean sameSymbolChecker) {
        this.sameSymbolChecker = sameSymbolChecker;
    }

    public boolean getLinearAlphabetChecker() {
        return linearAlphabetChecker;
    }

    public void setLinearAlphabetChecker(boolean linearAlphabetChecker) {
        this.linearAlphabetChecker = linearAlphabetChecker;
    }

    public boolean getReverseChecker() {
        return reverseChecker;
    }

    public void setReverseChecker(boolean reverseChecker) {
        this.reverseChecker = reverseChecker;
    }

    public List<String> getAvoidStringList() {
        return avoidStringList;
    }

    public void setAvoidStringList(List<String> avoidStringList) {
        this.avoidStringList = avoidStringList;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getRegexPolicy() {
        return regexPolicy;
    }

    public void setRegexPolicy(int regexPolicy) {
        this.regexPolicy = regexPolicy;
    }
    /**
     * Constructs a PasswordCheckerDto with a specified type, initializing validation criteria based on type.
     * @param type The type of password policy to apply. Currently, this parameter is not utilized in the constructor.
     */
    public PasswordCheckerDto(int type) {
        this.lengthChecker = lengthChecker;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.redisChecker = redisChecker;
        this.continueLength = continueLength;
        this.continueNumberChecker = continueNumberChecker;
        this.continueAlphabetChecker = continueAlphabetChecker;
        this.continueSymbolChecker = continueSymbolChecker;
        this.sameNumberChecker = sameNumberChecker;
        this.sameAlphabetChecker = sameAlphabetChecker;
        this.sameSymbolChecker = sameSymbolChecker;
        this.linearAlphabetChecker = linearAlphabetChecker;
        this.reverseChecker = reverseChecker;
        this.regexPolicy = regexPolicy;
        this.avoidStringList = avoidStringList;
    }
}

