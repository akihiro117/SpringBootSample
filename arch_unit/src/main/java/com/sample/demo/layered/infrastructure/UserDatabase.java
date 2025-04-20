package com.sample.demo.layered.infrastructure;

import com.sample.demo.layered.infrastructure.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDatabase {

    public User obtainUser(Integer userId) {
        return new User(1, "First", "Last");
    }
}
