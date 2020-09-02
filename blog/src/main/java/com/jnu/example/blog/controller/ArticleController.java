package com.jnu.example.blog.controller;

import com.jnu.example.db.pojo.dto.ArticleAddRequestDTO;
import com.jnu.example.db.pojo.dto.ArticleUpdateRequestDTO;
import com.jnu.example.db.pojo.vo.ArticleVO;
import com.jnu.example.blog.service.IArticleService;
import com.jnu.example.core.pojo.CustomizedPageResponseEntity;
import com.jnu.example.core.pojo.CustomizedResponseEntity;
import com.jnu.example.db.entity.BlogArticle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;


/**
 *  @Author: zy
 *  @Date: 2020/4/19 20:29
 *  @Description: 文章控制器
 */
@Api(tags = "文章接口")
@RestController
@RequestMapping("/blog/api/article")
@Validated
public class ArticleController {
    @Autowired
    private IArticleService articleService;

    @ApiOperation(value = "新增文章信息")
    @PostMapping("/add")
    public CustomizedResponseEntity<BlogArticle> insertArticle(@ApiParam(value = "文章信息",required = true) @Valid @RequestBody ArticleAddRequestDTO addRequestDTO){
        return CustomizedResponseEntity.success(articleService.insertArticle(addRequestDTO));
    }

    @ApiOperation(value = "根据文章id删除文章")
    @GetMapping("/delete")
    public CustomizedResponseEntity<Boolean> deleteUser(@ApiParam(value = "文章id",required = true) @Positive(message = "文章id必须是正整数") @RequestParam(value = "articleId") Integer articleId){
        return CustomizedResponseEntity.success(articleService.deleteArticle(articleId));
    }

    @ApiOperation(value = "根据文章id列表删除文章")
    @PostMapping("/delete")
    public CustomizedResponseEntity<Boolean> deleteArticles(@ApiParam(value = "文章id列表",required = true) @Valid @RequestBody List<Integer> articleIds){
        return CustomizedResponseEntity.success(articleService.deleteArticles(articleIds));
    }

    @ApiOperation(value = "更新文章信息")
    @PostMapping("/update")
    public CustomizedResponseEntity<BlogArticle> updateArticle(@ApiParam(value = "文章信息",required = true) @Valid @RequestBody ArticleUpdateRequestDTO updateRequestDTO){
        return CustomizedResponseEntity.success(articleService.updateArticle(updateRequestDTO));
    }

    @ApiOperation(value = "获取文章信息")
    @GetMapping("/{articleId}")
    public CustomizedResponseEntity<ArticleVO> getArticle(@ApiParam(value = "文章id",required = true) @PathVariable("articleId") Integer articleId){
        return CustomizedResponseEntity.success(articleService.getArticle(articleId));
    }


    @ApiOperation(value = "根据标签分页获取文章")
    @GetMapping("/listByTagName")
    public CustomizedPageResponseEntity<BlogArticle> getAriticleListByTagName(@ApiParam(value = "当前页") @Positive(message = "current必须是正整数") @RequestParam(value = "current",defaultValue = "1") Long current,
                                                                              @ApiParam(value = "页大小") @Positive(message = "pageSize必须是正整数") @RequestParam(value = "pageSize",defaultValue = "10") Long pageSize,
                                                                              @ApiParam(value = "标签名") @RequestParam(value = "tag",required = false) String tag,
                                                                              @ApiParam(value = "查询全部",required = true) @RequestParam(value = "all",defaultValue = "true") Boolean all){
        return CustomizedPageResponseEntity.success(articleService.getAriticleListByTagName(current,pageSize,tag,all));
    }

    @ApiOperation(value = "分页获取文章")
    @GetMapping("/list")
    public CustomizedPageResponseEntity<ArticleVO> getArticleList(@ApiParam(value = "当前页") @Positive(message = "current必须是正整数") @RequestParam(value = "current",defaultValue = "1") Long current,
                                                                  @ApiParam(value = "页大小") @Positive(message = "pageSize必须是正整数") @RequestParam(value = "pageSize",defaultValue = "10") Long pageSize,
                                                                  @ApiParam(value = "查询全部",required = true) @RequestParam(value = "all",defaultValue = "true") Boolean all,
                                                                  @ApiParam(value = "查询内容") @RequestParam(value = "search",required = false) String search){
        return CustomizedPageResponseEntity.success(articleService.getArticleList(current,pageSize,all,search, BlogArticle::getId));
    }

    @ApiOperation(value = "根据热度分页获取文章")
    @GetMapping("/listByViewCountDesc")
    public CustomizedPageResponseEntity<ArticleVO> getArticleListByViewCountDesc(@ApiParam(value = "当前页") @Positive(message = "current必须是正整数") @RequestParam(value = "current",defaultValue = "1") Long current,
                                                                                 @ApiParam(value = "页大小") @Positive(message = "pageSize必须是正整数") @RequestParam(value = "pageSize",defaultValue = "10") Long pageSize,
                                                                                 @ApiParam(value = "查询全部",required = true) @RequestParam(value = "all",defaultValue = "true") Boolean all,
                                                                                 @ApiParam(value = "查询内容") @RequestParam(value = "search",required = false) String search){
        return CustomizedPageResponseEntity.success(articleService.getArticleList(current,pageSize,all,search, BlogArticle::getViewCount));
    }
}
