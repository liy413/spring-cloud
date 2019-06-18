package com.liy.com;

import com.liy.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserController {

    @GetMapping("/user")
    public User user(@RequestParam("id") int id);
}
