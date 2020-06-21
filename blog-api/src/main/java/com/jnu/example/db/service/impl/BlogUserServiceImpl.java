package com.jnu.example.db.service.impl;

import com.jnu.example.db.entity.BlogUser;
import com.jnu.example.db.mapper.BlogUserMapper;
import com.jnu.example.db.service.IBlogUserService;
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
public class BlogUserServiceImpl extends ServiceImpl<BlogUserMapper, BlogUser> implements IBlogUserService {

}
