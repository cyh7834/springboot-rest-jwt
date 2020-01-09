package yh.simplejwt.restjwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import yh.simplejwt.restjwt.advice.exception.CustomUserNotFoundException;
import yh.simplejwt.restjwt.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String userId) {
        return userRepository.findById(userId).orElseThrow(CustomUserNotFoundException::new);
    }
}