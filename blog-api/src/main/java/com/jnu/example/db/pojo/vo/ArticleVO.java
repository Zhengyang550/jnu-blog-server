package com.jnu.example.db.pojo.vo;


import com.jnu.example.db.entity.BlogArticle;
import com.jnu.example.db.entity.BlogTag;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 *  @Author: zy
 *  @Date: 2020/4/19 19:12
 *  @Description: 文章信息
 */
@Data
public class ArticleVO extends BlogArticle {
    @ApiModelProperty("文章标签")
    private List<BlogTag> tags;

    @ApiModelProperty("文章评论")
    private List<CommentVO> comments;
}
