package com.jnu.example.blog.service;

import com.jnu.example.db.pojo.dto.ReplyAddRequestDTO;
import com.jnu.example.db.pojo.vo.ArticleVO;

/**
 * @Description：文章评论回复接口
 * @Author: zy
 * @Date: 2020/5/4 11:31
 */
public interface IReplyService {
    ArticleVO insertReply(ReplyAddRequestDTO replyAddRequestDTO);
    void deleteReply(Integer replyId);
}
