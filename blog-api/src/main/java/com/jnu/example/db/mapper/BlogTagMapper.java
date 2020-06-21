package com.jnu.example.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jnu.example.db.entity.BlogTag;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB; InnoDB free: 3072 kB Mapper 接口
 * </p>
 *
 * @author zy
 * @since 2020-04-15
 */
public interface BlogTagMapper extends BaseMapper<BlogTag> {
    List<Map<String, Object>> getTagList();
}
