package com.gil.easyjwttest.user;

import com.gil.easyjwt.user.JwtUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Getter
@NoArgsConstructor
@Entity
public class User extends JwtUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(30)", unique = true)
    private String accountId;

    @NotNull
    @Column(columnDefinition = "CHAR(60)")
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(5)")
    private Authority authority;

    @Builder
    public User(String accountId, String password, Authority authority) {
        super(accountId, Collections.singleton(new SimpleGrantedAuthority(authority.name())));
        this.accountId = accountId;
        this.password = password;
        this.authority = authority;
    }

    @Override
    public String getUsername() {
        return getAccountId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(getAuthority().name()));
    }
}
