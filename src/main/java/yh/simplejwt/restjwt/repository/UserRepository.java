package yh.simplejwt.restjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yh.simplejwt.restjwt.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}