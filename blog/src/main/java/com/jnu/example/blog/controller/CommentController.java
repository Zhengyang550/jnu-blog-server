package com.jnu.example.blog.controller;

import com.jnu.example.db.pojo.dto.CommentAddRequestDTO;
import com.jnu.example.db.pojo.vo.ArticleVO;
import com.jnu.example.blog.service.ICommentService;
import com.jnu.example.core.pojo.CustomizedResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *  @Author: zy
 *  @Date: 2020/5/4 20:29
 *  @Description: 文章评论控制器
 */
@Api(tags = "文章评论接口")
@RestController
@RequestMapping("/blog/api//comment")
@Validated
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @ApiOperation(value = "新增文章评论")
    @PostMapping("/add")
    public CustomizedResponseEntity<ArticleVO> getArticle(@ApiParam(value = "文章评论",required = true) @RequestBody @Valid CommentAddRequestDTO commentAddRequestDTO){
        return CustomizedResponseEntity.success(commentService.insertComment(commentAddRequestDTO));
    }

    @ApiOperation(value = "删除文章评论")
    @GetMapping("/delete")
    public CustomizedResponseEntity<String> login(@ApiParam(value = "评论id",required = true) @RequestParam("commentId") @NotNull(message="评论id不能为空") Integer commentId){
        commentService.deleteComment(commentId);
        return CustomizedResponseEntity.success();
    }
}
