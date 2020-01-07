package yh.simplejwt.restjwt.advice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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
}
