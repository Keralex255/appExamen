package org.example.appexamen.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/userlist")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/register")
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            existingUser.setActive(true);  // Update isactive status
            userRepository.save(existingUser);
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @DeleteMapping(path = "userId")
    public void deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "userId")
    public void updateUser(@PathVariable("userId") Long userId,
                           @RequestParam(required = false) String username) {
        userService.updateUsername(userId, username);
    }

}
