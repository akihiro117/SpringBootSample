package com.sample.demo.layered.infrastructure;

import com.sample.demo.layered.infrastructure.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDatabase {

    public User obtainUser(Integer userId) {
        User user = new User();
        user.setUserId(1);
        user.setFirstName("First");
        user.setLastName("Last");
        return user;
    }
}
