package com.example.emptyblogproject.controller.diarycontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.annotation.UserLoginToken;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.observe.Observe;
import com.example.emptyblogproject.bean.observe.observeBo.ObserveNodeBO;
import com.example.emptyblogproject.bean.sensitivewords.SensitiveWords;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.diaryservice.DiaryService;
import com.example.emptyblogproject.service.observeservice.ObserveService;
import com.example.emptyblogproject.service.productioncollectionservice.ProductionCollectionService;
import com.example.emptyblogproject.service.productionstarservice.ProductionStarService;
import com.example.emptyblogproject.service.sensitivewordsservice.SensitiveWordsService;
import com.example.emptyblogproject.service.userservice.UserService;
import com.example.emptyblogproject.utils.UserTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

//@Controller
//@ResponseBody
@RestController
public class DiaryController {

    @Autowired
    UserTokenUtils userTokenUtils;

    @Value("${file.diaryImagePath}")
    private String uploadImageAbsolutePath;

    @Value("${file.diaryVideoPath}")
    private String uploadVideoAbsolutePath;

    @Value("${file.musicPath}")
    private String uploadMusciAbsolutePath;

    @Autowired
    UserService userService;

    @Autowired
    DiaryService diaryService;

    @Autowired
    ProductionStarService productionStarService;

    @Autowired
    ProductionCollectionService productionCollectionService;

    @Autowired
    ObserveService observeService;

    @Autowired
    SensitiveWordsService sensitiveWordsService;
//    日记内容图片上传
//    @UserLoginToken
//    日记图片文件上传并返回路径
    @PostMapping("/diaryImageFileUpLoadAndReturnUrl")
    public JSON diaryImageFileUpLoadAndReturnUrl(@RequestParam("myImageFileName") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new RuntimeException("文件不能为空");
        }
        String originalFilename = multipartFile.getOriginalFilename();

        File absolutePath = new File(uploadImageAbsolutePath);
        if (!absolutePath.exists()) {
            absolutePath.mkdirs();
        }

        String imageFileName = "" + UUID.randomUUID() + UUID.randomUUID().hashCode() + originalFilename.substring(originalFilename.lastIndexOf("."));

        File dest = new File(absolutePath , imageFileName);
        try {
            multipartFile.transferTo(dest);
            String imageUrl = "http://localhost:8080/images/diary/diaryImage/" + imageFileName;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("errno" , 0);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(imageUrl);

            jsonObject.put("data" , jsonArray);


            System.out.println(jsonObject);
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("上传失败，请重试");
        }
    }

//    日记内容视频上传
//    @UserLoginToken
    @PostMapping("/diaryVideoFileUpLoadAndReturnUrl")
    public JSON diaryVideoFileUpLoadAndReturnUrl(@RequestParam("myVideoFileName")
                                                             MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new RuntimeException("文件不能为空");
        }
        String originalFilename = multipartFile.getOriginalFilename();
        File absolutePath = new File(uploadVideoAbsolutePath);
        if (!absolutePath.exists()) {
            absolutePath.mkdirs();
        }
        String imageFileName = "" + UUID.randomUUID() + UUID.randomUUID().hashCode() +
                originalFilename.substring(originalFilename.lastIndexOf("."));
        File dest = new File(absolutePath , imageFileName);
        try {
            multipartFile.transferTo(dest);
//            return "http://localhost:8080/images/diaryImage/" + imageFileName;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("errno" , 0);
//            jsonObject.put("data" , "http://localhost:8080/images/diaryImage/" + imageFileName);
//            JSONArray jsonArray = new JSONArray();
//            jsonArray.add("http://localhost:8080/images/diary/diaryVideo/" + imageFileName);
//            jsonObject.put("data" , jsonArray);
            JSONObject url = new JSONObject();
            url.put("url" , "http://localhost:8080/images/diary/diaryVideo/" + imageFileName);
            jsonObject.put("data" , url);
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("上传失败，请重试");
        }
    }

    @PostMapping("/diaryMusicFileUpLoadAndReturnUrl")
    public String diaryMusicFileUpLoadAndReturnUrl(@RequestParam("myMusicFileName")
                                                         MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new RuntimeException("文件不能为空");
        }
        String originalFilename = multipartFile.getOriginalFilename();
        File absolutePath = new File(uploadMusciAbsolutePath);
        if (!absolutePath.exists()) {
            absolutePath.mkdirs();
        }
        String musicFileName = "" + UUID.randomUUID() + UUID.randomUUID().hashCode() +
                originalFilename.substring(originalFilename.lastIndexOf("."));
        File dest = new File(absolutePath , musicFileName);
        try {
            multipartFile.transferTo(dest);

            return "http://localhost:8080/images/diary/musics/" + musicFileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("上传失败，请重试");
        }
    }

