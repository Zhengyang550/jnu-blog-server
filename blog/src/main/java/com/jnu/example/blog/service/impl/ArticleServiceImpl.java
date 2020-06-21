package com.jnu.example.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jnu.example.db.pojo.dto.ArticleAddRequestDTO;
import com.jnu.example.db.pojo.vo.ArticleVO;
import com.jnu.example.db.pojo.vo.CommentVO;
import com.jnu.example.db.pojo.vo.ReplyVO;
import com.jnu.example.blog.service.IArticleService;
import com.jnu.example.core.pojo.PageData;
import com.jnu.example.core.util.JnuMybatisPlusPageUtil;
import com.jnu.example.db.entity.*;
import com.jnu.example.db.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 *  @Author: zy
 *  @Date: 2020/4/19 19:26
 *  @Description:文章业务逻辑
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private IBlogArticleService blogArticleService;

    @Autowired
    private IBlogTagService blogTagService;

    @Autowired
    private IBlogCommentService blogCommentService;

    @Autowired
    private IBlogReplyService blogReplyService;

    @Autowired
    private IBlogUserService blogUserService;

    /**
     * @Description: 新增文章以及文章标签
     * @Author: zy
     * @Date: 2020/5/17 19:03
     * @param articleAddRequestDTO
     * @Return BlogArticle:
     * @Exception :
     */
    @Override
    public BlogArticle insertArticle(ArticleAddRequestDTO articleAddRequestDTO) {
        //创建实体
        BlogArticle article = new BlogArticle();
        BeanUtil.copyProperties(articleAddRequestDTO,article);
        article.setViewCount(0);

        //插入
        blogArticleService.save(article);

        //插入文章标签
        if(CollUtil.isNotEmpty(articleAddRequestDTO.getTags())){
            List<BlogTag> tags = new ArrayList<>();
            for(String tagName:articleAddRequestDTO.getTags()){
                BlogTag tag = new BlogTag();
                tag.setArticleId(article.getId());
                tag.setName(tagName);
                tags.add(tag);
            }
            blogTagService.saveBatch(tags);
        }

        return article;
    }

    /**
     * @author: zy
     * @description:获取文章信息
     * @date: 2020/4/30 16:43
     * @param artcleId:文章id
     * @return ArticleVO:
     */
    @Override
    public ArticleVO getArticle(Integer artcleId) {
        //创建返回实例
        ArticleVO articleVO = new ArticleVO();

        //获取文章信息
        BlogArticle article = blogArticleService.getById(artcleId);
        if(article == null){
            return articleVO;
        }
        BeanUtils.copyProperties(article,articleVO);

        //获取文章对应的标签
        List<BlogTag> tags = blogTagService.list(Wrappers.<BlogTag>lambdaQuery().eq(BlogTag::getArticleId,artcleId));
        articleVO.setTags(CollUtil.emptyIfNull(tags));
        articleVO.setComments(new ArrayList<>());

        //获取文章对应的评论 评论者id  评论id  评论id -> 回复列表
        HashSet<Integer> userIds = new HashSet<>();
        HashSet<Integer> commentIds = new HashSet<>();
        HashMap<Integer,List<ReplyVO>> commentIdRepliesMap = new HashMap<>();
        List<BlogComment> comments = blogCommentService.list(Wrappers.<BlogComment>lambdaQuery()
                .eq(BlogComment::getArticleId,artcleId)
                .orderByDesc(BlogComment::getId));
        if(CollUtil.isNotEmpty(comments)){
            //遍历每一个评论
            for(BlogComment comment:comments){
                userIds.add(comment.getUserId());
                commentIds.add(comment.getId());
                CommentVO commentVO = new CommentVO();
                BeanUtils.copyProperties(comment,commentVO);
                articleVO.getComments().add(commentVO);
                commentIdRepliesMap.computeIfAbsent(comment.getId(), k -> new ArrayList<>());
            }
        }

        //获取每一个评论的回复列表
        if(CollUtil.isNotEmpty(commentIds)) {
            List<BlogReply> replies = blogReplyService.list(Wrappers.<BlogReply>lambdaQuery()
                    .in(BlogReply::getCommentId, commentIds));
            for (BlogReply reply : replies) {
                ReplyVO replyVO = new ReplyVO();
                BeanUtil.copyProperties(reply,replyVO);
                commentIdRepliesMap.get(reply.getCommentId()).add(replyVO);
                userIds.add(reply.getUserId());
            }
        }

        //获取每一个评论/回复者
        HashMap<Integer,BlogUser> userIdUserMap = new HashMap<>();
        if(CollUtil.isNotEmpty(userIds)){
            List<BlogUser> users = blogUserService.list(Wrappers.<BlogUser>lambdaQuery()
                    .in(BlogUser::getId,userIds));
            for(BlogUser user:users){
                userIdUserMap.put(user.getId(),user);
            }
        }

        //给评论/回复添加用户信息
        if(CollUtil.isNotEmpty(articleVO.getComments())){
            for(CommentVO commentVO : articleVO.getComments()) {
                commentVO.setUser(userIdUserMap.get(commentVO.getUserId()));
                commentVO.setReplies(CollUtil.emptyIfNull(commentIdRepliesMap.get(commentVO.getId())));
                for ( ReplyVO reply : commentIdRepliesMap.get(commentVO.getId())) {
                    reply.setUser(userIdUserMap.get(reply.getUserId()));
                }
            }
        }

        return articleVO;
    }

    /**
     * @Description: 分页查询 获取文章列表
     * @Author: zy
     * @Date: 2020/5/4 17:07
     * @param current: 页码
     * @param pageSize: 每页记录数
     * @param tagName: 所属标签
     * @param all: 查询所有？ 默认查询全部 如果为true，pageNum和pageSize参数无效
     * @Return PageData<ArticleVO>:
     * @Exception :
     */
    public PageData<BlogArticle> getAriticleListByTagName(Long current, Long pageSize,String tagName, Boolean all){
        //生成分页参数
        Page<BlogArticle> page = JnuMybatisPlusPageUtil.getPage(current, pageSize, all);

        //分页查询
        IPage<BlogArticle> articlePage = blogArticleService.getArticleListByTagName(page,tagName);

        //保存返回结果
        PageData<BlogArticle> result = new PageData(articlePage);

        return result;
    }


    /**
     * @author: zy
     * @description: 分页查询  获取文章列表
     * @date: 2020/4/19 20:28
     * @param current: 页码
     * @param pageSize: 每页记录数
     * @param all: 查询所有？ 默认查询全部 如果为true，pageNum和pageSize参数无效
     * @param search: 搜索文本
     * @param column: 指定根据某一列降序排列
     * @return PageData<BlogUser>:
     */
    @Override
    public PageData<ArticleVO> getArticleList(Long current, Long pageSize, Boolean all, String search,SFunction<BlogArticle, ?> column) {
        //条件
        LambdaQueryWrapper<BlogArticle> queryWrapper = Wrappers.<BlogArticle>lambdaQuery();
        if(column != null) {
            queryWrapper.orderByDesc(column);
        }

        //生成分页参数
        Page<BlogArticle> page = JnuMybatisPlusPageUtil.getPage(current, pageSize, all);

        //搜索条件
        if(StrUtil.isNotBlank(search)){
            queryWrapper = queryWrapper.and(i ->i.like(BlogArticle::getTitle,search)
                    .or()
                    .like(BlogArticle::getContent,search));
        }

        //查询
        IPage<BlogArticle> articlePage = blogArticleService.page(page, queryWrapper);

        //保存返回结果
        PageData<ArticleVO> result = new PageData(articlePage);

        //如果为空
        if(CollUtil.isEmpty(articlePage.getRecords())){
            return result;
        }

        //创建实例 保存返回数据
        List<ArticleVO> articleVOs = new ArrayList<>();

        //保存该页文章id 文章id => 标签列表  文章id => 评论列表
        HashSet<Integer> articleIds = new HashSet<>();
        HashMap<Integer,List<BlogTag>> articleIdTagsMap = new HashMap<>();
        HashMap<Integer,List<CommentVO>> articleIdCommentsMap = new HashMap<>();
        for(BlogArticle article:articlePage.getRecords()){
            articleIds.add(article.getId());
            articleIdTagsMap.put(article.getId(),new ArrayList<>());
            articleIdCommentsMap.put(article.getId(),new ArrayList<>());
        }

        //获取文章对应的标签
        List<BlogTag> tags = blogTagService.list(Wrappers.<BlogTag>lambdaQuery().in(BlogTag::getArticleId,articleIds));
        if(CollUtil.isNotEmpty(tags)){
            for(BlogTag tag:tags){
                articleIdTagsMap.get(tag.getArticleId()).add(tag);
            }
        }

        //获取文章对应的评论 评论/回复者id  评论id -> 回复列表
        HashSet<Integer> userIds = new HashSet<>();
        HashSet<Integer> commentIds = new HashSet<>();
        HashMap<Integer,List<ReplyVO>> commentIdRepliesMap = new HashMap<>();
        List<BlogComment> comments = blogCommentService.list(Wrappers.<BlogComment>lambdaQuery().in(BlogComment::getArticleId,articleIds));
        if(CollUtil.isNotEmpty(comments)){
            for(BlogComment comment:comments){
                userIds.add(comment.getUserId());
                commentIds.add(comment.getId());
                CommentVO commentVO = new CommentVO();
                BeanUtils.copyProperties(comment,commentVO);
                articleIdCommentsMap.get(comment.getArticleId()).add(commentVO);
                commentIdRepliesMap.computeIfAbsent(comment.getId(), k -> new ArrayList<>());
            }
        }

        //获取每一个评论的回复列表
        if(CollUtil.isNotEmpty(commentIds)) {
            List<BlogReply> replies = blogReplyService.list(Wrappers.<BlogReply>lambdaQuery().in(BlogReply::getCommentId, commentIds));
            for (BlogReply reply : replies) {
                ReplyVO replyVO = new ReplyVO();
                BeanUtil.copyProperties(reply,replyVO);
                commentIdRepliesMap.get(reply.getCommentId()).add(replyVO);
                userIds.add(reply.getUserId());
            }
        }

        //获取每一个评论/回复者
        HashMap<Integer,BlogUser> userIdUserMap = new HashMap<>();
        if(CollUtil.isNotEmpty(userIds)){
            List<BlogUser> users = blogUserService.list(Wrappers.<BlogUser>lambdaQuery()
                    .in(BlogUser::getId,userIds));
            for(BlogUser user:users){
                userIdUserMap.put(user.getId(),user);
            }
        }

        //遍历所有文章评论
        for(List<CommentVO> commentVOs:articleIdCommentsMap.values()){
            for(CommentVO commentVO : commentVOs) {
                commentVO.setUser(userIdUserMap.get(commentVO.getUserId()));
                commentVO.setReplies(CollUtil.emptyIfNull(commentIdRepliesMap.get(commentVO.getId())));
                for ( ReplyVO reply : commentIdRepliesMap.get(commentVO.getId())) {
                    reply.setUser(userIdUserMap.get(reply.getUserId()));
                }
            }
        }

        //填充返回实例
        for(BlogArticle article:articlePage.getRecords()){
            ArticleVO articleVO = new ArticleVO();
            BeanUtil.copyProperties(article,articleVO);
            articleVO.setTags(CollUtil.emptyIfNull(articleIdTagsMap.get(article.getId())));
            articleVO.setComments(CollUtil.emptyIfNull(articleIdCommentsMap.get(article.getId())));
            articleVOs.add(articleVO);
        }
        result.setList(articleVOs);
        return result;
    }
}
