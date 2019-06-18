package com.liy;

import com.liy.com.UserController;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "provider",fallback = ServiceFallback.class)
public interface UserService extends UserController {
}
