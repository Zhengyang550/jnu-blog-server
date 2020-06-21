package com.jnu.example.db.pojo.vo;


import com.jnu.example.db.entity.BlogComment;
import com.jnu.example.db.entity.BlogUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 *  @Author: zy
 *  @Date: 2020/4/25 19:57
 *  @Description: 评论信息
 */
@Data
public class CommentVO extends BlogComment {
    @ApiModelProperty("评论回复")
    List<ReplyVO> replies;

    @ApiModelProperty("评论人")
    BlogUser user;
}
