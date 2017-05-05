package com.motorcycle.areas.user.repositories;

import com.motorcycle.areas.user.entities.BasicUser;
import com.motorcycle.areas.user.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasicUserRepository extends UserRepository<BasicUser> {

    @Query("select u from User as u")
    List<User> getAllUsers();
}
