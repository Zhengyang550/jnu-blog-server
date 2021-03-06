package com.jnu.example.db.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * InnoDB free: 3072 kB; InnoDB free: 3072 kB
 * </p>
 *
 * @author zy
 * @since 2020-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_blog_article")
@ApiModel(value="BlogArticle对象", description="InnoDB free: 3072 kB; InnoDB free: 3072 kB")
public class BlogArticle implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private String content;

    @TableField("viewCount")
    private Integer viewCount;

    @TableField(value = "createdAt",fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updatedAt",fill = FieldFill.UPDATE)
    private LocalDateTime updatedAt;


}
