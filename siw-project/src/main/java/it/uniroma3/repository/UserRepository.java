package it.uniroma3.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

}