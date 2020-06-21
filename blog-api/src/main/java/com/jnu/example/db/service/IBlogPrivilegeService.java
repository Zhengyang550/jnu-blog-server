package com.jnu.example.db.service;

import com.jnu.example.db.entity.BlogPrivilege;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.HashMap;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author zy
 * @since 2020-04-15
 */
public interface IBlogPrivilegeService extends IService<BlogPrivilege> {
    List<HashMap<String,Object>> getPrivilegeListByUserId(Integer userId);
    List<HashMap<String, Object>> getPrivilegeListByPath(String path);
}
