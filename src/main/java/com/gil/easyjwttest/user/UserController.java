package com.gil.easyjwttest.user;

import com.gil.easyjwt.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return userService.login();
    }

    @GetMapping("/auth")
    public String testAuth() {
        return userService.testAuth();
    }

    @GetMapping("/auth/user")
    public String testAuthUser() {
        return userService.testAuth();
    }

    @GetMapping("/auth/admin")
    public String testAuthAdmin() {
        return userService.testAuth();
    }
}
