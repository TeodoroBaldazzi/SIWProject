package it.uniroma3.service;

import it.uniroma3.model.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}
