package demo.spring.angular.auth.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.spring.angular.auth.persistence.entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	PasswordResetToken findByToken(String token);
}
