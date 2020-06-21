package com.jnu.example.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.jnu.example.db.pojo.vo.TagVO;
import com.jnu.example.blog.service.ITagService;
import com.jnu.example.db.service.IBlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: zy
 * Description: 标签业务逻辑
 * Date: 2020/4/22
 */
@Service
public class TagServiceImpl implements ITagService {
    @Autowired
    private IBlogTagService blogTagService;

    /**
     * @author: zy
     * @description: 获取标签列表
     * @date: 2020/4/22 17:10
     * @return List<TagVO>:
     */
    @Override
    public List<TagVO> getTagList() {
        //创建返回实体
        List<TagVO> tags = new ArrayList<>();

        //获取tag列表
        List<Map<String,Object>> tagMaps = blogTagService.getTagList();
        if(CollUtil.isEmpty(tagMaps)){
            return tags;
        }

        //map转为bean
        for(Map<String,Object> tagMap:tagMaps){
            tags.add(BeanUtil.mapToBean(tagMap,TagVO.class,false));
        }

        return tags;
    }
}
