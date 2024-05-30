package co.develhope.esercizio_unit_test.service;

import co.develhope.esercizio_unit_test.entity.User;
import co.develhope.esercizio_unit_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser (User user) {
        return userRepository.save(user);
    }

    public User findUserById (long id) {
        if (userRepository.existsById(id)) {
            return userRepository.findById(id).get();
        } else {
            return null;
        }
    }

    public List<User> findAllUsers () {
        return userRepository.findAll();
    }

    public User updateUser (long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser (long id) {
        userRepository.deleteById(id);
    }
}
