package app.cnode.utils.constant;

public enum PwdMsgConst {

    OK("OK"),
    EMPTY("Password is empty"),
    BAD_REQUEST("Bad Request"),
    REGEX_FAIL("Password did not Pass the Regex Test"),
    KALI_FAIL("Password is in Password Database"),
    REDIS_ERROR("Redis Connection Error"),
    CONTINUES_FAIL("Password contains continuous characters Over Designated Limit"),
    REPEAT_FAIL("Password contains repeated characters Over Designated Limit"),
    LINEAR_FAIL("Password contains linear characters Over Designated Limit"),
    UNACCEPTABLE_FAIL("Password contains unacceptable characters"),
    LENGTH_FAIL("Password length is not within the designated range"),
    SYSTEM_ERROR("System Error")


    ;



    private final String msg;

    PwdMsgConst(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
