package com.jnu.example.blog.service.impl;

import com.jnu.example.db.pojo.dto.ReplyAddRequestDTO;
import com.jnu.example.db.pojo.vo.ArticleVO;
import com.jnu.example.blog.service.IArticleService;
import com.jnu.example.blog.service.IReplyService;
import com.jnu.example.db.entity.BlogReply;
import com.jnu.example.db.service.IBlogReplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 文章评论回复业务逻辑
 * @Author: zy
 * @Date: 2020/5/4 11:33
 */
@Service
public class ReplyServiceImpl implements IReplyService {
    @Autowired
    private IBlogReplyService blogReplyService;

    @Autowired
    private IArticleService articleService;

    /**
     * @Description: 新增文章评论的回复  并返回该文章的所有信息
     * @Author: zy
     * @Date: 2020/5/4 13:45
     * @param replyAddRequestDTO: 评论的回复
     * @Return ArticleVO:
     * @Exception :
     */
    @Override
    public ArticleVO insertReply(ReplyAddRequestDTO replyAddRequestDTO) {
        //创建新增实体
        BlogReply reply = new BlogReply();

        BeanUtils.copyProperties(replyAddRequestDTO,reply);

        //新增
        blogReplyService.save(reply);

        //获取文章所有信息
        return articleService.getArticle(reply.getArticleId());
    }

    /**
     * @Description: 根据回复id 删除评论下的回复
     * @Author: zy
     * @Date: 2020/5/4 14:39
     * @param replyId: 评论id
     * @Return void:
     * @Exception :
     */
    @Override
    public void deleteReply(Integer replyId) {
        //删除评论回复
        blogReplyService.removeById(replyId);
    }
}
