package yh.simplejwt.restjwt.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import yh.simplejwt.restjwt.advice.exception.CustomUserNotFoundException;
import yh.simplejwt.restjwt.entity.User;
import yh.simplejwt.restjwt.entity.UserAuth;
import yh.simplejwt.restjwt.network.Header;
import yh.simplejwt.restjwt.network.ListResult;
import yh.simplejwt.restjwt.network.SingleResult;
import yh.simplejwt.restjwt.repository.UserRepository;
import yh.simplejwt.restjwt.service.ResponseService;

@Api(tags = {"1. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "모든 회원 조회", notes = "모든 회원을 조회")
    @GetMapping(value = "")
    public ListResult<User> findAllUser() {
        return responseService.getListResult(userRepository.findAll());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 조회", notes = "회원 번호로 회원을 조회")
    @GetMapping(value = "/{userNum}")
    public SingleResult<User> findUserById(@ApiParam(value = "회원ID", required = true) @PathVariable long userNum) {
        return responseService.getSingleResult(userRepository.findById(userNum).orElseThrow(CustomUserNotFoundException::new));
    }

    @ApiOperation(value = "회원 가입", notes = "회원으로 가입")
    @PostMapping(value = "")
    public Header save(@ApiParam(value = "회원 아이디", required = true) @RequestParam String id,
                       @ApiParam(value = "회원 비밀번호", required = true) @RequestParam String password,
                       @ApiParam(value = "회원 이메일", required = true) @RequestParam String email,
                       @ApiParam(value = "회원 닉네임", required = true) @RequestParam String nickName) {
        User user = new User();
        UserAuth userAuth = new UserAuth();
        userAuth.setAuthority("ROLE_USER");

        userRepository.save(user.builder()
                .id(id)
                .password(passwordEncoder.encode(password))
                .email(email)
                .nickname(nickName)
                .userAuth(userAuth)
                .build());

        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 수정", notes = "회원 정보를 수정")
    @PutMapping(value = "")
    public SingleResult<User> modify(
            @ApiParam(value = "회원 번호", required = true) @RequestParam long userNum,
            @ApiParam(value = "회원 아이디", required = true) @RequestParam String id,
            @ApiParam(value = "회원 비밀번호", required = true) @RequestParam String password,
            @ApiParam(value = "회원 이메일", required = true) @RequestParam String email,
            @ApiParam(value = "회원 닉네임", required = true) @RequestParam String nickName) {

        userRepository.findById(userNum).map(selectedUser -> {
            selectedUser.setId(id);
            selectedUser.setNickname(nickName);
            selectedUser.setEmail(email);
            selectedUser.setPassword(password);
            return responseService.getSingleResult(userRepository.save(selectedUser));
        });
        return null;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "JWT", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 삭제", notes = "회원 번호로 회원정보를 삭제")
    @DeleteMapping(value = "/{userNum}")
    public Header delete(@ApiParam(value = "회원 번호", required = true) @PathVariable long userNum) {
        userRepository.deleteById(userNum);
        return responseService.getSuccessResult();
    }

}
