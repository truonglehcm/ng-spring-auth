package demo.spring.angular.auth.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.spring.angular.auth.persistence.entity.Tag;


public interface TagRepository extends JpaRepository<Tag, Long> {
}
