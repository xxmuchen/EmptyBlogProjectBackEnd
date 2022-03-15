package com.example.emptyblogproject.controller.observecontroller;

import com.example.emptyblogproject.annotation.UserLoginToken;
import com.example.emptyblogproject.bean.observe.Observe;
import com.example.emptyblogproject.bean.observe.observeBo.ObserveNodeBO;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.observeservice.ObserveService;
import com.example.emptyblogproject.utils.UserTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/23
 * Time: 3:49
 * Description: 评论controller
 */
@RestController
public class ObserveController  {

    @Autowired
    ObserveService observeService;
    @Autowired
    UserTokenUtils userTokenUtils;

    /**
     * 功能描述：根据博客id，查询此博客的所有评论信息（链表类型的数据）
     * @param
     * @return 博客的评论信息
     */
    @GetMapping("/queryObserveByObjId")
    public List<ObserveNodeBO> queryObserveByObjId
                (@RequestParam(name = "objType")String objType , @RequestParam("objId") Long objId) {
//        long obj_id = Long.parseLong(objId);

//        List<ObserveNodeBO> observeNodeBOList = observeService.queryObserveByObjId("放空日记", obj_id);
        List<ObserveNodeBO> observeNodeBOList = observeService.queryObserveByObjId(objType, objId);
        System.out.println(observeNodeBOList);
        return observeNodeBOList;
    }
    /**
     * 功能描述：根据评论id查询用户信息（评论信息，携带用户信息）
     * @param
     * @return 评论信息，携带用户信息
     */
//    @GetMapping("/userservice/{observeId}")
//    public ObserveUserBo queryObserveUserById (@RequestParam(name = "observeId") Long observeId) {
//        ObserveUserBo observeUserBo = observeService.queryObserveUserById(observeId);
//        return observeUserBo;
//    }

    @UserLoginToken
    @PostMapping("/addObjObserve")
    public List<ObserveNodeBO> addObjObserve(@RequestBody Observe observe , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        observe.setObserverId(user.getId());
//        System.out.println(observe);
        boolean flag = observeService.save(observe);

        if (flag) {
            List<ObserveNodeBO> observeNodeBOList = observeService.queryObserveByObjId(observe.getType(), observe.getObjId());
            for (Observe item : observeNodeBOList) {
                System.out.println(item);
            }
            return observeNodeBOList;
        }else {
            throw new RuntimeException("评论失败,请重试");
        }
    }
}
