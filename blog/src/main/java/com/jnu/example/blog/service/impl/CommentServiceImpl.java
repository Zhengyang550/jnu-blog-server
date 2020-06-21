package com.jnu.example.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jnu.example.db.pojo.dto.CommentAddRequestDTO;
import com.jnu.example.db.pojo.vo.ArticleVO;
import com.jnu.example.blog.service.IArticleService;
import com.jnu.example.blog.service.ICommentService;
import com.jnu.example.db.entity.BlogComment;
import com.jnu.example.db.entity.BlogReply;
import com.jnu.example.db.service.IBlogCommentService;
import com.jnu.example.db.service.IBlogReplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
* @Description: 文章评论业务逻辑
* @Author: zy
* @Date: 2020/5/4 11:33
*/
@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private IBlogCommentService blogCommentService;

    @Autowired
    private IBlogReplyService blogReplyService;

    @Autowired
    private IArticleService articleService;

    /**
     * @Description: 新增文章评论 并返回该文章的所有信息
     * @Author: zy
     * @Date: 2020/5/4 11:34
     * @param commentAddRequestDTO : 评论
     * @Return List<CommentVO>:
     * @Exception :
     */
    @Override
    public ArticleVO insertComment(CommentAddRequestDTO commentAddRequestDTO) {
        //创建新增实体
        BlogComment comment = new BlogComment();

        BeanUtils.copyProperties(commentAddRequestDTO,comment);

        //新增
        blogCommentService.save(comment);

        //获取文章所有信息
        return articleService.getArticle(comment.getArticleId());
    }

    /**
     * @Description: 根据评论id 删除评论以及评论下的回复
     * @Author: zy
     * @Date: 2020/5/4 14:39
     * @param void: 评论id
     * @Return Boolean:
     * @Exception :
     */
    @Transactional
    @Override
    public void deleteComment(Integer commentId) {
        //删除评论
        blogCommentService.removeById(commentId);

        //删除评论回复
        blogReplyService.remove(Wrappers.<BlogReply>lambdaQuery().in(BlogReply::getCommentId,commentId));
    }
}
