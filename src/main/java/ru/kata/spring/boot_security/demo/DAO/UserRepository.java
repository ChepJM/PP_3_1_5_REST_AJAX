package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kata.spring.boot_security.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u join fetch u.roleList WHERE u.username = :username")
    User findByUsername(String username);
    @Query("SELECT u FROM User u join fetch u.roleList WHERE u.email = :email")
    User findByEmail(String email);
}
