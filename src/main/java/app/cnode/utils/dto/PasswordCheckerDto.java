package app.cnode.utils.dto;

//import org.springframework.data.redis.core.RedisTemplate;

import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
     * Layout of the Keyboard
     */
    public String keyboardLayout;

    /**
     * Customized Regex
     */
    public String regex;

    /**
     * Redis Connection
     */
//    public RedisTemplate redisTemplate;

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

    public String getKeyboardLayout() {
        return keyboardLayout;
    }

    public void setKeyboardLayout(String keyboardLayout) {
        this.keyboardLayout = keyboardLayout;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

//    public RedisTemplate<String, String> getRedisTemplate() {
//        return redisTemplate;
//    }
//
//    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }

    /**
     * Initializes a new instance of the PasswordCheckerDto class with a specified password policy configuration.
     * <p>
     * Based on the 'type' parameter, this constructor configures the password policy with predefined settings, including minimum length requirements, the inclusion of various checks (e.g., sequential numbers, identical characters, linear alphabet sequences), and a list of strings to avoid. These settings are tailored for different levels of password security requirements.
     * </p>
     * <p>
     * Types and their configurations:
     * <ul>
     * <li>Type 1: High security - All checks enabled, minimum length of 12, QWERTY layout, reverse checks, and a comprehensive avoid list.</li>
     * <li>Type 2-5: Medium to lower security - Gradually reduces checks and minimum length requirements.</li>
     * <li>Type 6: Minimal security - Only length check enabled with a minimum of 6 characters.</li>
     * <li>Type 65535: Custom or debug setting - Similar to Type 1, serves as an override or specific configuration setup.</li>
     * </ul>
     * If an undefined type is provided, no configuration is applied.
     * </p>
     * <p>
     * The constructor also defines a default list of strings to avoid in passwords, common across most types, including terms like 'password', 'admin', and various others, often associated with weak passwords.
     * </p>
     *
     * @param type The type of password policy configuration to apply, determining the security measures and checks to be enabled for password validation.
     */
    public PasswordCheckerDto(int type) {
        List<String> defaultAvoidStringList = new Vector<>();
        defaultAvoidStringList.add("password");
        defaultAvoidStringList.add("pwd");
        defaultAvoidStringList.add("admin");
        defaultAvoidStringList.add("root");
        defaultAvoidStringList.add("user");
        defaultAvoidStringList.add("linux");
        defaultAvoidStringList.add("unix");
        defaultAvoidStringList.add("ubuntu");
        defaultAvoidStringList.add("debian");
        defaultAvoidStringList.add("centos");
        defaultAvoidStringList.add("redhat");
        defaultAvoidStringList.add("fedora");
        defaultAvoidStringList.add("prod");
        defaultAvoidStringList.add("test");
        defaultAvoidStringList.add("dev");
        defaultAvoidStringList.add("login");
        defaultAvoidStringList.add("logout");
        defaultAvoidStringList.add("signin");
        defaultAvoidStringList.add("signout");
        defaultAvoidStringList.add("signup");
        defaultAvoidStringList.add("signoff");
        defaultAvoidStringList.add("register");
        defaultAvoidStringList.add("unregister");
        defaultAvoidStringList.add("azure");
        defaultAvoidStringList.add("aws");
        defaultAvoidStringList.add("google");
        defaultAvoidStringList.add("Aliyun");
        defaultAvoidStringList.add("tencent");
        defaultAvoidStringList.add("baidu");
        defaultAvoidStringList.add("passw0rd");
        defaultAvoidStringList.add("master");
        switch (type){
            case 1:
                this.lengthChecker = true;
                this.minLength = 12;
                this.redisChecker = true;
                this.continueLength = 3;
                this.continueNumberChecker = true;
                this.continueAlphabetChecker = true;
                this.continueSymbolChecker = true;
                this.sameNumberChecker = true;
                this.sameAlphabetChecker = true;
                this.sameSymbolChecker = true;
                this.linearAlphabetChecker = true;
                this.reverseChecker = true;
                this.regexPolicy = 4;
                this.avoidStringList = defaultAvoidStringList;
                this.keyboardLayout = "QWERTY";
                break;
            case 2:
                this.lengthChecker = true;
                this.minLength = 10;
                this.redisChecker = true;
                this.continueLength = 3;
                this.continueNumberChecker = true;
                this.continueAlphabetChecker = true;
                this.continueSymbolChecker = false;
                this.sameNumberChecker = true;
                this.sameAlphabetChecker = true;
                this.sameSymbolChecker = true;
                this.linearAlphabetChecker = true;
                this.reverseChecker = false;
                this.regexPolicy = 4;
                this.avoidStringList = defaultAvoidStringList;
                this.keyboardLayout = "QWERTY";
                break;
            case 3:
                this.lengthChecker = true;
                this.minLength = 8;
                this.redisChecker = true;
                this.continueLength = 4;
                this.continueNumberChecker = true;
                this.continueAlphabetChecker = true;
                this.continueSymbolChecker = false;
                this.sameNumberChecker = true;
                this.sameAlphabetChecker = true;
                this.sameSymbolChecker = false;
                this.linearAlphabetChecker = true;
                this.reverseChecker = false;
                this.regexPolicy = 3;
                this.avoidStringList = defaultAvoidStringList;
                this.keyboardLayout = "QWERTY";
                break;
            case 4:
                this.lengthChecker = true;
                this.minLength = 8;
                this.redisChecker = false;
                this.continueLength = 5;
                this.continueNumberChecker = true;
                this.continueAlphabetChecker = true;
                this.continueSymbolChecker = false;
                this.sameNumberChecker = true;
                this.sameAlphabetChecker = true;
                this.sameSymbolChecker = false;
                this.linearAlphabetChecker = true;
                this.reverseChecker = false;
                this.regexPolicy = 2;
                this.avoidStringList = defaultAvoidStringList;
                this.keyboardLayout = "QWERTY";
                break;
            case 5:
                this.lengthChecker = true;
                this.minLength = 6;
                this.redisChecker = false;
                this.continueLength = 4;
                this.continueNumberChecker = true;
                this.continueAlphabetChecker = false;
                this.continueSymbolChecker = false;
                this.sameNumberChecker = true;
                this.sameAlphabetChecker = false;
                this.sameSymbolChecker = false;
                this.linearAlphabetChecker = false;
                this.reverseChecker = false;
                this.regexPolicy = 1;
                this.keyboardLayout = "QWERTY";
                break;
            case 6:
                this.lengthChecker = true;
                this.minLength = 6;
                this.redisChecker = false;
                this.continueLength = 4;
                this.continueNumberChecker = false;
                this.continueAlphabetChecker = false;
                this.continueSymbolChecker = false;
                this.sameNumberChecker = false;
                this.sameAlphabetChecker = false;
                this.sameSymbolChecker = false;
                this.linearAlphabetChecker = false;
                this.reverseChecker = false;
                this.regexPolicy = 1;
                this.keyboardLayout = "QWERTY";
                break;
            case 65535:
                this.lengthChecker = true;
                this.minLength = 12;
                this.redisChecker = true;
                this.continueLength = 3;
                this.continueNumberChecker = true;
                this.continueAlphabetChecker = true;
                this.continueSymbolChecker = true;
                this.sameNumberChecker = true;
                this.sameAlphabetChecker = true;
                this.sameSymbolChecker = true;
                this.linearAlphabetChecker = true;
                this.reverseChecker = true;
                this.regexPolicy = 4;
                this.avoidStringList = defaultAvoidStringList;
                this.keyboardLayout = "QWERTY";
                break;
            default:
        }
    }

    /**
     * Initializes a new instance of the PasswordCheckerDto class with configuration parameters specified in a map.
     * <p>
     * This constructor allows for a highly customizable setup of the password policy by passing a map containing specific configuration keys and values. The map allows setting boolean flags for various checks (e.g., length check, sequence checks), defining minimum and maximum password lengths, specifying the length for sequential character checks, enabling or disabling checks for numbers, alphabets, and symbols, and much more.
     * </p>
     * <p>
     * Configuration parameters include:
     * <ul>
     * <li>{@code lengthChecker}: Enables or disables length check.</li>
     * <li>{@code minLength}: Specifies the minimum password length.</li>
     * <li>{@code maxLength}: Specifies the maximum password length.</li>
     * <li>{@code redisChecker}: Enables or disables Redis check (if applicable).</li>
     * <li>{@code continueLength}: Length threshold for continuous character checks.</li>
     * <li>{@code continueNumberChecker}: Enables or disables continuous number sequence check.</li>
     * <li>{@code continueAlphabetChecker}: Enables or disables continuous alphabet sequence check.</li>
     * <li>{@code continueSymbolChecker}: Enables or disables continuous symbol sequence check.</li>
     * <li>{@code sameNumberChecker}: Enables or disables check for repeated numbers.</li>
     * <li>{@code sameAlphabetChecker}: Enables or disables check for repeated alphabets.</li>
     * <li>{@code sameSymbolChecker}: Enables or disables check for repeated symbols.</li>
     * <li>{@code linearAlphabetChecker}: Enables or disables check for linear alphabetic sequences based on a keyboard layout.</li>
     * <li>{@code reverseChecker}: Enables or disables checks for reverse sequences.</li>
     * <li>{@code regexPolicy}: Specifies the regex policy level.</li>
     * <li>{@code avoidStringList}: A list of strings to be avoided in the password.</li>
     * <li>{@code keyboardLayout}: Specifies the keyboard layout for linear sequence checks.</li>
     * <li>{@code regex}: Custom regex pattern for additional password validation.</li>
     * </ul>
     * </p>
     * <p>
     * This approach offers flexibility in configuring password validation rules to match specific security requirements.
     * </p>
     *
     * @param map A {@code Map<String, Object>} where each key represents a configuration parameter and the value its setting.
     */
    public PasswordCheckerDto(Map<String,Object> map){
        this.lengthChecker = (boolean) map.get("lengthChecker");
        this.minLength = (int) map.get("minLength");
        this.maxLength = (int) map.get("maxLength");
        this.redisChecker = (boolean) map.get("redisChecker");
        this.continueLength = (int) map.get("continueLength");
        this.continueNumberChecker = (boolean) map.get("continueNumberChecker");
        this.continueAlphabetChecker = (boolean) map.get("continueAlphabetChecker");
        this.continueSymbolChecker = (boolean) map.get("continueSymbolChecker");
        this.sameNumberChecker = (boolean) map.get("sameNumberChecker");
        this.sameAlphabetChecker = (boolean) map.get("sameAlphabetChecker");
        this.sameSymbolChecker = (boolean) map.get("sameSymbolChecker");
        this.linearAlphabetChecker = (boolean) map.get("linearAlphabetChecker");
        this.reverseChecker = (boolean) map.get("reverseChecker");
        this.regexPolicy = (int) map.get("regexPolicy");
        this.avoidStringList = (List<String>) map.get("avoidStringList");
        this.keyboardLayout = (String) map.get("keyboardLayout");
        this.regex = (String) map.get("regex");
    }
}

