package com.example.emptyblogproject.utils;

import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.bean.user.UserLogin;
import com.example.emptyblogproject.bean.user.UserOperation;
import com.example.emptyblogproject.service.user.UserLoginService;
import com.example.emptyblogproject.service.user.UserOperationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/15
 * Time: 2:12
 * Description:
 */
public class UserOperationUtils {
    @Autowired
    private static UserOperationService userOperationService;

    public static void saveUserOperationInfo(User user , String identity , String type , String operation , String content) {
        UserOperation userOperation = new UserOperation();
        userOperation.setUserId(user.getId());
        userOperation.setUserName(user.getUserName());
        userOperation.setOperation(operation);
        userOperation.setContent(content);
        userOperation.setType(type);
        userOperation.setUserIdentity(identity);
        userOperationService.save(userOperation);
    }
}
