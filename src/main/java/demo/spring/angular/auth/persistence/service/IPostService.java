package demo.spring.angular.auth.persistence.service;

import java.util.List;

import demo.spring.angular.auth.persistence.entity.Post;
import demo.spring.angular.auth.web.exception.ServiceException;
import demo.spring.angular.auth.web.request.PostRequest;
import demo.spring.angular.auth.web.response.PostResponse;

public interface IPostService extends IGenericService<Post, Long> {
	PostResponse getPostDetail(Long postId) throws ServiceException;
    Long createPost(PostRequest post) throws ServiceException;
    void updatePost(Long id, PostRequest post) throws ServiceException;
    void deletePost(Long id) throws ServiceException;
    List<PostResponse> findAllPost(boolean isManage) throws ServiceException;
}
