package co.develhope.esercizio_unit_test.service;

import co.develhope.esercizio_unit_test.entity.User;
import co.develhope.esercizio_unit_test.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private static final User DEFAULT_USER_TOINSERT = new User(0, "Gianni", "Gia", 26);

    private static final User DEFAULT_USER_RETURNED = new User(1, "Gianni", "Gia", 26);

    @Test
    void testCreateUser() {
        when(userRepository.save(DEFAULT_USER_TOINSERT)).thenReturn(DEFAULT_USER_RETURNED);
        User result = userService.createUser(DEFAULT_USER_TOINSERT);
        assertEquals(DEFAULT_USER_RETURNED, result);
    }

    @Test
    void testFindUserById () {
        when(userRepository.findById(DEFAULT_USER_RETURNED.getId())).thenReturn(Optional.of(DEFAULT_USER_RETURNED));
        when(userRepository.existsById(DEFAULT_USER_RETURNED.getId())).thenReturn(true);
        User result = userService.findUserById(DEFAULT_USER_RETURNED.getId());
        assertEquals(DEFAULT_USER_RETURNED, result);
    }

    @Test
    void testFindAllUsers () {
        when(userRepository.findAll()).thenReturn(List.of(DEFAULT_USER_RETURNED));
        List<User> result = userService.findAllUsers();
        assertEquals(List.of(DEFAULT_USER_RETURNED), result);
    }

    @Test
    void deleteUserById () {
        when(userRepository.existsById(DEFAULT_USER_RETURNED.getId())).thenReturn(true);
        userService.deleteUserById(DEFAULT_USER_RETURNED.getId());
        verify(userRepository, times(1)).deleteById(DEFAULT_USER_RETURNED.getId());
    }

}