//    日记上传
    @UserLoginToken
    @PostMapping("/diaryInfoUpload")
    public String diaryInfoUpload(@RequestBody Diary diary , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        if (user == null) {
            throw new RuntimeException("日记上传失败，用户不存在，请退出重新登陆");
        }
        List<SensitiveWords> sensitiveWordsList = sensitiveWordsService.list();
        for (SensitiveWords sensitiveWords : sensitiveWordsList) {
            if (diary.getTitle().contains(sensitiveWords.getSensitiveWord())) {
                throw new RuntimeException("该日记标题中含有敏感词:" +
                        sensitiveWords.getSensitiveWord() + ",请修改后重新上传");
            }else if (diary.getContent().contains(sensitiveWords.getSensitiveWord())) {
                throw new RuntimeException("该日记内容中含有敏感词:" +
                        sensitiveWords.getSensitiveWord() + ",请修改后重新上传");
            }
        }
        diary.setAuthorId(user.getId());
        diary.setAuthorName(user.getUserName());
        diary.setState("待审批");
        boolean flag = diaryService.save(diary);
        if (flag) {
            return "日记上传成功";
        }else {
            throw new RuntimeException("日记上传失败，请重试");
        }
    }

    /*日记页首页的最新日记四条数据*/
    @GetMapping("/diaryHomePageNewDiaryDisplayFourPieces")
    public List<Diary> diaryHomePageNewDiaryDisplayFourPieces() {
        List<Diary> diaryList = diaryService.getNewDiaryFourPieces();
        return diaryList;
    }
    /*日记页首页的推荐日记数据*/
    @GetMapping("/diaryHomePageRecommendDiaryDisplayOnePieces")
    public Diary diaryHomePageRecommendDiaryDisplayOnePieces() {
        Page<Diary> recommendDiaryListPageing = diaryService.getRecommendDiaryListPageing(1);
        List<Diary> records = recommendDiaryListPageing.getRecords();
        if (records == null) {
            throw new RuntimeException("暂无日记");
        }
        if (records.size() >= 1) {
            return records.get(0);
        }else {
            return null;
        }
    }

    /*日记页首页的顶客排行四条数据*/
    @GetMapping("/diaryHomePageTopGuestDiaryDisplayFourPieces")
    public List<Diary> diaryHomePageTopGuestDiaryDisplayFourPieces() {
        Page<Diary> topGuestDiaryListPageing = diaryService.getTopGuestDiaryListPageing(1);
        List<Diary> records = topGuestDiaryListPageing.getRecords();
        if (records == null) {
            throw new RuntimeException("暂无日记");
        }
        if (records.size() >= 4) {
            return records.subList(0, 4);
        }else {
            return records;
        }
    }


    /*分页查询最新日记*/
    @GetMapping("/newDiaryListDisplay")
    public Page<Diary> newDiaryListDisplay(@RequestParam("currentIndex") int currentPage) {
        Page<Diary> diaryListPageing = diaryService.getNewDiaryListPageing(currentPage);
//        System.out.println("最新日记列表");
        return diaryListPageing;
    }

    @GetMapping("/recommendDiaryListDisplay")
    public Page<Diary> recommendDiaryListDisplay(@RequestParam("currentIndex") int currentPage) {
//        Page<Diary> diaryListPageing = diaryService.getNewDiaryListPageing(currentPage);
//        return diaryListPageing;
        Page<Diary> recommendDiaryListPageing = diaryService.getRecommendDiaryListPageing(currentPage);

//        System.out.println("推荐日记列表");
//        System.out.println(recommendDiaryListPageing.toString());
        return recommendDiaryListPageing;
    }

    @GetMapping("topGuestDiaryListDisplay")
    public Page<Diary> topGuestDiaryListDisplay(@RequestParam("currentIndex") int currentPage) {
//        Page<Diary> diaryListPageing = diaryService.getNewDiaryListPageing(currentPage);
//        return diaryListPageing;

        Page<Diary> topGuestDiaryListPageing = diaryService.getTopGuestDiaryListPageing(currentPage);
//        System.out.println("顶客排行列表");
//        System.out.println(recommendDiaryListPageing.toString());
        return topGuestDiaryListPageing;
    }
    /*根据日记id查询日记*/
    @GetMapping("/getDiaryByDiaryId")
    public Diary getDiaryByDiaryId(@RequestParam(name = "diaryId")Long diaryId) {



//        Long diary_Id = Long.parseLong(diaryId);

//        Diary diary = diaryService.getById(diary_Id);
        Diary diary = diaryService.getById(diaryId);


        if (diary != null) {
            return diary;
        }else {
            throw new RuntimeException("该日记不存在");
        }
    }



    /**
     * 功能描述：根据博客id，查询此博客的所有评论信息（链表类型的数据）
     * @param
     * @return 博客的评论信息
     */
    @GetMapping("/queryObserveByBlogId")
    public List<ObserveNodeBO> queryObserveByBlogId (Long objId) {
//        long obj_id = Long.parseLong(objId);

//        List<ObserveNodeBO> observeNodeBOList = observeService.queryObserveByObjId("放空日记", obj_id);
        List<ObserveNodeBO> observeNodeBOList = observeService.queryObserveByObjId("放空日记", objId);
//        System.out.println(observeNodeBOList);
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
    @PostMapping("/addDiaryObserve")
    public List<ObserveNodeBO> addDiaryObserve(@RequestBody Observe observe , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        observe.setObserverId(user.getId());
        System.out.println(observe);
        boolean flag = observeService.save(observe);

        if (flag) {
            List<ObserveNodeBO> observeNodeBOList = observeService.queryObserveByObjId("放空日记", observe.getObjId());
            for (Observe item : observeNodeBOList) {
                System.out.println(item);
            }
            return observeNodeBOList;
        }else {
            throw new RuntimeException("评论失败,请重试");
        }

//        System.out.println(observe);
//        return null;
    }


//    前端通过关键字获取日记
    @GetMapping("/getDiaryByKeyValue")
    public List<Diary> getDiaryByKeyValue(@RequestParam("diaryKeyValue") String diaryKeyValue) {
        List<Diary> diaryList = diaryService.getDiaryByKeyValue(diaryKeyValue);
        return diaryList;
    }
}




















