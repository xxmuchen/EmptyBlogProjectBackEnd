package com.example.emptyblogproject.controller.diary;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.annotation.UserLoginToken;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.productioncollection.ProductionCollection;
import com.example.emptyblogproject.bean.productionstar.ProductionStar;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.diaryservice.DiaryService;
import com.example.emptyblogproject.service.productioncollectionservice.ProductionCollectionService;
import com.example.emptyblogproject.service.productionstarservice.ProductionStarService;
import com.example.emptyblogproject.service.user.UserService;
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

    @Value("${file.diaryImagePath}")
    private String uploadImageAbsolutePath;

    @Value("${file.diaryVideoPath}")
    private String uploadVideoAbsolutePath;

    @Autowired
    UserService userService;

    @Autowired
    DiaryService diaryService;

    @Autowired
    ProductionStarService productionStarService;

    @Autowired
    ProductionCollectionService productionCollectionService;

//    日记内容图片上传
    @UserLoginToken
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
//            return "http://localhost:8080/images/diaryImage/" + imageFileName;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("errno" , 0);
//            jsonObject.put("data" , "http://localhost:8080/images/diaryImage/" + imageFileName);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add("http://localhost:8080/images/diary/diaryImage/" + imageFileName);
            jsonObject.put("data" , jsonArray);
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("上传失败，请重试");
        }
    }

//    日记内容视频上传
    @UserLoginToken
    @PostMapping("/diaryVideoFileUpLoadAndReturnUrl")
    public JSON diaryVideoFileUpLoadAndReturnUrl(@RequestParam("myVideoFileName") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new RuntimeException("文件不能为空");
        }
        String originalFilename = multipartFile.getOriginalFilename();

        File absolutePath = new File(uploadVideoAbsolutePath);
        if (!absolutePath.exists()) {
            absolutePath.mkdirs();
        }

        String imageFileName = "" + UUID.randomUUID() + UUID.randomUUID().hashCode() + originalFilename.substring(originalFilename.lastIndexOf("."));

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

