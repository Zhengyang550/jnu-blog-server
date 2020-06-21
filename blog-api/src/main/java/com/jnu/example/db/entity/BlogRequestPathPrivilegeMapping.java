package com.jnu.example.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 路径权限关系表
 * </p>
 *
 * @author zy
 * @since 2020-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_blog_request_path_privilege_mapping")
@ApiModel(value="BlogRequestPathPrivilegeMapping对象", description="路径权限关系表")
public class BlogRequestPathPrivilegeMapping implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "请求路径id")
    @TableField("urlId")
    private Integer urlId;

    @ApiModelProperty(value = "权限id")
    @TableField("privilegeId")
    private Integer privilegeId;


}
