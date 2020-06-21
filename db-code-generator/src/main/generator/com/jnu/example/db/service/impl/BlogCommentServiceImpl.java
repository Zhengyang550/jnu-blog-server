package com.jnu.example.db.service.impl;

import com.jnu.example.db.entity.BlogComment;
import com.jnu.example.db.mapper.BlogCommentMapper;
import com.jnu.example.db.service.IBlogCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * InnoDB free: 3072 kB 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-04-18
 */
@Service
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements IBlogCommentService {

}