//    日记上传
    @UserLoginToken
    @PostMapping("/diaryInfoUpload")
    public String diaryInfoUpload(@RequestBody Diary diary , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        String userId = null;
        try {
            userId = JWT.decode(authorization).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        Long user_id = Long.parseLong(userId);
        User user = userService.getById(user_id);

        diary.setAuthorId(user.getId());
        diary.setAuthorName(user.getUserName());
//        System.out.println(diary);
        boolean flag = diaryService.save(diary);
        if (flag) {
            return "日记上传成功";
        }else {
            throw new RuntimeException("日记上传失败，请重试");
        }
    }

    /*日记页首页的最新日记四条数据*/
    @GetMapping("/diaryHomePageNewDiaryDisplayThreePieces")
    public List<Diary> diaryHomePageNewDiaryDisplayThreePieces() {
        List<Diary> diaryList = diaryService.getNewDiaryFourPieces();
        return diaryList;
    }

    /*分页查询最新日记*/
    @GetMapping("/newDiaryListDisplay")
    public Page<Diary> newDiaryListDisplay(@RequestParam("currentIndex") int currentPage) {
        Page<Diary> diaryListPageing = diaryService.getNewDiaryListPageing(currentPage);
        return diaryListPageing;
    }

    /*根据日记id查询日记*/
    @GetMapping("/getDiaryByDiaryId")
    public Diary getDiaryByDiaryId(@RequestParam(name = "diaryId")String diaryId , HttpServletRequest httpServletRequest) {
//        System.out.println(diaryId);
        httpServletRequest.getHeader("token");


        Long diary_Id = Long.parseLong(diaryId);

        Diary diary = diaryService.getById(diary_Id);
//        System.out.println(diary);
//        diaryService.getById()

        if (diary != null) {
            return diary;
        }else {
            throw new RuntimeException("该日记不存在");
        }
    }

    @UserLoginToken
    @PostMapping("/saveDiaryStar")
    /*日记点赞功能*/
    public String saveDiaryStar(@RequestBody String diaryId , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        String userId = null;
        try {
            userId = JWT.decode(authorization).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
//        System.out.println(userId);
        Long user_id = Long.parseLong(userId);
        User user = userService.getById(user_id);
//        System.out.println(user);
        if (user == null) {
            throw new RuntimeException("点赞失败，该作者不存在");
        }
        JSONObject jsonObject = JSON.parseObject(diaryId);

        long diary_id = Long.parseLong(jsonObject.getString("diaryId"));

        Diary diary = diaryService.getById(diary_id);
//        System.out.println(diary);
        if (diary == null) {
            throw new RuntimeException("点赞失败，该日记不存在");
        }
//        System.out.println(diary);
//        查询取消点赞的记录

        ProductionStar productionStar = productionStarService.getOneHasDelDiaryStar(user.getId() , diary.getId() , "放空日记");
        System.out.println(productionStar);
        boolean flag = false;

        if (productionStar == null) {
            productionStar = new ProductionStar();
            productionStar.setUserId(user.getId());
            productionStar.setObjId(diary.getId());
            productionStar.setType("放空日记");
//            System.out.println(diaryStar);
            flag = productionStarService.save(productionStar);
        }else {
            flag = productionStarService.reLikeDiary(productionStar.getId());
        }

//        DiaryStar diaryStar;

        if (flag) {
            return "点赞成功";
        }else {
            throw new RuntimeException("点赞失败，请重试");
        }
    }

    /*取消日记点赞*/
    @UserLoginToken
    @PostMapping("/cancelDiaryStar")
    public String cancelDiaryStar(@RequestBody String diaryId , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        String userId = null;
        try {
            userId = JWT.decode(authorization).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        Long user_id = Long.parseLong(userId);
        User user = userService.getById(user_id);

        if (user == null) {
            throw new RuntimeException("取消点赞失败，该作者不存在");
        }

        JSONObject jsonObject = JSON.parseObject(diaryId);

        long diary_id = Long.parseLong(jsonObject.getString("diaryId"));

        Diary diary = diaryService.getById(diary_id);

        if (diary == null) {
            throw new RuntimeException("取消点赞失败，该日记不存在");
        }

        ProductionStar productionStar = productionStarService.getOneDiaryStar(user.getId(), diary.getId(), "放空日记");

        if (productionStar == null) {
            throw new RuntimeException("取消点赞失败，您未赞过");
        }

        boolean flag = productionStarService.removeById(productionStar.getId());

        if (flag) {
            return "取消点赞成功";
        }else {
            throw new RuntimeException("取消点赞失败，请重试");
        }
    }

    @UserLoginToken
    @PostMapping("/saveDiaryCollection")
    /* 日记收藏功能 */
    public String saveDiaryCollection(@RequestBody String diaryId , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        String userId = null;
        try {
            userId = JWT.decode(authorization).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
//        System.out.println(userId);
        Long user_id = Long.parseLong(userId);
        User user = userService.getById(user_id);
//        System.out.println(user);
        if (user == null) {
            throw new RuntimeException("收藏失败，该作者不存在");
        }
        JSONObject jsonObject = JSON.parseObject(diaryId);

        long diary_id = Long.parseLong(jsonObject.getString("diaryId"));

        Diary diary = diaryService.getById(diary_id);
//        System.out.println(diary);
        if (diary == null) {
            throw new RuntimeException("收藏失败，该日记不存在");
        }
//        System.out.println(diary);
//        查询取消点赞的记录

        ProductionCollection productionCollection = productionCollectionService.getOneHasDelDiaryCollection(user.getId(), diary.getId() , "放空日记");

        boolean flag = false;

        if (productionCollection == null) {
            productionCollection = new ProductionCollection();
            productionCollection.setUserId(user.getId());
            productionCollection.setObjId(diary.getId());
            productionCollection.setType("放空日记");

            flag = productionCollectionService.save(productionCollection);
        }else {
            flag = productionCollectionService.reCollectDiary(productionCollection.getId());
        }


        if (flag) {
            return "收藏成功";
        }else {
            throw new RuntimeException("收藏失败，请重试");
        }
    }

    /*取消日记收藏*/
    @UserLoginToken
    @PostMapping("/cancelDiaryCollection")
    public String cancelDiaryCollection(@RequestBody String diaryId , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        String userId = null;
        try {
            userId = JWT.decode(authorization).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        Long user_id = Long.parseLong(userId);
        User user = userService.getById(user_id);

        if (user == null) {
            throw new RuntimeException("取消收藏失败，该作者不存在");
        }

        JSONObject jsonObject = JSON.parseObject(diaryId);

        long diary_id = Long.parseLong(jsonObject.getString("diaryId"));

        Diary diary = diaryService.getById(diary_id);

        if (diary == null) {
            throw new RuntimeException("取消收藏失败，该日记不存在");
        }

        ProductionCollection productionCollection = productionCollectionService.getOneDiaryCollection(user.getId(), diary.getId(), "放空日记");
        if (productionCollection == null) {
            throw new RuntimeException("取消收藏失败，您未赞过");
        }

        boolean flag = productionCollectionService.removeById(productionCollection.getId());

        if (flag) {
            return "取消收藏成功";
        }else {
            throw new RuntimeException("取消收藏失败，请重试");
        }
    }



    /*查看用户是否已点赞*/
    @GetMapping("/hasAlreadLike")
    @UserLoginToken
    public String hasAlreadLike(@RequestParam(name = "diaryId")String diaryId , HttpServletRequest httpServletRequest){
        String authorization = httpServletRequest.getHeader("Authorization");
        String userId = null;
        try {
            userId = JWT.decode(authorization).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        Long user_id = Long.parseLong(userId);
        User user = userService.getById(user_id);

        if (user == null) {
            throw new RuntimeException("该作者不存在");
        }

//        JSONObject jsonObject = JSON.parseObject(diaryId);
//        System.out.println(diaryId.getClass());

        long diary_id = Long.parseLong(diaryId);
//
        Diary diary = diaryService.getById(diary_id);

        if (diary == null) {
            throw new RuntimeException("该日记不存在");
        }

        ProductionStar productionStar = productionStarService.getOneDiaryStar(user.getId(), diary.getId(), "放空日记");

        if (productionStar != null) {
//            throw new RuntimeException("您未赞过");
            return "like";
        }else {
            return "not like";
        }
    }

    /*查看用户是否已收藏*/
    @GetMapping("/hasAlreadCollect")
    @UserLoginToken
    public String hasAlreadCollect(@RequestParam(name = "diaryId")String diaryId , HttpServletRequest httpServletRequest){
        String authorization = httpServletRequest.getHeader("Authorization");
        String userId = null;
        try {
            userId = JWT.decode(authorization).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        Long user_id = Long.parseLong(userId);
        User user = userService.getById(user_id);

        if (user == null) {
            throw new RuntimeException("该作者不存在");
        }
        long diary_id = Long.parseLong(diaryId);
        Diary diary = diaryService.getById(diary_id);

        if (diary == null) {
            throw new RuntimeException("该日记不存在");
        }


        ProductionCollection productionCollection = productionCollectionService.getOneDiaryCollection(user.getId(), diary.getId(), "放空日记");
        if (productionCollection != null) {
//            throw new RuntimeException("您未赞过");
            return "collect";
        }else {
            return "not collect";
        }
    }
}
