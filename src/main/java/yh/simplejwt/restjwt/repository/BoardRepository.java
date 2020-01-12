package yh.simplejwt.restjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yh.simplejwt.restjwt.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    
}