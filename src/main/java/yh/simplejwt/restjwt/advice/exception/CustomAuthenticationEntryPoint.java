package yh.simplejwt.restjwt.advice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
// 예외 처리가 security 단이 아닌 controller 까지 도달 하기 위해
// SpringSecurity에서 제공하는 AuthenticationEntryPoint를 상속받아 재정의
// 온전한 jwt가 전달이 안됐을 경우에 토큰 인증이 불가능하다. 컨트롤러로 redirect하여 예외처리
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException,
            ServletException {
        response.sendRedirect("/exception/entrypoint");
    }
}