package com.jnu.example.blog.service;


import com.jnu.example.db.pojo.dto.CommentAddRequestDTO;
import com.jnu.example.db.pojo.vo.ArticleVO;

/**
* @Description：文章评论接口
* @Author: zy
* @Date: 2020/5/4 11:31
*/
public interface ICommentService {
    ArticleVO insertComment(CommentAddRequestDTO commentAddRequestDTO);
    void deleteComment(Integer commentId);
}
