package com.example.emptyblogproject.controller.diary;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.annotation.UserLoginToken;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.dairy.diarystar.DiaryStar;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.diary.DiaryService;
import com.example.emptyblogproject.service.diary.diarystar.DiaryStarService;
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
    DiaryStarService diaryStarService;

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
        User user = userService.getById(userId);

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

    /*日记页首页的最新日记三条数据*/
    @GetMapping("/diaryHomePageNewDiaryDisplayThreePieces")
    public List<Diary> diaryHomePageNewDiaryDisplayThreePieces() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        queryWrapper.last("limit 0,4");
        List<Diary> diaryList = diaryService.list(queryWrapper);


        return diaryList;
    }

    /*分页查询最新日记*/
    @GetMapping("/newDiaryListDisplay")
    public Page<Diary> newDiaryListDisplay(@RequestParam("currentIndex") int currentPage) {
//        System.out.println(currentPage);
        Page<Diary> page = new Page<>(currentPage , 10);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");

        Page page1 = diaryService.page(page, queryWrapper);

//        System.out.println(page1);
        return page1;
//        return null;
    }

    /*根据日记id查询日记*/
    @GetMapping("/getDiaryByDiaryId")
    public Diary getDiaryByDiaryId(@RequestParam("diaryId")String diaryId) {
//        System.out.println(diaryId);
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
    public String saveDiaryStar(@RequestParam("diaryId") Long diaryId , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        long userId = Long.parseLong(authorization);

        User user = userService.getById(userId);

        if (user == null) {
            throw new RuntimeException("点赞失败，该作者不存在");
        }

        Diary diary = diaryService.getById(diaryId);

        if (diary == null) {
            throw new RuntimeException("点赞失败，该日记不存在");
        }

//        查询取消点赞的记录
        QueryWrapper<DiaryStar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id" , user.getId());
        queryWrapper.eq("diary_id" , diary.getId());
        queryWrapper.eq("del" , 1);
        DiaryStar diaryStar = diaryStarService.getOne(queryWrapper);
        boolean flag = false;

        if (diaryStar == null) {
            diaryStar = new DiaryStar();
            diaryStar.setUserId(user.getId());
            diaryStar.setDiaryId(diary.getId());
            flag = diaryStarService.save(diaryStar);
        }else {
            diaryStarService.reLikeDiary(diaryStar.getId());
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
    public String cancelDiaryStar(@RequestParam("diaryId") Long diaryId , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        long userId = Long.parseLong(authorization);

        User user = userService.getById(userId);

        if (user == null) {
            throw new RuntimeException("取消点赞失败，该作者不存在");
        }

        Diary diary = diaryService.getById(diaryId);

        if (diary == null) {
            throw new RuntimeException("取消点赞失败，该日记不存在");
        }

//        DiaryStar diaryStar = new DiaryStar();
//        diaryStar.setUserId(user.getId());
//        diaryStar.setDiaryId(diary.getId());
        QueryWrapper<DiaryStar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id" , user.getId());
        queryWrapper.eq("diary_id" , diary.getId());
        DiaryStar diaryStar = diaryStarService.getOne(queryWrapper);

        if (diaryStar == null) {
            throw new RuntimeException("取消点赞失败，您未赞过");
        }

        boolean flag = diaryStarService.removeById(diaryId);

        if (flag) {
            return "取消点赞成功";
        }else {
            throw new RuntimeException("取消点赞失败，请重试");
        }
    }
}
