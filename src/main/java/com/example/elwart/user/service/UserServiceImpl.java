package com.example.elwart.user.service;

import com.example.elwart.user.dto.UserDto;
import com.example.elwart.user.exception.RoleNotFoundException;
import com.example.elwart.user.exception.UserNotFoundException;
import com.example.elwart.user.model.Delegation;
import com.example.elwart.user.model.Role;
import com.example.elwart.user.model.User;
import com.example.elwart.user.repository.RoleRepository;
import com.example.elwart.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

    }

    // tutaj void ? A nie mozna zwrocic Usera ?

    @Override
    public void registerUser(UserDto userDto) {
        User user = User.UserBuilder.anUser().withName(userDto.getName()).withFullName(userDto.getFullName()).
                withCompanyAddress(userDto.getCompanyAddress()).
                withCompanyName(userDto.getCompanyName()).withCompanyNip(userDto.getCompanyNip()).
                withEmail(userDto.getEmail()).
                withPassword(passwordEncoder.encode(userDto.getPassword())).withRoles(getRoles(userDto.getRoles())).build();
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void changePassword(Long userId, String password) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        user.setPassword(password);
        userRepository.save(user);
    }

    // Zrobilem to boolean bo pan kazal, ale mysle, ze Exceptions to lepszy pomysl

    @Override
    public boolean deleteUserById(Long userId) {
        if (userRepository.findById(userId).isEmpty())
            return false;
        userRepository.deleteById(userId);
        return true;
    }

    // Tutaj przy boolean to wgl nie wiemy o co chodzi ? Ktory to blad :/


    @Override
    public List<User> getAllByRole(String roleName) {
        Role role = roleRepository.findByRole(roleName).orElseThrow(()->new RoleNotFoundException(roleName));
        return userRepository.findAllByRoles(role);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(email));
    }


    private List<Role> getRoles(List<String> roles) {
        List<Role> roleList = new ArrayList<>();
        if (roles == null)
            roleList.add(roleRepository.findByRole("ROLE_USER").orElseThrow(() -> new RoleNotFoundException("ROLE_USER")));
        else
            roles.forEach(r -> roleList.add(roleRepository.findByRole(r).orElseThrow(() -> new RoleNotFoundException(r))));
        return roleList;
    }
}
