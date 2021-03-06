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
//    ????????????????????????
//    @UserLoginToken
//    ???????????????????????????????????????
    @PostMapping("/diaryImageFileUpLoadAndReturnUrl")
    public JSON diaryImageFileUpLoadAndReturnUrl(@RequestParam("myImageFileName") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new RuntimeException("??????????????????");
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
            throw new RuntimeException("????????????????????????");
        }
    }

//    ????????????????????????
//    @UserLoginToken
    @PostMapping("/diaryVideoFileUpLoadAndReturnUrl")
    public JSON diaryVideoFileUpLoadAndReturnUrl(@RequestParam("myVideoFileName")
                                                             MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new RuntimeException("??????????????????");
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
            throw new RuntimeException("????????????????????????");
        }
    }

    @PostMapping("/diaryMusicFileUpLoadAndReturnUrl")
    public String diaryMusicFileUpLoadAndReturnUrl(@RequestParam("myMusicFileName")
                                                         MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new RuntimeException("??????????????????");
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
            throw new RuntimeException("????????????????????????");
        }
    }

//    ????????????
    @UserLoginToken
    @PostMapping("/diaryInfoUpload")
    public String diaryInfoUpload(@RequestBody Diary diary , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        if (user == null) {
            throw new RuntimeException("????????????????????????????????????????????????????????????");
        }
        List<SensitiveWords> sensitiveWordsList = sensitiveWordsService.list();
        for (SensitiveWords sensitiveWords : sensitiveWordsList) {
            if (diary.getTitle().contains(sensitiveWords.getSensitiveWord())) {
                throw new RuntimeException("?????????????????????????????????:" +
                        sensitiveWords.getSensitiveWord() + ",????????????????????????");
            }else if (diary.getContent().contains(sensitiveWords.getSensitiveWord())) {
                throw new RuntimeException("?????????????????????????????????:" +
                        sensitiveWords.getSensitiveWord() + ",????????????????????????");
            }
        }
        diary.setAuthorId(user.getId());
        diary.setAuthorName(user.getUserName());
        diary.setState("?????????");
        boolean flag = diaryService.save(diary);
        if (flag) {
            return "??????????????????";
        }else {
            throw new RuntimeException("??????????????????????????????");
        }
    }

    /*??????????????????????????????????????????*/
    @GetMapping("/diaryHomePageNewDiaryDisplayFourPieces")
    public List<Diary> diaryHomePageNewDiaryDisplayFourPieces() {
        List<Diary> diaryList = diaryService.getNewDiaryFourPieces();
        return diaryList;
    }
    /*????????????????????????????????????*/
    @GetMapping("/diaryHomePageRecommendDiaryDisplayOnePieces")
    public Diary diaryHomePageRecommendDiaryDisplayOnePieces() {
        Page<Diary> recommendDiaryListPageing = diaryService.getRecommendDiaryListPageing(1);
        List<Diary> records = recommendDiaryListPageing.getRecords();
        if (records == null) {
            throw new RuntimeException("????????????");
        }
        if (records.size() >= 1) {
            return records.get(0);
        }else {
            return null;
        }
    }

    /*??????????????????????????????????????????*/
    @GetMapping("/diaryHomePageTopGuestDiaryDisplayFourPieces")
    public List<Diary> diaryHomePageTopGuestDiaryDisplayFourPieces() {
        Page<Diary> topGuestDiaryListPageing = diaryService.getTopGuestDiaryListPageing(1);
        List<Diary> records = topGuestDiaryListPageing.getRecords();
        if (records == null) {
            throw new RuntimeException("????????????");
        }
        if (records.size() >= 4) {
            return records.subList(0, 4);
        }else {
            return records;
        }
    }


    /*????????????????????????*/
    @GetMapping("/newDiaryListDisplay")
    public Page<Diary> newDiaryListDisplay(@RequestParam("currentIndex") int currentPage) {
        Page<Diary> diaryListPageing = diaryService.getNewDiaryListPageing(currentPage);
//        System.out.println("??????????????????");
        return diaryListPageing;
    }

    @GetMapping("/recommendDiaryListDisplay")
    public Page<Diary> recommendDiaryListDisplay(@RequestParam("currentIndex") int currentPage) {
//        Page<Diary> diaryListPageing = diaryService.getNewDiaryListPageing(currentPage);
//        return diaryListPageing;
        Page<Diary> recommendDiaryListPageing = diaryService.getRecommendDiaryListPageing(currentPage);

//        System.out.println("??????????????????");
//        System.out.println(recommendDiaryListPageing.toString());
        return recommendDiaryListPageing;
    }

    @GetMapping("topGuestDiaryListDisplay")
    public Page<Diary> topGuestDiaryListDisplay(@RequestParam("currentIndex") int currentPage) {
//        Page<Diary> diaryListPageing = diaryService.getNewDiaryListPageing(currentPage);
//        return diaryListPageing;

        Page<Diary> topGuestDiaryListPageing = diaryService.getTopGuestDiaryListPageing(currentPage);
//        System.out.println("??????????????????");
//        System.out.println(recommendDiaryListPageing.toString());
        return topGuestDiaryListPageing;
    }
    /*????????????id????????????*/
    @GetMapping("/getDiaryByDiaryId")
    public Diary getDiaryByDiaryId(@RequestParam(name = "diaryId")Long diaryId) {



//        Long diary_Id = Long.parseLong(diaryId);

//        Diary diary = diaryService.getById(diary_Id);
        Diary diary = diaryService.getById(diaryId);


        if (diary != null) {
            return diary;
        }else {
            throw new RuntimeException("??????????????????");
        }
    }



    /**
     * ???????????????????????????id??????????????????????????????????????????????????????????????????
     * @param
     * @return ?????????????????????
     */
    @GetMapping("/queryObserveByBlogId")
    public List<ObserveNodeBO> queryObserveByBlogId (Long objId) {
//        long obj_id = Long.parseLong(objId);

//        List<ObserveNodeBO> observeNodeBOList = observeService.queryObserveByObjId("????????????", obj_id);
        List<ObserveNodeBO> observeNodeBOList = observeService.queryObserveByObjId("????????????", objId);
//        System.out.println(observeNodeBOList);
        return observeNodeBOList;
    }
    /**
     * ???????????????????????????id?????????????????????????????????????????????????????????
     * @param
     * @return ?????????????????????????????????
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
            List<ObserveNodeBO> observeNodeBOList = observeService.queryObserveByObjId("????????????", observe.getObjId());
            for (Observe item : observeNodeBOList) {
                System.out.println(item);
            }
            return observeNodeBOList;
        }else {
            throw new RuntimeException("????????????,?????????");
        }

//        System.out.println(observe);
//        return null;
    }


//    ?????????????????????????????????
    @GetMapping("/getDiaryByKeyValue")
    public List<Diary> getDiaryByKeyValue(@RequestParam("diaryKeyValue") String diaryKeyValue) {
        List<Diary> diaryList = diaryService.getDiaryByKeyValue(diaryKeyValue);
        return diaryList;
    }
}




















//    @UserLoginToken
//    @PostMapping("/saveDiaryStar")
//    /*??????????????????*/
//    public String saveDiaryStar(@RequestBody String diaryId , HttpServletRequest httpServletRequest) {
//        String authorization = httpServletRequest.getHeader("Authorization");
//        User userservice = tokenUtils.parseTokenAndGetUser(authorization);
//        if (userservice == null) {
//            throw new RuntimeException("??????????????????????????????????????????????????????");
//        }
//
//        JSONObject jsonObject = JSON.parseObject(diaryId);
//
//        long diary_id = Long.parseLong(jsonObject.getString("diaryId"));
//
//        Diary diary = diaryService.getById(diary_id);
////        System.out.println(diarycontroller);
//        if (diary == null) {
//            throw new RuntimeException("?????????????????????????????????");
//        }
////        System.out.println(diarycontroller);
////        ???????????????????????????
//
//        ProductionStar productionStar = productionStarService.getOneHasDelDiaryStar(userservice.getId() , diary.getId() , "????????????");
//        System.out.println(productionStar);
//        boolean flag = false;
//
//        if (productionStar == null) {
//            productionStar = new ProductionStar();
//            productionStar.setUserId(userservice.getId());
//            productionStar.setObjId(diary.getId());
//            productionStar.setType("????????????");
////            System.out.println(diaryStar);
//            flag = productionStarService.save(productionStar);
//        }else {
//            flag = productionStarService.reLikeDiary(productionStar.getId());
//        }
//
////        DiaryStar diaryStar;
//
//        if (flag) {
//            return "????????????";
//        }else {
//            throw new RuntimeException("????????????????????????");
//        }
//    }
//
//    /*??????????????????*/
//    @UserLoginToken
//    @PostMapping("/cancelDiaryStar")
//    public String cancelDiaryStar(@RequestBody String diaryId , HttpServletRequest httpServletRequest) {
//        String authorization = httpServletRequest.getHeader("Authorization");
//
//        User userservice = tokenUtils.parseTokenAndGetUser(authorization);
//
//        if (userservice == null) {
//            throw new RuntimeException("????????????????????????????????????????????????????????????");
//        }
//
//        JSONObject jsonObject = JSON.parseObject(diaryId);
//
//        long diary_id = Long.parseLong(jsonObject.getString("diaryId"));
//
//        Diary diary = diaryService.getById(diary_id);
//
//        if (diary == null) {
//            throw new RuntimeException("???????????????????????????????????????");
//        }
//
//        ProductionStar productionStar = productionStarService.getOneDiaryStar(userservice.getId(), diary.getId(), "????????????");
//
//        if (productionStar == null) {
//            throw new RuntimeException("?????????????????????????????????");
//        }
//
//        boolean flag = productionStarService.removeById(productionStar.getId());
//
//        if (flag) {
//            return "??????????????????";
//        }else {
//            throw new RuntimeException("??????????????????????????????");
//        }
//    }
//
//    @UserLoginToken
//    @PostMapping("/saveDiaryCollection")
//    /* ?????????????????? */
//    public String saveDiaryCollection(@RequestBody String diaryId , HttpServletRequest httpServletRequest) {
//        String authorization = httpServletRequest.getHeader("Authorization");
//        User userservice = tokenUtils.parseTokenAndGetUser(authorization);
//        if (userservice == null) {
//            throw new RuntimeException("??????????????????????????????????????????????????????");
//        }
//        JSONObject jsonObject = JSON.parseObject(diaryId);
//
//        long diary_id = Long.parseLong(jsonObject.getString("diaryId"));
//
//        Diary diary = diaryService.getById(diary_id);
////        System.out.println(diarycontroller);
//        if (diary == null) {
//            throw new RuntimeException("?????????????????????????????????");
//        }
////        System.out.println(diarycontroller);
////        ???????????????????????????
//
//        ProductionCollection productionCollection = productionCollectionService.getOneHasDelDiaryCollection(userservice.getId(), diary.getId() , "????????????");
//
//        boolean flag = false;
//
//        if (productionCollection == null) {
//            productionCollection = new ProductionCollection();
//            productionCollection.setUserId(userservice.getId());
//            productionCollection.setObjId(diary.getId());
//            productionCollection.setType("????????????");
//
//            flag = productionCollectionService.save(productionCollection);
//        }else {
//            flag = productionCollectionService.reCollectDiary(productionCollection.getId());
//        }
//
//
//        if (flag) {
//            return "????????????";
//        }else {
//            throw new RuntimeException("????????????????????????");
//        }
//    }
//
//    /*??????????????????*/
//    @UserLoginToken
//    @PostMapping("/cancelDiaryCollection")
//    public String cancelDiaryCollection(@RequestBody String diaryId , HttpServletRequest httpServletRequest) {
//        String authorization = httpServletRequest.getHeader("Authorization");
//        User userservice = tokenUtils.parseTokenAndGetUser(authorization);
//
//
//        if (userservice == null) {
//            throw new RuntimeException("???????????????????????????????????????????????????????????????");
//        }
//
//        JSONObject jsonObject = JSON.parseObject(diaryId);
//
//        long diary_id = Long.parseLong(jsonObject.getString("diaryId"));
//
//        Diary diary = diaryService.getById(diary_id);
//
//        if (diary == null) {
//            throw new RuntimeException("???????????????????????????????????????");
//        }
//
//        ProductionCollection productionCollection = productionCollectionService.getOneDiaryCollection(userservice.getId(), diary.getId(), "????????????");
//        if (productionCollection == null) {
//            throw new RuntimeException("?????????????????????????????????");
//        }
//
//        boolean flag = productionCollectionService.removeById(productionCollection.getId());
//
//        if (flag) {
//            return "??????????????????";
//        }else {
//            throw new RuntimeException("??????????????????????????????");
//        }
//    }
//
//
//
//    /*???????????????????????????*/
//    @GetMapping("/hasAlreadLike")
//    @UserLoginToken
//    public String hasAlreadLike(@RequestParam(name = "diaryId")String diaryId , HttpServletRequest httpServletRequest){
//        String authorization = httpServletRequest.getHeader("Authorization");
//        User userservice = tokenUtils.parseTokenAndGetUser(authorization);
//
//        if (userservice == null) {
//            throw new RuntimeException("???????????????????????????????????????");
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
//            throw new RuntimeException("??????????????????");
//        }
//
//        ProductionStar productionStar = productionStarService.getOneDiaryStar(userservice.getId(), diary.getId(), "????????????");
//
//        if (productionStar != null) {
////            throw new RuntimeException("????????????");
//            return "like";
//        }else {
//            return "not like";
//        }
//    }
//
//    /*???????????????????????????*/
//    @GetMapping("/hasAlreadCollect")
//    @UserLoginToken
//    public String hasAlreadCollect(@RequestParam(name = "diaryId")String diaryId , HttpServletRequest httpServletRequest){
//        String authorization = httpServletRequest.getHeader("Authorization");
//        User userservice = tokenUtils.parseTokenAndGetUser(authorization);
//
//        if (userservice == null) {
//            throw new RuntimeException("???????????????????????????????????????");
//        }
//        long diary_id = Long.parseLong(diaryId);
//        Diary diary = diaryService.getById(diary_id);
//
//        if (diary == null) {
//            throw new RuntimeException("??????????????????");
//        }
//
//
//        ProductionCollection productionCollection = productionCollectionService.getOneDiaryCollection(userservice.getId(), diary.getId(), "????????????");
//        if (productionCollection != null) {
////            throw new RuntimeException("????????????");
//            return "collect";
//        }else {
//            return "not collect";
//        }
//    }
