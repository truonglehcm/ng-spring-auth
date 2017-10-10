package demo.spring.angular.auth.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import demo.spring.angular.auth.persistence.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}