package com.jnu.example.db.service;

import com.jnu.example.db.entity.BlogTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB; InnoDB free: 3072 kB 服务类
 * </p>
 *
 * @author zy
 * @since 2020-04-15
 */
public interface IBlogTagService extends IService<BlogTag> {
    List<Map<String, Object>> getTagList();
}
