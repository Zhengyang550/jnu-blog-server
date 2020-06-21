package com.jnu.example.db.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jnu.example.db.entity.BlogArticle;
import com.jnu.example.db.mapper.BlogArticleMapper;
import com.jnu.example.db.service.IBlogArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * <p>
 * InnoDB free: 3072 kB; InnoDB free: 3072 kB 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-04-15
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements IBlogArticleService {
    @Override
    public IPage<BlogArticle> getArticleListByTagName(Page<?> page, String tagName){
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题
        page.setOptimizeCountSql(false);
        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个
        return getBaseMapper().getArticleListByTagName(page,tagName);
    }
}
