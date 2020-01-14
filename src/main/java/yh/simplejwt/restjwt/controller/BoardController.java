package yh.simplejwt.restjwt.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import yh.simplejwt.restjwt.dto.BoardDTO;
import yh.simplejwt.restjwt.entity.Board;
import yh.simplejwt.restjwt.network.Header;
import yh.simplejwt.restjwt.network.ListResult;
import yh.simplejwt.restjwt.network.SingleResult;
import yh.simplejwt.restjwt.repository.BoardRepository;
import yh.simplejwt.restjwt.service.BoardService;
import yh.simplejwt.restjwt.service.ResponseService;

import javax.validation.Valid;

@Api(tags = {"3. Board"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/board")
public class BoardController {
    private final ResponseService responseService;
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "게시글 작성", notes = "게시판에 글을 작성")
    @PostMapping(value = "")
    public SingleResult<Board> writeBoard(@Valid @ModelAttribute BoardDTO boardDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        return responseService.getSingleResult(boardService.writeBoard(userId, boardDTO));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "모든 게시글 검색", notes = "게시판의 모든 글을 검색")
    @GetMapping(value = "")
    public ListResult<Board> findAllBoard() {
        return responseService.getListResult(boardRepository.findAll());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "ID 검색", notes = "ID로 게시글 검색")
    @GetMapping(value = "/id/{userId}")
    public ListResult<Board> findBoardByUserId(@ApiParam(value = "ID", required = true) @PathVariable String userId) {
        return responseService.getListResult(boardRepository.findByUserIdIgnoreCaseLike("%" + userId + "%"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "제목 검색", notes = "제목으로 게시글 검색")
    @GetMapping(value = "/title/{title}")
    public ListResult<Board> findBoardByTitle(@ApiParam(value = "제목", required = true) @PathVariable String title) {
        return responseService.getListResult(boardRepository.findByTitleIgnoreCaseLike("%" + title + "%"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "내용 검색", notes = "내용으로 게시글 검색")
    @GetMapping(value = "/content/{content}")
    public ListResult<Board> findBoardByContent(@ApiParam(value = "내용", required = true) @PathVariable String content) {
        return responseService.getListResult(boardRepository.findByContentIgnoreCaseLike("%" + content + "%"));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "게시글 수정", notes = "게시판의 글을 수정")
    @PutMapping(value = "/{boardNum}")
    public SingleResult<Board> updateBoard(@PathVariable long boardNum, @Valid @ModelAttribute BoardDTO boardDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        return responseService.getSingleResult(boardService.updateBoard(boardNum, userId, boardDTO));
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "게시글 삭제", notes = "게시판의 글을 삭제")
    @DeleteMapping(value = "/{boardNum}")
    public Header deleteBoard(@PathVariable long boardNum) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        boardService.deleteBoard(boardNum, userId);
        return responseService.getSuccessResult();
    }


}
