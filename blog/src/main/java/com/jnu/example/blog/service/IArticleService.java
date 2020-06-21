package com.jnu.example.blog.service;


import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.jnu.example.db.pojo.dto.ArticleAddRequestDTO;
import com.jnu.example.db.pojo.vo.ArticleVO;
import com.jnu.example.core.pojo.PageData;
import com.jnu.example.db.entity.BlogArticle;


/**
 *  @Author: zy
 *  @Date: 2020/4/19 19:10
 *  @Description: 文章接口
 */
public interface IArticleService {
    BlogArticle insertArticle(ArticleAddRequestDTO articleAddRequestDTO);
    ArticleVO getArticle(Integer artcleId);
    PageData<BlogArticle> getAriticleListByTagName(Long current, Long pageSize,String tagName, Boolean all);
    PageData<ArticleVO> getArticleList(Long current, Long pageSize, Boolean all,String search, SFunction<BlogArticle, ?> column);
}
