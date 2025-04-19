package com.sample.demo.layerd.controller;

import com.sample.demo.layerd.controller.response.UserDetailResponse;
import com.sample.demo.layerd.domain.UserDetail;
import com.sample.demo.layerd.service.UserDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
    private final UserDetailService userDetailService;

    public UserController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDetailResponse> obtainUserDetail(@PathVariable("id") Integer userId) {
        UserDetail userDetail = userDetailService.obtainUserDetail(userId);
        return new ResponseEntity<>(new UserDetailResponse(userDetail.userId(), userDetail.name().firstName(), userDetail.name().lastName()), HttpStatus.OK);
    }
}
