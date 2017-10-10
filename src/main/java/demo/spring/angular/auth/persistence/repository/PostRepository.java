package demo.spring.angular.auth.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.spring.angular.auth.persistence.entity.Post;


public interface PostRepository extends JpaRepository<Post, Long> {
}
