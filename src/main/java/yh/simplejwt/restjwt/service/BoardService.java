package yh.simplejwt.restjwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yh.simplejwt.restjwt.advice.exception.CustomNotOwnerException;
import yh.simplejwt.restjwt.advice.exception.CustomResourceNotExistException;
import yh.simplejwt.restjwt.advice.exception.CustomUserNotFoundException;
import yh.simplejwt.restjwt.dto.BoardDTO;
import yh.simplejwt.restjwt.entity.Board;
import yh.simplejwt.restjwt.entity.User;
import yh.simplejwt.restjwt.repository.BoardRepository;
import yh.simplejwt.restjwt.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 게시물을 등록합니다. 회원 조회가 되지 않으면 예외처리.
    public Board writeBoard(String userId, BoardDTO boardDTO) {
        Board board = new Board();
        board.setUser(userRepository.findById(userId).orElseThrow(CustomUserNotFoundException::new));
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());

        return boardRepository.save(board);
    }

    //게시물을 수정합니다. 로그인 정보가 다르면 예외처리.
    public Board updateBoard(long boardNum, String userId, BoardDTO boardDTO) {
        Board board = boardRepository.findById(boardNum).orElseThrow(CustomResourceNotExistException::new);
        User user = board.getUser();
        if (!userId.equals(user.getUsername()))
            throw new CustomNotOwnerException();

        board.setUser(user);
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());

        return boardRepository.save(board);
    }

    //게시물을 삭제합니다. 로그인 정보가 다르면 예외처리.
    public boolean deleteBoard(long boardNum, String userId) {
        Board board = boardRepository.findById(boardNum).orElseThrow(CustomResourceNotExistException::new);
        User user = board.getUser();
        if (!userId.equals(user.getUsername()))
            throw new CustomNotOwnerException();

        boardRepository.delete(board);
        return true;
    }
}
