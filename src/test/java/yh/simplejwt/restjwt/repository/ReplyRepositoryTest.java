package yh.simplejwt.restjwt.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import yh.simplejwt.restjwt.RestJwtApplicationTests;
import yh.simplejwt.restjwt.entity.Board;
import yh.simplejwt.restjwt.entity.Reply;
import yh.simplejwt.restjwt.entity.User;

import java.util.Optional;

public class ReplyRepositoryTest extends RestJwtApplicationTests {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void create() {
        Optional<User> user = userRepository.findById(1L);
        Optional<Board> board = boardRepository.findById(1L);


        user.ifPresent(selectedUser -> {
            board.ifPresent(selectedBoard -> {
                Reply reply = new Reply();
                reply.setContent("test reply");
                reply.setBoard(selectedBoard);
                reply.setUser(selectedUser);

                Reply newReply = replyRepository.save(reply);
                Optional<Reply> selectedReply = replyRepository.findById(3L);
                System.out.println(selectedReply.get().getUser().getEmail()+ " " +selectedReply.get().getBoard().getTitle());
            });
        });
        //Assert.assertTrue(reply.isPresent());

    }

    @Test
    public void read() {
        Optional<Reply> reply = replyRepository.findById(1L);
        reply.ifPresent(selectedReply -> {
            System.out.println(selectedReply.getContent());
            System.out.println(selectedReply.getBoard().getTitle());
            System.out.println(selectedReply.getUser().getNickname());
        });
        Assert.assertTrue(reply.isPresent());

    }
}
