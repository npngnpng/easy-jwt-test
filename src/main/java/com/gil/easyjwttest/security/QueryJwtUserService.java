package com.gil.easyjwttest.security;

import com.gil.easyjwt.user.JwtUser;
import com.gil.easyjwttest.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class QueryJwtUserService implements com.gil.easyjwt.user.QueryJwtUserService {

    private final UserRepository userRepository;

    @Override
    public Optional<JwtUser> execute(String username) {
        return userRepository.findByAccountId(username)
                .map(JwtUser.class::cast);
    }
}
