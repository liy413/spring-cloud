package com.liy;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements com.liy.com.UserController {

    @Override
    public User user(int id) {
        User user = new User();
        user.setId(id);
        return user;
    }
}
