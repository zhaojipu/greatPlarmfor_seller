package com.wangku.library.utils;

import android.text.TextUtils;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 内容摘要：常用检验工具类
 */
public class CheckoutUtils {

    private CheckoutUtils() {
    }

    /**
     * 输入的用户是否有效，a~z、A~Z、数字、下划线
     *
     * @param userName
     * @return
     */
    public static boolean isValidInputUserName(String userName) {
        boolean isMatch = true;
        String reguarEx = "[A-Za-z0-9_]";
        for (int i = 0; i < userName.length(); i++) {
            isMatch = (userName.charAt(i) + "").matches(reguarEx);
            if (!isMatch) {
                return isMatch;
            }
        }
        return isMatch;
    }

    /**
     * 判断中文名字 2到10个中文
     *
     * @param name
     * @return
     */
    public static boolean ChineseNameTest(String name) {
        //判断不为空
        if (!TextUtils.isEmpty(name)) {
            String nonullString = name.replaceAll(" ", "");
            if (!nonullString.matches("[\u4e00-\u9fa5]{2,10}")) {
                System.out.println("只能输入2到4个汉字");
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean stringFilter(String str)throws PatternSyntaxException {
        // 只允许字母、数字和汉字
        String   regEx  =  "^[a-zA-Z0-9\u4E00-\u9FA5]{2,25}$";
//        Pattern   p   =   Pattern.compile(regEx);
//        Matcher   m   =   p.matcher(str);
//        return   m.replaceAll("").trim();
        Pattern p = Pattern
                .compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public static boolean isValidInputNickName(String nickName) {
        String reguarEx = "^[\\w\\u4e00-\\u9fa5]+$";
        return nickName.matches(reguarEx);
    }

    public static String StringFilter(String str) {
        String regEx = "[/\\:*?<>|\"()@￥%&*~!$^+#-,<>.`~=;1234567890]"; //要过滤掉的字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static boolean isValidUsernameLength(String username) {
        boolean valid = false;
        int trulyLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        int minLength = 6, maxLength = 40;
        for (int i = 0; i < username.length(); i++) {
            String temp = username.substring(i, i + 1);
            if (temp.matches(chinese)) {
                trulyLength += 2;
            } else {
                trulyLength += 1;
            }
        }
        if (trulyLength >= minLength && trulyLength <= maxLength) {
            valid = true;
        }
        return valid;
    }

    public static boolean isValidRealNameLength(String username) {
        boolean valid = false;
        int trulyLength = username.length();
        int minLength = 2, maxLength = 18;
        if (trulyLength >= minLength && trulyLength <= maxLength) {
            valid = true;
        }
        return valid;
    }


    public static String unMatchNickNameTips(String nickName) {
        String unMatchTips = "昵称不支持以下特殊字符";
        String reguarEx = "^[\\w\\u4e00-\\u9fa5]+$";
        LinkedHashSet<String> repeatSet = new LinkedHashSet<String>();
        for (int i = 0; i < nickName.length(); i++) {
            boolean isMatch = (nickName.charAt(i) + "").matches(reguarEx);
            if (!isMatch) repeatSet.add(nickName.charAt(i) + "");
        }
        Iterator<String> repeatList = repeatSet.iterator();
        int addTime = 0, maxTime = 3;
        while (repeatList.hasNext()) {
            if (addTime >= maxTime) {
                unMatchTips = unMatchTips.substring(
                        0, unMatchTips.length()) + "等";
                break;
            } else {
                unMatchTips = unMatchTips + repeatList.next() + "";
            }
            addTime++;
        }

        unMatchTips = unMatchTips.substring(0, unMatchTips.length());
        return reguarEx + "";
    }

    public static String unMatchUserNameTips(String userName) {
        String unMatchTips = "用户名不支持以下特殊字符";
        String reguarEx = "[A-Za-z0-9_]";
        LinkedHashSet<String> repeatSet = new LinkedHashSet<String>();
        for (int i = 0; i < userName.length(); i++) {
            boolean isMatch = (userName.charAt(i) + "").matches(reguarEx);
            if (!isMatch) repeatSet.add(userName.charAt(i) + "");
        }
        Iterator<String> repeatList = repeatSet.iterator();
        int addTime = 0, maxTime = 3;
        while (repeatList.hasNext()) {
            if (addTime >= maxTime) {
                unMatchTips = unMatchTips.substring(
                        0, unMatchTips.length() - 1) + "等";
                break;
            } else {
                unMatchTips = unMatchTips + repeatList.next() + "";
            }
            addTime++;
        }

        unMatchTips = unMatchTips.substring(0, unMatchTips.length());
        return unMatchTips + "";
    }

    /**
     * 手机号中间四位加密
     */
    public static String phone(String phone) {
        int between = phone.length() / 2;
        String mobile = phone.substring(0, between - 2) + "*****" + phone.substring(between + 3, phone.length());
        return mobile;
    }

    /**
     * 是否内容全为数字
     *
     * @param text
     * @return
     */
    public static boolean isWholeDigit(String text) {
        boolean isWholeDigit = true;
        for (int i = 0; i < text.length(); i++) {
            boolean isDigit = Character.isDigit(text.charAt(i));
            if (!isDigit) {
                isWholeDigit = false;
                break;
            }
        }
        return isWholeDigit;
    }

    /**
     * 判断是不是一个合法的身份证号
     *
     * @param IdNumber
     * @return
     */
    public static boolean isRealName(String IdNumber) {
        boolean m = IdNumber
                .matches("^[\\u4E00-\\u9FA5A-Za-z]{2,15}$");
        return m;
    }

    /**
     * 判断是不是一个合法的身份证号
     *
     * @param IdNumber
     * @return
     */
    public static boolean isIdNumber(String IdNumber) {
        boolean m = IdNumber
                .matches("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)");
        return m;
    }

    public static boolean isRealNum(String IdNumber) {
        boolean m = false;
        switch (IdNumber.length()) {
            case 15:
                int year = Integer.parseInt(IdNumber.substring(6, 14)) + 1900;
                if (year % 4 == 0 || (year % 100 == 0 && year % 4 == 0)) {
                    m = IdNumber.matches("^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$");
                } else {
                    m = IdNumber.matches("^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$");
                }
                break;

            case 18:
                int years = Integer.parseInt(IdNumber.substring(6, 14));
                LogUtils.e("============", years + "");
                if (years % 4 == 0 || (years % 100 == 0 && years % 4 == 0)) {
                    m = IdNumber.matches("^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$");
                    LogUtils.e("********",m);
                } else {
                    m = IdNumber.matches("^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$");
                    LogUtils.e("****dddddd****",m);
                }
                break;
        }
        return m;
    }

    /**
     * 判断是否为邮编
     *
     * @param postcode
     * @return
     */
    public static boolean isPostcode(String postcode) {
        Pattern p = Pattern
                .compile("^[1-9]\\d{5}$");
        Matcher m = p.matcher(postcode);
        boolean b = m.matches();
        return b;
    }

    /**
     * 判断是不是一个合法的手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isPhone(String mobiles) {
        Pattern p = Pattern
                .compile("^(134|135|136|137|138|139|147|150|151|152|157|158|159|178|182|183|184|187|188|130|131|132|145|155|156|171|175|176|185|186|133|149|153|173|177|180|181|189|170|852)[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.find();

//        Pattern p = Pattern.compile("^((17[7-8])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
//        Matcher m = p.matcher(mobiles);
//        return m.matches();
    }
    /**
     * 判断是不是一个合法的座机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isTelPhone(String mobiles) {
        Pattern p = Pattern
                .compile("^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$");
        Matcher m = p.matcher(mobiles);
        return m.find();

//        Pattern p = Pattern.compile("^((17[7-8])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
//        Matcher m = p.matcher(mobiles);
//        return m.matches();
    }

    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email cand_tyliang@163.com
     * @return true
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }


    public static boolean isPassWord(String pwd) {
        Pattern p = Pattern.compile("^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{6,20}$");
//        (?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,20})$
        Matcher m = p.matcher(pwd);
        return m.matches();
//        return !(pwd.replaceAll("[a-z]*[A-Z]*\\d*-*_*\\s*", "").length() != 0);
    }

    public static boolean passWordCanOk(String password) {
        int score = 0;//得分
        //密码长度
        score += password.length() * 4;
        LogUtils.e("========", score + "");
        score += (checkRepetition(1, password).length() - password.length()) * 1;
        LogUtils.e("========", score + "");
        score += (checkRepetition(2, password).length() - password.length()) * 1;
        LogUtils.e("========", score + "");
        score += (checkRepetition(3, password).length() - password.length()) * 1;
        LogUtils.e("========", score + "");
        score += (checkRepetition(4, password).length() - password.length()) * 1;
        LogUtils.e("========", score + "");

        //包含数字
        int isNumber = 0;
        for (int i = 0; i < password.length(); i++) {    //循环遍历字符串
            if (Character.isDigit(password.charAt(i))) {    //用char包装类中的判断数字的方法判断每一个字符
                isNumber++;
            }
        }
        if (isNumber > 2) {
            score += 5;
            LogUtils.e("密码有3个数字", "密码有3个数字" + score);
        }
        //密码有大写和小写的英文字符
        if (Pattern.compile("[a-z].*[A-Z]||[A-Z].*[a-z]").matcher(password).matches()) {
            score += 10;
            LogUtils.e("密码有大写和小写的英文字符", "密码有大写和小写的英文字符" + score);
        }
        //密码有数字和英文字符
        if (Pattern.compile("^^(?![\\\\d]+$)(?![a-zA-Z]+$)(?![^\\\\da-zA-Z]+$).{6,18}$").matcher(password).matches()) {
            score += 15;
            LogUtils.e("密码有数字和英文字符", "密码有数字和英文字符" + score);
        }
        //密码只有一个数字或英文字符
        if (Pattern.compile("^\\w+$").matcher(password).matches() || Pattern.compile("^\\d+$").matcher(password).matches()) {
            score -= 10;
            LogUtils.e("密码只有一个数字或英文字符", "密码只有一个数字或英文字符" + score);
        }
        LogUtils.e("===score=====", score + "");
        if (score >= 34) {
            return true;
        }
        return false;
    }

    //检查密码重复长度
    private static String checkRepetition(int pLen, String str) {
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            boolean repeated = true;
            int j = 0;
            for (j = 0; j < pLen && (j + i + pLen) < str.length(); j++) {
                repeated = repeated && (str.charAt(j + i) == str.charAt(j + i + pLen));
            }
            if (j < pLen) {
                repeated = false;
            }
            if (repeated) {
                i += pLen - 1;
                repeated = false;
            } else {
                res += str.charAt(i);
            }
        }
        LogUtils.e("===========", res);
        return res;
    }

    /**
     * 密码是否过于简单
     *
     * @param password
     * @return
     */
    public static boolean passWordIsOk(String password) {
        //包含特殊字符
        boolean isSpecialCharacter = password.replaceAll("[a-z]*[A-Z]*\\d*-*_*\\s*", "").length() != 0;
        //包含字母
        boolean isletter = password.matches("(?i)[^a-z]*[a-z]+[^a-z]*");
        //包含数字
        boolean isNumber = false;

        for (int i = 0; i < password.length(); i++) {    //循环遍历字符串
            if (Character.isDigit(password.charAt(i))) {    //用char包装类中的判断数字的方法判断每一个字符
                isNumber = true;
            }
        }
        LogUtils.e("=======", isNumber + "======" + isletter + "=========" + isSpecialCharacter);
        if (isletter && isNumber && isSpecialCharacter) {
            LogUtils.e("==========", "密码是强密码");
            return false;
        } else {
            byte[] bytes = new byte[password.length()];
            bytes = password.getBytes();
            int num = 0;
            for (int i = 0; i < bytes.length; i++) {
                num = 0;
                int m = bytes[i];
                for (int j = 0; j < bytes.length; j++) {
                    if (m == bytes[j]) {
                        num++;
                        if (num >= bytes.length * 2 / 3) {
                            LogUtils.e("=====================", "密码不可以");
                            return true;
                        }
                        LogUtils.e("========", num + "");
                    }
                }
            }
        }
        return false;
    }

    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { // 如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }
}
