package yh.simplejwt.restjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yh.simplejwt.restjwt.entity.Board;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    public List<Board> findByTitleIgnoreCaseLike(String title);
    public List<Board> findByContentIgnoreCaseLike(String content);
    public List<Board> findByUserIdIgnoreCaseLike(String id);
}