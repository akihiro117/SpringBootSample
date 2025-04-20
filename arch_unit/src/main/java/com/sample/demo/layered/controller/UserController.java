package com.sample.demo.layered.controller;

import com.sample.demo.layered.controller.response.UserDetailResponse;
import com.sample.demo.layered.domain.UserDetail;
import com.sample.demo.layered.service.UserDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
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
