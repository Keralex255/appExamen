package org.example.appexamen.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //SELECT * FROM user WHERE username = ...
    @Query("SELECT u FROM User u where u.username=?1")
    Optional<User> findByUsernameOptional(String username);
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.isactive = true")
    Optional<User> findByIsActive(boolean isActive);

}
