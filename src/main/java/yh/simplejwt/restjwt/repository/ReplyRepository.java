package yh.simplejwt.restjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yh.simplejwt.restjwt.entity.Reply;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    public List<Reply> findByContentIgnoreCaseLike(String content);
    public List<Reply> findByBoardBoardNum(long boardNum);
    
}