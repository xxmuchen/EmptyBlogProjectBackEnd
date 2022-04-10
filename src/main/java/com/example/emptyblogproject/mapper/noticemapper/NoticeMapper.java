package com.example.emptyblogproject.mapper.noticemapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.emptyblogproject.bean.notice.Notice;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/4/10
 * Time: 8:29
 * Description:
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
}
