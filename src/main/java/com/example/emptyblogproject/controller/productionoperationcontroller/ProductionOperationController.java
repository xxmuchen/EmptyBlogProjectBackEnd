package com.example.emptyblogproject.controller.productionoperationcontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.emptyblogproject.annotation.UserLoginToken;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.griphic.Griphic;
import com.example.emptyblogproject.bean.productioncollection.ProductionCollection;
import com.example.emptyblogproject.bean.productionstar.ProductionStar;
import com.example.emptyblogproject.bean.sentence.Sentence;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.bean.vlog.Vlog;
import com.example.emptyblogproject.service.diaryservice.DiaryService;
import com.example.emptyblogproject.service.griphicservice.GriphicService;
import com.example.emptyblogproject.service.productioncollectionservice.ProductionCollectionService;
import com.example.emptyblogproject.service.productionstarservice.ProductionStarService;
import com.example.emptyblogproject.service.sentenceservice.SentenceService;
import com.example.emptyblogproject.service.vlogservice.VlogService;
import com.example.emptyblogproject.utils.UserTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/26
 * Time: 18:42
 * Description:
 */
@RestController
public class ProductionOperationController {

    @Autowired
    SentenceService sentenceService;
    @Autowired
    DiaryService diaryService;
    @Autowired
    ProductionCollectionService productionCollectionService;
    @Autowired
    ProductionStarService productionStarService;
    @Autowired
    UserTokenUtils userTokenUtils;
    @Autowired
    VlogService vlogService;
    @Autowired
    GriphicService griphicService;


//    用户博客点赞
    @UserLoginToken
    @PostMapping("/saveObjStar")
    public String saveObjStar(@RequestBody String objData , HttpServletRequest httpServletRequest) {
            String authorization = httpServletRequest.getHeader("Authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        if (user == null) {
            throw new RuntimeException("点赞失败，用户不存在，请退出重新登陆");
        }

        JSONObject jsonObject = JSON.parseObject(objData);

        long obj_id = Long.parseLong(jsonObject.getString("objId"));
        String objType = jsonObject.getString("objType");

        ProductionStar productionStar = null;
        boolean flag = false;
        if (objType.equals("放空日记")){
            Diary object = diaryService.getById(obj_id);
            if (object == null) {
                throw new RuntimeException("点赞失败，该日记不存在");
            }
            productionStar = productionStarService.
                    getOneHasDelDiaryStar(user.getId() , object.getId() , objType);
            if (productionStar == null) {
                productionStar = new ProductionStar();
                productionStar.setUserId(user.getId());
                productionStar.setObjId(object.getId());
                productionStar.setType(objType);
//            System.out.println(diaryStar);
                flag = productionStarService.save(productionStar);
            }else {
                flag = productionStarService.reLikeDiary(productionStar.getId());
            }

        }else if (objType.equals("放空句子")) {
            Sentence object = sentenceService.getById(obj_id);
            if (object == null) {
                throw new RuntimeException("点赞失败，该句子不存在");
            }
            productionStar = productionStarService.
                    getOneHasDelDiaryStar(user.getId() , object.getId() , objType);
            if (productionStar == null) {
                productionStar = new ProductionStar();
                productionStar.setUserId(user.getId());
                productionStar.setObjId(object.getId());
                productionStar.setType(objType);
//            System.out.println(diaryStar);
                flag = productionStarService.save(productionStar);
            }else {
                flag = productionStarService.reLikeDiary(productionStar.getId());
            }
        }else if (objType.equals("放空Vlog")){
            Vlog object = vlogService.getById(obj_id);
            if (object == null) {
                throw new RuntimeException("点赞失败，该Vlog不存在");
            }
            productionStar = productionStarService.
                    getOneHasDelDiaryStar(user.getId() , object.getId() , objType);
            if (productionStar == null) {
                productionStar = new ProductionStar();
                productionStar.setUserId(user.getId());
                productionStar.setObjId(object.getId());
                productionStar.setType(objType);
//            System.out.println(diaryStar);
                flag = productionStarService.save(productionStar);
            }else {
                flag = productionStarService.reLikeDiary(productionStar.getId());
            }
        } else if (objType.equals("放空图文")){
            Griphic object = griphicService.getById(obj_id);
            if (object == null) {
                throw new RuntimeException("点赞失败，该图文不存在");
            }
            productionStar = productionStarService.
                    getOneHasDelDiaryStar(user.getId() , object.getId() , objType);
            if (productionStar == null) {
                productionStar = new ProductionStar();
                productionStar.setUserId(user.getId());
                productionStar.setObjId(object.getId());
                productionStar.setType(objType);
//            System.out.println(diaryStar);
                flag = productionStarService.save(productionStar);
            }else {
                flag = productionStarService.reLikeDiary(productionStar.getId());
            }
        }else {
            throw new RuntimeException("数据错误，请重试");
        }
        if (flag) {
            return "点赞成功";
        } else {
            throw new RuntimeException("数据错误，请重试");
        }

    }

