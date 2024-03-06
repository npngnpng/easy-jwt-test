package com.gil.easyjwttest.security;

import com.gil.easyjwt.jwt.JwtFilter;
import com.gil.easyjwt.jwt.JwtTokenProvider;
import com.gil.easyjwt.user.CurrentUserService;
import com.gil.easyjwttest.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.gil.easyjwttest.user.Authority.ADMIN;
import static com.gil.easyjwttest.user.Authority.USER;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(HttpMethod.GET, "/login").permitAll()
                                .requestMatchers(HttpMethod.GET, "/auth").authenticated()
                                .requestMatchers(HttpMethod.GET, "/auth/user").hasAuthority(USER.name())
                                .requestMatchers(HttpMethod.GET, "/auth/admin").hasAuthority(ADMIN.name())
                                .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CurrentUserService<User> currentUserService() {
        return new CurrentUserService<>();
    }
}
