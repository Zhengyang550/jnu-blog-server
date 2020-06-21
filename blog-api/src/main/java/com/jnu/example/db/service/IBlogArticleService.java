package com.jnu.example.db.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jnu.example.db.entity.BlogArticle;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 * InnoDB free: 3072 kB; InnoDB free: 3072 kB 服务类
 * </p>
 *
 * @author zy
 * @since 2020-04-15
 */
public interface IBlogArticleService extends IService<BlogArticle> {
    IPage<BlogArticle> getArticleListByTagName(Page<?> page, String tagName);
}
