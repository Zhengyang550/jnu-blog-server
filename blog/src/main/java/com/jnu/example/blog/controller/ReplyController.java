package com.jnu.example.blog.controller;

import com.jnu.example.db.pojo.dto.ReplyAddRequestDTO;
import com.jnu.example.db.pojo.vo.ArticleVO;
import com.jnu.example.blog.service.IReplyService;
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
 *  @Description: 文章评论回复控制器
 */
@Api(tags = "文章评论回复接口")
@RestController
@RequestMapping("/reply")
@Validated
public class ReplyController {
    @Autowired
    private IReplyService replyService;

    @ApiOperation(value = "新增文章评论回复")
    @PostMapping("/add")
    public CustomizedResponseEntity<ArticleVO> getArticle(@ApiParam(value = "文章评论恢复",required = true) @RequestBody @Valid ReplyAddRequestDTO replyAddRequestDTO){
        return CustomizedResponseEntity.success(replyService.insertReply(replyAddRequestDTO));
    }

    @ApiOperation(value = "删除文章评论下的回复")
    @GetMapping("/delete")
    public CustomizedResponseEntity<String> login(@ApiParam(value = "评论回复id",required = true) @RequestParam("replyId") @NotNull(message="评论id不能为空") Integer replyId){
        replyService.deleteReply(replyId);
        return CustomizedResponseEntity.success();
    }
}
