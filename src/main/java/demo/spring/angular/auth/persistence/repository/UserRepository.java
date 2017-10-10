package demo.spring.angular.auth.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.spring.angular.auth.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);
	User findByEmail(String email);
}
