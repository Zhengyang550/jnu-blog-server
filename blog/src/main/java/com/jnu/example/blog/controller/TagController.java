package com.jnu.example.blog.controller;

import com.jnu.example.db.pojo.vo.TagVO;
import com.jnu.example.blog.service.ITagService;
import com.jnu.example.core.pojo.CustomizedResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: zy
 * Description: 标签控制器
 * Date: 2020/4/22
 */
@Api(tags="标签接口")
@RestController
@RequestMapping("/blog/api//tag")
@Validated
public class TagController {
    @Autowired
    private ITagService tagService;

    @ApiOperation(value = "获取所有标签信息")
    @GetMapping("/list")
    public CustomizedResponseEntity<List<TagVO>> getTagList(){
        return CustomizedResponseEntity.success(tagService.getTagList());
    }
}
