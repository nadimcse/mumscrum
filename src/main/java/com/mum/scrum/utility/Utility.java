package com.mum.scrum.utility;

import com.mum.scrum.viewmodel.PermissionModel;
import com.mum.scrum.viewmodel.Token;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/9/16
 * Time: 1:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class Utility {

    public static boolean authenticateToken(String realToken, String secretKey) {

        if (StringUtils.isEmpty(realToken)) {
            return false;
        }
        String[] split = realToken.split("\\|");
        long timestamp = Long.valueOf(split[0]);
        int role = Integer.valueOf(split[1]);
        if (System.currentTimeMillis() > timestamp) {
            return false;
        }

        Token token = new Token(role, timestamp, secretKey);

        if (!token.compareTokenSignature(realToken)) {
            System.out.println("err...here2");
            return false;
        }
        return true;
    }

    public static boolean hasPermission(String permission, int role) {
         if (Arrays.asList(PermissionModel.permissionMap.get(role)).contains(permission)) {
             return true;
         }
        return false;
    }

}
