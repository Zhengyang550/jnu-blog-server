package com.jnu.example.db.service.impl;

import com.jnu.example.db.entity.BlogRequestPath;
import com.jnu.example.db.mapper.xml.BlogRequestPathMapper;
import com.jnu.example.db.service.IBlogRequestPathService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 请求路径 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-04-15
 */
@Service
public class BlogRequestPathServiceImpl extends ServiceImpl<BlogRequestPathMapper, BlogRequestPath> implements IBlogRequestPathService {

}
