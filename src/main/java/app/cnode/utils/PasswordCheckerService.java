package app.cnode.utils;

import app.cnode.utils.constant.PwdFailReasonConst;
import app.cnode.utils.constant.PwdMsgConst;
import app.cnode.utils.dto.PasswordCheckerDto;

public class PasswordCheckerService {

    public PasswordCheckerDto checkPassword(PasswordCheckerDto dto) {
        if (StringTools.isNull(dto.getPassword())) {
            dto.setResult(false);
            dto.setMessage(PwdMsgConst.EMPTY.getMsg());
            dto.setFailedReason(PwdFailReasonConst.EMPTY.getMsg());
            return dto;
        }


        dto.setResult(true);
        dto.setMessage(PwdMsgConst.OK.getMsg());

        return dto;
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
