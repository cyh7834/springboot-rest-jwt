package yh.simplejwt.restjwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yh.simplejwt.restjwt.advice.exception.CustomNotOwnerException;
import yh.simplejwt.restjwt.advice.exception.CustomResourceNotExistException;
import yh.simplejwt.restjwt.advice.exception.CustomUserNotFoundException;
import yh.simplejwt.restjwt.dto.ReplyDTO;
import yh.simplejwt.restjwt.entity.Board;
import yh.simplejwt.restjwt.entity.Reply;
import yh.simplejwt.restjwt.entity.User;
import yh.simplejwt.restjwt.repository.BoardRepository;
import yh.simplejwt.restjwt.repository.ReplyRepository;
import yh.simplejwt.restjwt.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;

    //댓글을 작성합니다. 게시글이나 유저의 정보가 잘못됐을시 예외처리.
    public Reply writeReply(ReplyDTO replyDTO, String userId) {
        Board board = boardRepository.findById(replyDTO.getBoardNum()).orElseThrow(CustomResourceNotExistException::new);
        User user = userRepository.findById(userId).orElseThrow(CustomUserNotFoundException::new);

        Reply reply = Reply.builder()
                .user(user)
                .board(board)
                .content(replyDTO.getContent())
                .build();

        return replyRepository.save(reply);
    }

    //댓글을 수정합니다. 댓글이 없거나 댓글을 작성한 유저가 아닐시 예외처리.
    public Reply updateReply(String userId, ReplyDTO replyDTO, long replyNum) {
        Reply reply = replyRepository.findById(replyNum).orElseThrow(CustomResourceNotExistException::new);
        User user = reply.getUser();
        if (!userId.equals(user.getUsername()))
            throw new CustomNotOwnerException();

        reply.setContent(replyDTO.getContent());

        return replyRepository.save(reply);
    }

    //댓글을 삭제합니다. 댓글이 없거나 댓글을 작성한 유저가 아닐시 예외처리.
    public boolean deleteReply(long replyNum, String userId) {
        Reply reply = replyRepository.findById(replyNum).orElseThrow(CustomResourceNotExistException::new);
        User user = reply.getUser();
        if (!userId.equals(user.getUsername()))
            throw new CustomNotOwnerException();

        replyRepository.delete(reply);

        return true;
    }
}
