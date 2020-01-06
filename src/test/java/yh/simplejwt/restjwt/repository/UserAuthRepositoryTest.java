package yh.simplejwt.restjwt.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import yh.simplejwt.restjwt.RestJwtApplicationTests;
import yh.simplejwt.restjwt.entity.UserAuth;

import java.util.Optional;

public class UserAuthRepositoryTest extends RestJwtApplicationTests {
    @Autowired
    private UserAuthRepository userAuthRepository;

    @Test
    public void read() {
        Optional<UserAuth> userAuth = userAuthRepository.findById(1L);
        userAuth.ifPresent(selectedUserAuth -> {
            System.out.println(selectedUserAuth.getAuthority());
        });
        Assert.assertTrue(userAuth.isPresent());

    }
}
