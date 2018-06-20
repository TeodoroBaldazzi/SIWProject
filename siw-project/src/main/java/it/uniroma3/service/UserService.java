package it.uniroma3.service;

import it.uniroma3.model.User;

import javax.management.timer.TimerMBean;
import java.util.List;

public interface UserService {

    public User findUserByEmail(String email);

    public void saveUser(User user);

    public List<User> findAll();
}
