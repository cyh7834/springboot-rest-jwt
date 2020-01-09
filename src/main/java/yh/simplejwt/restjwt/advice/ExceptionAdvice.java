package yh.simplejwt.restjwt.advice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yh.simplejwt.restjwt.advice.exception.CustomAuthenticationEntryPointException;
import yh.simplejwt.restjwt.advice.exception.CustomLoginFailedException;
import yh.simplejwt.restjwt.advice.exception.CustomUserNotFoundException;
import yh.simplejwt.restjwt.network.Header;
import yh.simplejwt.restjwt.service.ResponseService;

import javax.servlet.http.HttpServletRequest;
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
    private final ResponseService responseService;

    @ExceptionHandler(CustomUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected Header userNotFoundException(HttpServletRequest request, CustomUserNotFoundException e) {
        return responseService.getFailResult(-1, "회원 정보가 존재하지 않습니다.");
    }

    @ExceptionHandler(CustomLoginFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected Header loginFailed(HttpServletRequest request, CustomLoginFailedException e) {
        return responseService.getFailResult(-1, "아이디와 비밀번호를 확인해주세요.");
    }

    @ExceptionHandler(CustomAuthenticationEntryPointException.class)
    public Header authenticationEntryPointException(HttpServletRequest request, CustomAuthenticationEntryPointException e) {
        return responseService.getFailResult(-1, "로그인 후 이용 가능합니다.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Header AccessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return responseService.getFailResult(-1, "해당 권한으로는 접근할 수 없습니다.");
    }
}
