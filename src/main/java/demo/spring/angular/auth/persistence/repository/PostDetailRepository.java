package demo.spring.angular.auth.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.spring.angular.auth.persistence.entity.PostDetail;


public interface PostDetailRepository extends JpaRepository<PostDetail, Long> {

}
