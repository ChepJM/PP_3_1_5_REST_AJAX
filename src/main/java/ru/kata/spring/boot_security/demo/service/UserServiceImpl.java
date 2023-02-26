package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.RoleRepository;
import ru.kata.spring.boot_security.demo.DAO.UserRepository;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        User user = null;
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void saveUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());

        if ((existingUser == null) || (!user.getPassword().equals(existingUser.getPassword()))) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Transactional(readOnly = true)
    public void setUserRoles(User user) {
        user.setRoleList(user.getRoleList().stream()
                .map(r -> roleRepository.findByRoleName(r.getRoleName()))
                .collect(Collectors.toList()));
    }
}
