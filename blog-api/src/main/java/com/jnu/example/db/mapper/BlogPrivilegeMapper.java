package com.jnu.example.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jnu.example.db.entity.BlogPrivilege;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author zy
 * @since 2020-04-15
 */
public interface BlogPrivilegeMapper extends BaseMapper<BlogPrivilege> {
    List<HashMap<String, Object>> getPrivilegeListByUserId(Integer userId);
    List<HashMap<String, Object>> getPrivilegeListByPath(String path);
}
