package com.jnu.example.core.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.session.RowBounds;

/**
 *  @Author: zy
 *  @Date: 2020/4/14 23:35
 *  @Description: 分页查询工具类
 */
public class JnuMybatisPlusPageUtil {

    private JnuMybatisPlusPageUtil() {}

    /**
     * 根据all参数动态生成分页参数
     * @param pageNum
     * @param pageSize
     * @param all
     * @param <T>
     * @return
     */
    public static <T> Page<T> getPage(Long pageNum, Long pageSize, Boolean all) {
        return Boolean.TRUE.equals(all) ? new Page<>(1, RowBounds.NO_ROW_LIMIT) : new Page<>(pageNum, pageSize);
    }
}
