package com.jnu.example.db.pojo.vo;

import com.jnu.example.db.entity.BlogReply;
import com.jnu.example.db.entity.BlogUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author: zy
 * Description: 回复人信息
 * Date: 2020/4/30
 */
@Data
public class ReplyVO extends BlogReply {
    @ApiModelProperty("回复人")
    BlogUser user;
}
