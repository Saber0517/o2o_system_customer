package com.oocl.jyhon.util;

import com.oocl.jyhon.entiy.UserEntity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ZHANGJA4 on 8/4/2015.
 */
public class VerifyUtil {

    enum FLAG {
        SUCCSS
    }

    ;

    public Map<String, String> verifyParameter(UserEntity userEntity) {
        Map<String, String> verifyMessageList = new LinkedHashMap<String, String>();

        String message = verifyName(userEntity.getUserName());
        if (message.length() > 1) {
            verifyMessageList.put("name", message);
        }
        message = verifyPsd(userEntity.getPassword());
        if (!message.equals("")) {
            verifyMessageList.put("pwd", message);
        }
        if (!StringFormatVerifyUtil.verifyTelFormat(userEntity.getTel())) {
            verifyMessageList.put("tel", "phone format wrong");
        }
        return verifyMessageList;
    }

    private String verifyNickName(String nickname) {
        if (isNoNullOrEmpty(nickname)) {
            if (nickname.length() < 1) {
                return "nickname is too short";
            } else if (nickname.length() > 8) {
                return "nick is too long";
            }

        } else {
            return "plase input your nick name";
        }
        return "";
    }

    final static String EMAIL_REGEX = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

    private String verifyEmail(String email) {
        if (isNullOrEmpty(email) || !email.matches(EMAIL_REGEX)) {
            return "illegal email address";
        } else {
            return "";
        }

    }


    private String verifySex(String sex) {
        if (isNullOrEmpty(sex)) {
            return "please select your sex";
        } else if (sex.equals("man") || sex.equals("woman")) {
            return "";
        } else {
            return "illegal sex parameter";
        }
    }

    final static String DATE_REGEX = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

    private String verifyBirth(Date birth) {
        //birth已经被遗弃
        Calendar topLimit = Calendar.getInstance();
        topLimit.set(Calendar.YEAR, 1990);
        //..未转换完成

        if (null != birth) {
            return "please pick your birth day";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD");
        if (simpleDateFormat.format(birth).matches(DATE_REGEX)) {
            return "";
        } else {
            return "illegal birth date fromat";
        }
    }

    private String verifyPsd(String psd) {
        if (isNoNullOrEmpty(psd)) {
            if (psd.length() < 6) {
                return "password is too short";
            }else {
                return "";
            }
        }
        return "password is empty";
    }

    final static String NAME_REGEX = "^\\w{4,8}$";

    private String verifyName(String name) {
        if (isNoNullOrEmpty(name)) {
            if (NAME_REGEX.matches(name)) {
                return "";
            }
            if (name.length() < 4) {
                return "name is too short";
            } else if (name.length() > 8) {
                return "name is too long";
            }
        }
        return "";
    }

    private boolean isNullOrEmpty(String message) {
        return !isNoNullOrEmpty(message);
    }

    private boolean isNoNullOrEmpty(String message) {
        if (null != message || message.length() < 1) {
            return true;
        } else {
            return false;
        }
    }
}
