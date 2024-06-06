package com.app.spring_restaurant_jwt_application.serviceImpl;

import com.app.spring_restaurant_jwt_application.model.USER_ROLE;
import com.app.spring_restaurant_jwt_application.model.User;
import com.app.spring_restaurant_jwt_application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomeUserServiceImplementation implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found with email  - " + username);
        }
        USER_ROLE role = user.getRole();

        if (role == null) role = USER_ROLE.ROLE_CUSTOMER;

        System.out.println("role  ---- " + role);

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(role.toString()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), authorities);
    }

}
