package com.liy;

import org.springframework.stereotype.Component;

@Component
public class ServiceFallback implements UserService{
    @Override
    public User user(int id) {
        User user = new User();
        user.setName("default");
        return user;
    }
}
