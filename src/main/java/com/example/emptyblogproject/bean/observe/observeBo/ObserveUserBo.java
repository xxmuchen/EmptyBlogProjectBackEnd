package com.example.emptyblogproject.bean.observe.observeBo;

import com.example.emptyblogproject.bean.observe.Observe;
import com.example.emptyblogproject.bean.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/23
 * Time: 3:44
 * Description:
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ObserveUserBo extends User {
    private User user;
    private Observe observe;
}
