package com.example.ticketbookingrailwayapplication.service;

import com.example.ticketbookingrailwayapplication.dao.UserRepository;
import com.example.ticketbookingrailwayapplication.model.Role;
import com.example.ticketbookingrailwayapplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        {
            User admin = new User();
            admin.setActive(true);
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setRoles(Collections.singleton(Role.ROLE_ADMIN));
            registerUser(admin);
        }
    }
    @Transactional
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    @Transactional(readOnly = true)
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(long id, User user) {
        User existingUser = getUserById(id);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            existingUser.setActive(user.isActive());
            existingUser.setRoles(user.getRoles());
            return userRepository.save(existingUser);
        }
        return null;
    }
    @Transactional
    public int addUserDetail(long id, User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.updateById(user, id);

    }

    public int addUserDetailNoPass(long id, User user) {
        return userRepository.updateByIdNoPass(user, id);
    }
    @Transactional(readOnly = true)
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

}