//    @UserLoginToken
//    @PostMapping("/saveDiaryStar")
//    /*日记点赞功能*/
//    public String saveDiaryStar(@RequestBody String diaryId , HttpServletRequest httpServletRequest) {
//        String authorization = httpServletRequest.getHeader("Authorization");
//        User userservice = tokenUtils.parseTokenAndGetUser(authorization);
//        if (userservice == null) {
//            throw new RuntimeException("点赞失败，用户不存在，请退出重新登陆");
//        }
//
//        JSONObject jsonObject = JSON.parseObject(diaryId);
//
//        long diary_id = Long.parseLong(jsonObject.getString("diaryId"));
//
//        Diary diary = diaryService.getById(diary_id);
////        System.out.println(diarycontroller);
//        if (diary == null) {
//            throw new RuntimeException("点赞失败，该日记不存在");
//        }
////        System.out.println(diarycontroller);
////        查询取消点赞的记录
//
//        ProductionStar productionStar = productionStarService.getOneHasDelDiaryStar(userservice.getId() , diary.getId() , "放空日记");
//        System.out.println(productionStar);
//        boolean flag = false;
//
//        if (productionStar == null) {
//            productionStar = new ProductionStar();
//            productionStar.setUserId(userservice.getId());
//            productionStar.setObjId(diary.getId());
//            productionStar.setType("放空日记");
////            System.out.println(diaryStar);
//            flag = productionStarService.save(productionStar);
//        }else {
//            flag = productionStarService.reLikeDiary(productionStar.getId());
//        }
//
////        DiaryStar diaryStar;
//
//        if (flag) {
//            return "点赞成功";
//        }else {
//            throw new RuntimeException("点赞失败，请重试");
//        }
//    }
//
//    /*取消日记点赞*/
//    @UserLoginToken
//    @PostMapping("/cancelDiaryStar")
//    public String cancelDiaryStar(@RequestBody String diaryId , HttpServletRequest httpServletRequest) {
//        String authorization = httpServletRequest.getHeader("Authorization");
//
//        User userservice = tokenUtils.parseTokenAndGetUser(authorization);
//
//        if (userservice == null) {
//            throw new RuntimeException("取消点赞失败，用户不存在，请退出重新登陆");
//        }
//
//        JSONObject jsonObject = JSON.parseObject(diaryId);
//
//        long diary_id = Long.parseLong(jsonObject.getString("diaryId"));
//
//        Diary diary = diaryService.getById(diary_id);
//
//        if (diary == null) {
//            throw new RuntimeException("取消点赞失败，该日记不存在");
//        }
//
//        ProductionStar productionStar = productionStarService.getOneDiaryStar(userservice.getId(), diary.getId(), "放空日记");
//
//        if (productionStar == null) {
//            throw new RuntimeException("取消点赞失败，您未赞过");
//        }
//
//        boolean flag = productionStarService.removeById(productionStar.getId());
//
//        if (flag) {
//            return "取消点赞成功";
//        }else {
//            throw new RuntimeException("取消点赞失败，请重试");
//        }
//    }
//
//    @UserLoginToken
//    @PostMapping("/saveDiaryCollection")
//    /* 日记收藏功能 */
//    public String saveDiaryCollection(@RequestBody String diaryId , HttpServletRequest httpServletRequest) {
//        String authorization = httpServletRequest.getHeader("Authorization");
//        User userservice = tokenUtils.parseTokenAndGetUser(authorization);
//        if (userservice == null) {
//            throw new RuntimeException("收藏失败，用户不存在，请退出重新登陆");
//        }
//        JSONObject jsonObject = JSON.parseObject(diaryId);
//
//        long diary_id = Long.parseLong(jsonObject.getString("diaryId"));
//
//        Diary diary = diaryService.getById(diary_id);
////        System.out.println(diarycontroller);
//        if (diary == null) {
//            throw new RuntimeException("收藏失败，该日记不存在");
//        }
////        System.out.println(diarycontroller);
////        查询取消点赞的记录
//
//        ProductionCollection productionCollection = productionCollectionService.getOneHasDelDiaryCollection(userservice.getId(), diary.getId() , "放空日记");
//
//        boolean flag = false;
//
//        if (productionCollection == null) {
//            productionCollection = new ProductionCollection();
//            productionCollection.setUserId(userservice.getId());
//            productionCollection.setObjId(diary.getId());
//            productionCollection.setType("放空日记");
//
//            flag = productionCollectionService.save(productionCollection);
//        }else {
//            flag = productionCollectionService.reCollectDiary(productionCollection.getId());
//        }
//
//
//        if (flag) {
//            return "收藏成功";
//        }else {
//            throw new RuntimeException("收藏失败，请重试");
//        }
//    }
//
//    /*取消日记收藏*/
//    @UserLoginToken
//    @PostMapping("/cancelDiaryCollection")
//    public String cancelDiaryCollection(@RequestBody String diaryId , HttpServletRequest httpServletRequest) {
//        String authorization = httpServletRequest.getHeader("Authorization");
//        User userservice = tokenUtils.parseTokenAndGetUser(authorization);
//
//
//        if (userservice == null) {
//            throw new RuntimeException("取消收藏失败，该用户不存在，请退出重新登陆");
//        }
//
//        JSONObject jsonObject = JSON.parseObject(diaryId);
//
//        long diary_id = Long.parseLong(jsonObject.getString("diaryId"));
//
//        Diary diary = diaryService.getById(diary_id);
//
//        if (diary == null) {
//            throw new RuntimeException("取消收藏失败，该日记不存在");
//        }
//
//        ProductionCollection productionCollection = productionCollectionService.getOneDiaryCollection(userservice.getId(), diary.getId(), "放空日记");
//        if (productionCollection == null) {
//            throw new RuntimeException("取消收藏失败，您未赞过");
//        }
//
//        boolean flag = productionCollectionService.removeById(productionCollection.getId());
//
//        if (flag) {
//            return "取消收藏成功";
//        }else {
//            throw new RuntimeException("取消收藏失败，请重试");
//        }
//    }
//
//
//
//    /*查看用户是否已点赞*/
//    @GetMapping("/hasAlreadLike")
//    @UserLoginToken
//    public String hasAlreadLike(@RequestParam(name = "diaryId")String diaryId , HttpServletRequest httpServletRequest){
//        String authorization = httpServletRequest.getHeader("Authorization");
//        User userservice = tokenUtils.parseTokenAndGetUser(authorization);
//
//        if (userservice == null) {
//            throw new RuntimeException("用户不存在，请退出重新登陆");
//        }
//
////        JSONObject jsonObject = JSON.parseObject(diaryId);
////        System.out.println(diaryId.getClass());
//
//        long diary_id = Long.parseLong(diaryId);
////
//        Diary diary = diaryService.getById(diary_id);
//
//        if (diary == null) {
//            throw new RuntimeException("该日记不存在");
//        }
//
//        ProductionStar productionStar = productionStarService.getOneDiaryStar(userservice.getId(), diary.getId(), "放空日记");
//
//        if (productionStar != null) {
////            throw new RuntimeException("您未赞过");
//            return "like";
//        }else {
//            return "not like";
//        }
//    }
//
//    /*查看用户是否已收藏*/
//    @GetMapping("/hasAlreadCollect")
//    @UserLoginToken
//    public String hasAlreadCollect(@RequestParam(name = "diaryId")String diaryId , HttpServletRequest httpServletRequest){
//        String authorization = httpServletRequest.getHeader("Authorization");
//        User userservice = tokenUtils.parseTokenAndGetUser(authorization);
//
//        if (userservice == null) {
//            throw new RuntimeException("用户不存在，请退出重新登陆");
//        }
//        long diary_id = Long.parseLong(diaryId);
//        Diary diary = diaryService.getById(diary_id);
//
//        if (diary == null) {
//            throw new RuntimeException("该日记不存在");
//        }
//
//
//        ProductionCollection productionCollection = productionCollectionService.getOneDiaryCollection(userservice.getId(), diary.getId(), "放空日记");
//        if (productionCollection != null) {
////            throw new RuntimeException("您未赞过");
//            return "collect";
//        }else {
//            return "not collect";
//        }
//    }
