package com.gil.easyjwttest.user;

import com.gil.easyjwt.auth.JwtUserDetails;
import com.gil.easyjwt.jwt.JwtProperties;
import com.gil.easyjwt.jwt.JwtTokenProvider;
import com.gil.easyjwt.jwt.TokenType;
import com.gil.easyjwt.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final CurrentUserService<User> currentUserService;

    public String login() {
        User user = User.builder()
                .accountId("gggugg06")
                .password("1234")
                .authority(Authority.USER)
                .build();
        userRepository.save(user);

        return jwtTokenProvider.generateAccessToken(user.getAccountId());
    }

    public String testAuth() {
        return currentUserService.getCurrentUser().getAccountId();
    }
}
