package yh.simplejwt.restjwt.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import yh.simplejwt.restjwt.RestJwtApplicationTests;
import yh.simplejwt.restjwt.entity.Board;
import yh.simplejwt.restjwt.entity.User;

import java.util.List;
import java.util.Optional;

public class BoardRepositoryTest extends RestJwtApplicationTests {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired UserRepository userRepository;


    @Test
    public void create() {
        Optional<User> user = userRepository.findById(1L);
        user.ifPresent(selectedUser -> {
            Board board = new Board();
            board.setTitle("test title");
            board.setContent("test content");
            board.setUser(user.get());
            Board newBoard = boardRepository.save(board);
            System.out.println(newBoard.getUser().getNickname());
        });
        //Assert.assertTrue(board.isPresent());

    }

    @Test
    public void read() {
        Optional<Board> board = boardRepository.findById(1L);
        board.ifPresent(selectedBoard -> {
            System.out.println(selectedBoard.getTitle());
            System.out.println(selectedBoard.getUser().getNickname());
        });
        Assert.assertTrue(board.isPresent());

    }

    @Test
    public void findByTitleLike() {
        List<Board> boardList = boardRepository.findByTitleIgnoreCaseLike("%User%");

        boardList.forEach(board -> {
            System.out.println(board.getTitle());
        });
    }

    @Test
    public void findByUserId() {
        List<Board> boardList = boardRepository.findByUserIdIgnoreCaseLike("%id%");

        boardList.forEach(board -> {
            System.out.println(board.getUser().getUserAuth().getAuthority());
        });
    }
}
