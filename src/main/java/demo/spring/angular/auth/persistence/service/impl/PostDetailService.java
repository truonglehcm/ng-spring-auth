package demo.spring.angular.auth.persistence.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.spring.angular.auth.persistence.entity.PostDetail;
import demo.spring.angular.auth.persistence.service.IPostDetailService;

@Service
@Transactional
public class PostDetailService extends GenericService<PostDetail, Long> implements IPostDetailService {
}
