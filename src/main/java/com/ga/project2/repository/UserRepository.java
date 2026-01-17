package com.ga.project2.repository;

import com.ga.project2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User,Long>{
    boolean existsByEmailAddress(String emailAddress);
    boolean existsByEmailAddressAndIsActivatedTrue(String emailAddress);
    User findUserByEmailAddress(String emailAddress);
    User findByUserName(String userName);
    Optional<User> getUserById(Long id);

}