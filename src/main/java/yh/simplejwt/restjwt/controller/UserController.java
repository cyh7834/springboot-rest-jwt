package yh.simplejwt.restjwt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yh.simplejwt.restjwt.entity.User;
import yh.simplejwt.restjwt.entity.UserAuth;
import yh.simplejwt.restjwt.repository.UserRepository;

import java.util.List;

@Api(tags = {"1. User"})
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserRepository userRepository;

    @ApiOperation(value = "회원 조회", notes = "모든 회원을 조회")
    @GetMapping(value = "/user")
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @ApiOperation(value = "회원 가입", notes = "회원으로 가입")
    @PostMapping(value = "/user")
    public User save(@ApiParam(value = "회원 아이디", required = true) @RequestParam String id,
                     @ApiParam(value = "회원 비밀번호", required = true) @RequestParam String password,
                     @ApiParam(value = "회원 이메일", required = true) @RequestParam String email,
                     @ApiParam(value = "회원 닉네임", required = true) @RequestParam String nickName) {
        User user = new User();
        UserAuth userAuth = new UserAuth();
        userAuth.setAuthority("ROLE_USER");

        return userRepository.save(user.builder()
                .id(id)
                .password(password)
                .email(email)
                .nickname(nickName)
                .userAuth(userAuth)
                .build());
    }
}
