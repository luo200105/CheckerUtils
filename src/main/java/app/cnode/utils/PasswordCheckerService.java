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
     *
     * If the password passes the initial null or empty check, the method then checks the password length if the length check
     * is enabled in the DTO. If the password fails the length check, the result, message, and failed reason fields are updated
     * to indicate the failure reason, and the DTO is returned.
     *
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

        if (dto.getLengthChecker()){
            if (checkLength(dto.getPassword(), dto.getMinLength(), dto.getMaxLength())) {
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
     * Validates the length of a given password against specified minimum and maximum length constraints.
     * If the minimum length is greater than the maximum length, the maximum length is set to 65535 as a safeguard.
     * This method ensures the password length is within the specified bounds, inclusive.
     *
     * @param password The password string to validate.
     * @param min The minimum acceptable length for the password. Must be a non-negative integer.
     * @param max The maximum acceptable length for the password. If less than min, it is reset to 65535.
     * @return {@code true} if the password's length is within the specified range (inclusive); {@code false} otherwise.
     */
    public boolean checkLength(String password, int min, int max) {
        if (min > max) {
            max = 65535;
        }
        if (password.length() < min || password.length() > max) {
            return false;
        }
        return true;
    }



    public boolean checkRedis(String password) {
//        boolean hasKey = redisServices.hasKey(password);
        if (false) {
            //与KALI密码库重合
            return false;
        }
        return true;
    }
}
