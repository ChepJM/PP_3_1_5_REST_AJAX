package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

public interface RoleRepository extends JpaRepository<Role, Long> {

//    @Query("SELECT u FROM User u join fetch u.roleList WHERE u.email = :email")
    Role findByRoleName(String roleName);
}
