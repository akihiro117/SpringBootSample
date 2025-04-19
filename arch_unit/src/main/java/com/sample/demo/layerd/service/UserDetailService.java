package com.sample.demo.layerd.service;

import com.sample.demo.layerd.domain.PersonName;
import com.sample.demo.layerd.domain.UserDetail;
import com.sample.demo.layerd.infrastructure.UserDatabase;
import com.sample.demo.layerd.infrastructure.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService {
    private final UserDatabase userDatabase;

    public UserDetailService(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public UserDetail obtainUserDetail(Integer userId) {
        User user = userDatabase.obtainUser(userId);
        return new UserDetail(user.getUserId(), new PersonName(user.getFirstName(), user.getLastName()));
    }
}
