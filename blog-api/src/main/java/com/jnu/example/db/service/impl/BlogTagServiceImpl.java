package com.jnu.example.db.service.impl;

import com.jnu.example.db.entity.BlogTag;
import com.jnu.example.db.mapper.BlogTagMapper;
import com.jnu.example.db.service.IBlogTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB; InnoDB free: 3072 kB 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-04-15
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements IBlogTagService {

    @Override
    public List<Map<String, Object>> getTagList() {
        return baseMapper.getTagList();
    }
}
