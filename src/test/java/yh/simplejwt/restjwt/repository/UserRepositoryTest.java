package yh.simplejwt.restjwt.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import yh.simplejwt.restjwt.RestJwtApplicationTests;
import yh.simplejwt.restjwt.entity.User;

import java.util.Optional;

public class UserRepositoryTest extends RestJwtApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void read() {
        Optional<User> user = userRepository.findById(1L);
        user.ifPresent(selectedUser -> {
            System.out.println(selectedUser.getNickname());
        });
        Assert.assertTrue(user.isPresent());

    }
}
