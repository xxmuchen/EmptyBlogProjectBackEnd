package com.example.emptyblogproject.bean.observe.observeBo;

import com.example.emptyblogproject.bean.observe.Observe;
import com.example.emptyblogproject.bean.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/23
 * Time: 2:54
 * Description: 评论的业务对象
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ObserveNodeBO extends Observe {
    /**
     * 评论的用户信息
     */
    private User user;

    /**
     * 下一条回复
     */
    private List<ObserveNodeBO> nextNodes = new ArrayList<>();

    public ObserveNodeBO (ObserveNodeBO observeNodeBo ) {
        super();
        setId(observeNodeBo.getId());
        setType(observeNodeBo.getType());
        setObjId(observeNodeBo.getObjId());
        setObserverId(observeNodeBo.getObserverId());
        setObserveContent(observeNodeBo.getObserveContent());
        setLastId(observeNodeBo.getLastId());
        setCreateTime(observeNodeBo.getCreateTime());
        setUpdateTime(observeNodeBo.getUpdateTime());
        this.user = observeNodeBo.getUser();
    }
}