    /*取消日记点赞*/
    @UserLoginToken
    @PostMapping("/cancelObjStar")
    public String cancelObjStar(@RequestBody String objData , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        if (user == null) {
            throw new RuntimeException("取消点赞失败，用户不存在，请退出重新登陆");
        }
        JSONObject jsonObject = JSON.parseObject(objData);
        long obj_id = Long.parseLong(jsonObject.getString("objId"));
        String objType = jsonObject.getString("objType");
        boolean flag = false;
        ProductionStar productionStar = null;
        if (objType.equals("放空日记")) {
            Diary object = diaryService.getById(obj_id);
            if (object == null) {
                throw new RuntimeException("取消点赞失败，该日记不存在");
            }
            productionStar = productionStarService.getOneDiaryStar(user.getId(), object.getId(), objType);
        }else if (objType.equals("放空句子")) {
            Sentence object = sentenceService.getById(obj_id);
            if (object == null) {
                throw new RuntimeException("取消点赞失败，该句子不存在");
            }
            productionStar = productionStarService.getOneDiaryStar(user.getId(), object.getId(), objType);
        }else if (objType.equals("放空Vlog")) {
            Vlog object = vlogService.getById(obj_id);
            if (object == null) {
                throw new RuntimeException("取消点赞失败，该Vlog不存在");
            }
            productionStar = productionStarService.getOneDiaryStar(user.getId(), object.getId(), objType);
        }else if (objType.equals("放空图文")) {
            Griphic object = griphicService.getById(obj_id);
            if (object == null) {
                throw new RuntimeException("取消点赞失败，该图文不存在");
            }
            productionStar = productionStarService.getOneDiaryStar(user.getId(), object.getId(), objType);
        }else {
            throw new RuntimeException("取消点赞失败，数据错误");
        }

        if (productionStar == null) {
            throw new RuntimeException("取消点赞失败，您未赞过");
        }
        flag = productionStarService.removeById(productionStar.getId());
        if (flag) {
            return "取消点赞成功";
        }else {
            throw new RuntimeException("取消点赞失败，请重试");
        }
    }

