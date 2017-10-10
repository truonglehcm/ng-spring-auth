package demo.spring.angular.auth.persistence.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.spring.angular.auth.persistence.entity.Post;
import demo.spring.angular.auth.persistence.entity.PostDetail;
import demo.spring.angular.auth.persistence.entity.User;
import demo.spring.angular.auth.persistence.repository.PostDetailRepository;
import demo.spring.angular.auth.persistence.repository.PostRepository;
import demo.spring.angular.auth.persistence.repository.UserRepository;
import demo.spring.angular.auth.persistence.service.IPostService;
import demo.spring.angular.auth.utils.AuthoritiesConstants;
import demo.spring.angular.auth.utils.SecurityUtils;
import demo.spring.angular.auth.web.exception.ServiceException;
import demo.spring.angular.auth.web.request.PostRequest;
import demo.spring.angular.auth.web.response.PostResponse;
import javassist.NotFoundException;

@Service
@Transactional
public class PostService extends GenericService<Post, Long> implements IPostService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PostDetailRepository postDetailRepository;
	
	@Override
	public PostResponse getPostDetail(Long postId) throws ServiceException {
		try {
			PostDetail postDetail = entityManager.find(PostDetail.class, postId);
			if(Objects.isNull(postDetail)) {
				throw new NotFoundException("Post is not found!!!");
			}
			
			Post post = postDetail.getPost();
			PostResponse postResponse = mapper.map(post, PostResponse.class);
			postResponse.setContent(postDetail.getContent());
			postResponse.setCreateBy(post.getUser().getFullName());
			return postResponse;
		} catch (Exception e) {
			throw new ServiceException("PostService --> getPostDetail()", e);
		}
	}
	
	@Override
	public Long createPost(PostRequest postReq) throws ServiceException {
		try {
			String userName = SecurityUtils.getCurrentUserLogin();
	        User user = userRepository.findByUserName(userName);
	        
	        Post post = mapper.map(postReq, Post.class);
	        post.setCreateAt(new Date());
	        post.setUser(user);
	        post.setVisible(false);
	        post.setVisited(0);
	        
	        PostDetail postDetail = new PostDetail();
	        postDetail.setContent(postReq.getContent());
	        postDetail.setPost(post);
	        postDetailRepository.save(postDetail);
	        
	        return post.getId();
		} catch (Exception e) {
			throw new ServiceException("PostService --> createPost()", e);
		}
	}

	@Override
	public void updatePost(Long id, PostRequest post) throws ServiceException {
		try {
			PostDetail oldPostDetail = postDetailRepository.findOne(id);
	    	if (Objects.isNull(oldPostDetail)) {
	    		throw new NotFoundException("Post is not found!!!");
	    	}
	    	
	    	oldPostDetail.setContent(post.getContent());
	    	
	    	Post oldPost = oldPostDetail.getPost();
	    	oldPost.setTitle(post.getTitle());
	    	oldPost.setDescription(post.getDescription());
	    	oldPost.setLogoUrl(post.getLogoUrl());
	    	postDetailRepository.save(oldPostDetail);
	    	
		} catch (Exception e) {
			throw new ServiceException("PostService --> updatePost()", e);
		}
	}

	@Override
	public void deletePost(Long id) throws ServiceException {
		try {
			if(!SecurityUtils.isAuthenticated()) {
				throw new AccessDeniedException("Access deniel");
	    	} else if(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
	    		postRepository.delete(id);
	    	} else {
	    		Post post = postRepository.findOne(id);
	    		if (Objects.isNull(post) || !post.getUser().getUserName().equals(SecurityUtils.getCurrentUserLogin())) {
	    			throw new AccessDeniedException("Access deniel");
	    		}
	    		postRepository.delete(id);
	    	}
		} catch (Exception e) {
			throw new ServiceException("PostService --> deletePost()", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostResponse> findAllPost(boolean isManage) throws ServiceException {
		try {
			
			Session currentSession = (Session) entityManager.getDelegate();
			
			StringBuilder sb = new StringBuilder();
			sb.append("Select p.id, p.title, p.description, ");
			sb.append("p.logo_url logoUrl, p.create_at createAt, ");
			sb.append("u.username createBy, p.visible visible, p.visited ");
			sb.append("From Post p ");
			sb.append("Left Join User u ");
			sb.append("On p.create_by = u.id ");
			
			// if is user get post belong to this user
			if(isManage && !SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
				sb.append("Where u.username = :username");
			}
			
			Query q = currentSession.createSQLQuery(sb.toString())
			.addScalar("id", StandardBasicTypes.LONG)
			.addScalar("title", StandardBasicTypes.STRING)
			.addScalar("description", StandardBasicTypes.STRING)
			.addScalar("logoUrl", StandardBasicTypes.STRING)
			.addScalar("createAt", StandardBasicTypes.DATE)
			.addScalar("createBy", StandardBasicTypes.STRING)
			.addScalar("visible", StandardBasicTypes.BOOLEAN)
			.addScalar("visited", StandardBasicTypes.STRING)
			.setResultTransformer(Transformers.aliasToBean(PostResponse.class));
			
			if(isManage && !SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
				String userName = SecurityUtils.getCurrentUserLogin();
				q.setParameter("username", userName);
			}
			
			return q.list();
		} catch (Exception e) {
			throw new ServiceException("PostService --> findAllPost()", e);
		}
	}
}
