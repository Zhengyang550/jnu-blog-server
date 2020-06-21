package com.jnu.example.blog.service;

import com.jnu.example.db.pojo.vo.TagVO;

import java.util.List;

/**
 * Author: zy
 * Description: 标签接口
 * Date: 2020/4/22
 */
public interface ITagService {
    List<TagVO> getTagList();
}