    @UserLoginToken
    @PostMapping("/saveObjCollection")
    /* 日记收藏功能 */
    public String saveObjCollection(@RequestBody String objData , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        if (user == null) {
            throw new RuntimeException("收藏失败，用户不存在，请退出重新登陆");
        }
        JSONObject jsonObject = JSON.parseObject(objData);

        long obj_id = Long.parseLong(jsonObject.getString("objId"));
        String objType = jsonObject.getString("objType");
        boolean flag = false;
        if (objType.equals("放空日记")) {
            Diary object = diaryService.getById(obj_id);

            if (object == null) {
                throw new RuntimeException("收藏失败，该日记不存在");
            }
            ProductionCollection productionCollection = productionCollectionService.getOneHasDelDiaryCollection(user.getId(), object.getId() , objType);
            if (productionCollection == null) {
                productionCollection = new ProductionCollection();
                productionCollection.setUserId(user.getId());
                productionCollection.setObjId(object.getId());
                productionCollection.setType(objType);

                flag = productionCollectionService.save(productionCollection);
            }else {
                flag = productionCollectionService.reCollectDiary(productionCollection.getId());
            }
        }else if (objType.equals("放空句子")) {
            Sentence object = sentenceService.getById(obj_id);

            if (object == null) {
                throw new RuntimeException("收藏失败，该句子不存在");
            }
            ProductionCollection productionCollection = productionCollectionService.getOneHasDelDiaryCollection(user.getId(), object.getId() , objType);
            if (productionCollection == null) {
                productionCollection = new ProductionCollection();
                productionCollection.setUserId(user.getId());
                productionCollection.setObjId(object.getId());
                productionCollection.setType(objType);
                flag = productionCollectionService.save(productionCollection);
            }else {
                flag = productionCollectionService.reCollectDiary(productionCollection.getId());
            }
        }else if(objType.equals("放空Vlog")) {
            Vlog object = vlogService.getById(obj_id);

            if (object == null) {
                throw new RuntimeException("收藏失败，该Vlog不存在");
            }
            ProductionCollection productionCollection = productionCollectionService.getOneHasDelDiaryCollection(user.getId(), object.getId() , objType);
            if (productionCollection == null) {
                productionCollection = new ProductionCollection();
                productionCollection.setUserId(user.getId());
                productionCollection.setObjId(object.getId());
                productionCollection.setType(objType);
                flag = productionCollectionService.save(productionCollection);
            }else {
                flag = productionCollectionService.reCollectDiary(productionCollection.getId());
            }
        }else if(objType.equals("放空图文")) {
            Griphic object = griphicService.getById(obj_id);

            if (object == null) {
                throw new RuntimeException("收藏失败，该图文不存在");
            }
            ProductionCollection productionCollection = productionCollectionService.getOneHasDelDiaryCollection(user.getId(), object.getId() , objType);
            if (productionCollection == null) {
                productionCollection = new ProductionCollection();
                productionCollection.setUserId(user.getId());
                productionCollection.setObjId(object.getId());
                productionCollection.setType(objType);
                flag = productionCollectionService.save(productionCollection);
            }else {
                flag = productionCollectionService.reCollectDiary(productionCollection.getId());
            }
        }else {
            throw new RuntimeException("收藏失败，数据错误");
        }

        if (flag) {
            return "收藏成功";
        }else {
            throw new RuntimeException("收藏失败，请重试");
        }
    }

    /*取消日记收藏*/
    @UserLoginToken
    @PostMapping("/cancelObjCollection")
    public String cancelObjCollection(@RequestBody String objData , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);


        if (user == null) {
            throw new RuntimeException("取消收藏失败，该用户不存在，请退出重新登陆");
        }

        JSONObject jsonObject = JSON.parseObject(objData);

        long obj_id = Long.parseLong(jsonObject.getString("objId"));
        String objType = jsonObject.getString("objType");
        boolean flag = false;
        if (objType.equals("放空日记")) {
            Diary object = diaryService.getById(obj_id);

            if (object == null) {
                throw new RuntimeException("取消收藏失败，该日记不存在");
            }

            ProductionCollection productionCollection = productionCollectionService.getOneDiaryCollection(user.getId(), object.getId(), objType);
            if (productionCollection == null) {
                throw new RuntimeException("取消收藏失败，您未赞过");
            }
            flag = productionCollectionService.removeById(productionCollection.getId());
        }else if (objType.equals("放空句子")) {
            Sentence object = sentenceService.getById(obj_id);

            if (object == null) {
                throw new RuntimeException("取消收藏失败，该句子不存在");
            }

            ProductionCollection productionCollection = productionCollectionService.getOneDiaryCollection(user.getId(), object.getId(), objType);
            if (productionCollection == null) {
                throw new RuntimeException("取消收藏失败，您未赞过");
            }
            flag = productionCollectionService.removeById(productionCollection.getId());
        }else if(objType.equals("放空Vlog")) {
            Vlog object = vlogService.getById(obj_id);

            if (object == null) {
                throw new RuntimeException("取消收藏失败，该Vlog不存在");
            }

            ProductionCollection productionCollection = productionCollectionService.getOneDiaryCollection(user.getId(), object.getId(), objType);
            if (productionCollection == null) {
                throw new RuntimeException("取消收藏失败，您未赞过");
            }
            flag = productionCollectionService.removeById(productionCollection.getId());
        }else if(objType.equals("放空图文")) {
            Griphic object = griphicService.getById(obj_id);

            if (object == null) {
                throw new RuntimeException("取消收藏失败，该图文不存在");
            }

            ProductionCollection productionCollection = productionCollectionService.getOneDiaryCollection(user.getId(), object.getId(), objType);
            if (productionCollection == null) {
                throw new RuntimeException("取消收藏失败，您未赞过");
            }
            flag = productionCollectionService.removeById(productionCollection.getId());
        }else {
            throw new RuntimeException("取消收藏失败，数据错误");
        }

