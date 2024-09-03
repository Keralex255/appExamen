package org.example.appexamen.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getUsers()
    {
        return userRepository.findAll();
    }

    public void addNewUser(User user)
    {
        Optional<User> userOptional = userRepository.findByUsernameOptional(user.getUsername());
        if(userOptional.isPresent()){
            throw new IllegalStateException("Username already exists");
        }

        userRepository.save(user);
    }

    public void deleteUser(Long id)
    {
        boolean exists = userRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("User not found");
        }
        else
        {
            userRepository.deleteById(id);
        }
    }
    public void updateUsername(Long id, String username)
    {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User not found"));
        if(username != null && !username.isEmpty() &&
        !Objects.equals(user.getUsername(),username)){
            user.setUsername(username);
        }
        userRepository.save(user);

    }

    public Long getCurrentUserId()
    {
        User currentUser = userRepository.findByIsActive(true).orElseThrow(()-> new IllegalStateException("No active users"));
        return currentUser.getId();
    }
}
