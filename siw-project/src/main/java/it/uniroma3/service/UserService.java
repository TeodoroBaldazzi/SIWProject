package it.uniroma3.service;

import it.uniroma3.model.User;

import javax.management.timer.TimerMBean;
import java.util.List;

public interface UserService {

    public User findUserByUsername(String username);

    public void saveAdminUser(User user);

    public void saveManagerUser(User user);

    public List<User> findAll();
}
