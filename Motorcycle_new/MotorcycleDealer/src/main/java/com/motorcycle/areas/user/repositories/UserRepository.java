package com.motorcycle.areas.user.repositories;

import com.motorcycle.areas.user.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface UserRepository<T extends User> extends CrudRepository<T, Long> {

    @Query("select u from User As u where u.username = :username and u.password = :password")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    T findOneByUsername(String username);
}
