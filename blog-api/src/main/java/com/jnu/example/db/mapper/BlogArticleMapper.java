package com.jnu.example.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jnu.example.db.entity.BlogArticle;


/**
 * <p>
 * InnoDB free: 3072 kB; InnoDB free: 3072 kB Mapper 接口
 * </p>
 *
 * @author zy
 * @since 2020-04-15
 */
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {
    /**
     * <p>
     * 查询 : 根据标签名查询文章列表，分页显示
     * </p>
     *
     * @param page: 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
     * @param tagName：标签名
     * @return 分页对象
     */
    IPage<BlogArticle> getArticleListByTagName(Page<?> page, String tagName);
}
