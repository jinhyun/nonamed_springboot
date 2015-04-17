package io.nonamed.service.user;

import io.nonamed.dao.user.UserRepository;
import io.nonamed.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getUserList(){
        return userRepository.findAll();
    }
}