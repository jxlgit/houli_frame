package com.houli.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.houli.system.domain.UserDO;

public class ShiroUtils {

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }
    public static UserDO getUser() {
        Object object = getSubjct().getPrincipal();
        return (UserDO)object;
    }
    public static Long getUserId() {
        return getUser().getUserId();
    }
    public static void logout() {
        getSubjct().logout();
    }
    
}
