package it.uniroma3.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.uniroma3.model.Role;
import it.uniroma3.model.User;
import it.uniroma3.repository.RoleRepository;
import it.uniroma3.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveAdminUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void saveManagerUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);

        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRole("MANAGER"));

        user.setRoles(roles);
        userRepository.save(user);
    }


    public List<User> findAll(){
        return this.userRepository.findAll();
    }
}
