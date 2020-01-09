package yh.simplejwt.restjwt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yh.simplejwt.restjwt.advice.exception.CustomLoginFailedException;
import yh.simplejwt.restjwt.entity.User;
import yh.simplejwt.restjwt.network.SingleResult;
import yh.simplejwt.restjwt.provider.JwtTokenProvider;
import yh.simplejwt.restjwt.repository.UserRepository;
import yh.simplejwt.restjwt.service.ResponseService;

@Api(tags = {"1. Log in"})
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value = "로그인", notes = "회원 로그인")
    @PostMapping(value = "/login")
    public SingleResult<String> login(@ApiParam(value = "회원ID", required = true) @RequestParam String id,
                                      @ApiParam(value = "비밀번호", required = true) @RequestParam String password) {

        User user = userRepository.findById(id).orElseThrow(CustomLoginFailedException::new);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new CustomLoginFailedException();

        return responseService.getSingleResult(jwtTokenProvider.createToken(user.getUsername(), user.getUserAuth().getAuthority()));
    }

}
