package yh.simplejwt.restjwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yh.simplejwt.restjwt.advice.exception.CustomAuthenticationEntryPointException;
import yh.simplejwt.restjwt.network.Header;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

    @GetMapping(value = "/entrypoint")
    public Header entrypointException() {
        throw new CustomAuthenticationEntryPointException();
    }

    @GetMapping(value = "/accessdenied")
    public Header accessdeniedException() {
        throw new AccessDeniedException("");
    }
}