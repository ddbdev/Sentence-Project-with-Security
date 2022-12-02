package it.cgmconsulting.msuser.service;

import it.cgmconsulting.msuser.entity.Users;
import it.cgmconsulting.msuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(Users users){
        userRepository.save(users);
    }

    public Optional<Users> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<Users> findById(Long id){
        return userRepository.findById(id);
    }
}