        if (flag) {
            return "取消收藏成功";
        }else {
            throw new RuntimeException("取消收藏失败，请重试");
        }
    }


    /*查看用户是否已点赞*/
    @GetMapping("/hasAlreadLike")
    @UserLoginToken
    public String hasAlreadLike(@RequestParam(name = "objId")String objId ,
                                @RequestParam("objType") String objType,
                                HttpServletRequest httpServletRequest){
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        if (user == null) {
            throw new RuntimeException("用户不存在，请退出重新登陆");
        }
        if (objId.equals("undefined")) {
            throw new RuntimeException("暂无资源");
        }
        long obj_id = Long.parseLong(objId);
        ProductionStar productionStar = null;
        if (objType.equals("放空日记")) {
            Diary object = diaryService.getById(obj_id);
            if (object == null) {
                throw new RuntimeException("该日记不存在");
            }
            productionStar = productionStarService.getOneDiaryStar(user.getId(), object.getId(), objType);
            if (productionStar != null) {
                return "like";
            }else {
                return "not like";
            }
        }else if (objType.equals("放空句子")) {
            Sentence object = sentenceService.getById(obj_id);
            if (object == null) {
                throw new RuntimeException("该句子不存在");
            }
            productionStar = productionStarService.getOneDiaryStar(user.getId(), object.getId(), objType);
        } else if(objType.equals("放空Vlog")) {
            Vlog object = vlogService.getById(obj_id);
            if (object == null) {
                throw new RuntimeException("该Vlog不存在");
            }
            productionStar = productionStarService.getOneDiaryStar(user.getId(), object.getId(), objType);
        } else if(objType.equals("放空图文")) {
            Griphic object = griphicService.getById(obj_id);
            if (object == null) {
                throw new RuntimeException("该图文不存在");
            }
            productionStar = productionStarService.getOneDiaryStar(user.getId(), object.getId(), objType);
        }else {
            throw new RuntimeException("数据错误，请重试");
        }
        if (productionStar != null) {
            return "like";
        }else {
            return "not like";
        }
    }

    /*查看用户是否已收藏*/
    @GetMapping("/hasAlreadCollect")
    @UserLoginToken
    public String hasAlreadCollect(@RequestParam(name = "objId")String objId,
                                   @RequestParam(name = "objType") String objType,
                                   HttpServletRequest httpServletRequest){
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);

        if (user == null) {
            throw new RuntimeException("用户不存在，请退出重新登陆");
        }
        if (objId.equals("undefined")) {
            throw new RuntimeException("暂无资源");
        }
        long obj_id = Long.parseLong(objId);

        ProductionCollection productionCollection = null;
        if (objType.equals("放空日记")) {
            Diary object = diaryService.getById(obj_id);
            if (object == null) {
                throw new RuntimeException("该日记不存在");
            }
            productionCollection = productionCollectionService.getOneDiaryCollection(user.getId(), object.getId(), objType);

        }else if (objType.equals("放空句子")) {
            Sentence object = sentenceService.getById(obj_id);
            if (object == null) {
                throw new RuntimeException("该句子不存在");
            }
            productionCollection = productionCollectionService.getOneDiaryCollection(user.getId(), object.getId(), objType);

        } else if(objType.equals("放空Vlog")) {
            Vlog object = vlogService.getById(obj_id);
            if (object == null) {
                throw new RuntimeException("该Vlog不存在");
            }

            productionCollection = productionCollectionService.getOneDiaryCollection(user.getId(), object.getId(), objType);

        } else if(objType.equals("放空图文")) {
            Griphic object = griphicService.getById(obj_id);
            if (object == null) {
                throw new RuntimeException("该Vlog不存在");
            }

            productionCollection = productionCollectionService.getOneDiaryCollection(user.getId(), object.getId(), objType);

        } else {
            throw new RuntimeException("数据错误，请重试");
        }
        if (productionCollection != null) {
//            throw new RuntimeException("您未赞过");
            return "collect";
        }else {
            return "not collect";
        }
    }


}
