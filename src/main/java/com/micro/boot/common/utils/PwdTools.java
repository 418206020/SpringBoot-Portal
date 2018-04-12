package com.micro.boot.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * 〈密码校验〉
 *
 * @author Administrator
 * @create 2018/4/5
 * @since 1.0.0
 */
public class PwdTools {

    /**
     * 校验规则如下：
     * 1. 必须包含数字、字母、特殊字符 三种
     * 2. 长度至少8位
     * 3. 不能包含3位及以上相同字符的重复【eg：x111@q& xxxx@q&1】
     * 4 不能包含3位及以上字符组合的重复【eg：ab!23ab!】
     * 5. 不能包含3位及以上的正序及逆序连续字符【eg：123%@#aop %@#321ao efg3%@#47 3%@#47gfe】
     * 6. 不能包含空格、制表符、换页符等空白字符
     * 7. 键盘123456789数字对应的正序逆序特殊字符：eg：12#$%pwtcp(#$%(345对应的特殊字符#$%，仍视作连续))
     * 8. 支持的特殊字符范围：^$./,;:’!@#%&*|?-_+()[]{}
     */

    public static boolean isCorrect_1_8(String str) {
        if (str.matches("^.*[a-zA-Z]+.*$") && str.matches("^.*[0-9]+.*$")
                && str.matches("^.*[/^/$/.//,;:'!@#%&/*/|/?/-/_/+/(/)/[/]/{/}]+.*$"))
        {
            return true;
        }
        return false;
    }

    public static boolean isCorrect_2(String str) {
        if (str.matches("^.{8,}$")) {
            return true;
        }
        return false;
    }

    public static boolean isCorrect_3(String str) {
        if (!str.matches("^.*(.)\\1{2,}+.*$")) {
            return true;
        }
        return false;
    }

    public static boolean isCorrect_4(String str) {
        if (!str.matches("^.*(.{3})(.*)\\1+.*$")) {
            return true;
        }
        return false;
    }

    public static boolean isCorrect_6(String str) {
        if (!str.matches("^.*[\\s]+.*$")) {
            return true;
        }
        return false;
    }

    public static String encodeHexPwd(String pwd, String salt) {
        return new Sha256Hash(DigestUtils.sha256Hex(pwd), salt).toHex();
    }


}