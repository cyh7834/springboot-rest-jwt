package yh.simplejwt.restjwt.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import yh.simplejwt.restjwt.dto.ReplyDTO;
import yh.simplejwt.restjwt.entity.Reply;
import yh.simplejwt.restjwt.network.Header;
import yh.simplejwt.restjwt.network.ListResult;
import yh.simplejwt.restjwt.network.SingleResult;
import yh.simplejwt.restjwt.repository.ReplyRepository;
import yh.simplejwt.restjwt.service.ReplyService;
import yh.simplejwt.restjwt.service.ResponseService;

import javax.validation.Valid;

@Api(tags = {"4. Reply"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/reply")
public class ReplyController {
    private final ResponseService responseService;
    private final ReplyService replyService;
    private final ReplyRepository replyRepository;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "댓글 등록", notes = "댓글을 등록")
    @PostMapping(value = "")
    public SingleResult<Reply> writeReply(@Valid @ModelAttribute ReplyDTO replyDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        return responseService.getSingleResult(replyService.writeReply(replyDTO, userId));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "게시글 번호 검색", notes = "게시글 번호로 댓글 검색")
    @GetMapping(value = "/{boardNum}")
    public ListResult<Reply> findReplyByBoardNum(@ApiParam(value = "게시글 번호", required = true) @PathVariable long boardNum) {
        return responseService.getListResult(replyRepository.findByBoardBoardNum(boardNum));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정")
    @PutMapping(value = "/{replyNum}")
    public SingleResult<Reply> updateReply(@PathVariable long replyNum, @Valid @ModelAttribute ReplyDTO replyDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        return responseService.getSingleResult(replyService.updateReply(userId, replyDTO, replyNum));
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제")
    @DeleteMapping(value = "/{replyNum}")
    public Header deleteReply(@PathVariable long replyNum) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        replyService.deleteReply(replyNum, userId);

        return responseService.getSuccessResult();
    }

}
