package com.app.spring_restaurant_jwt_application.controller;


import com.app.spring_restaurant_jwt_application.config.JwtProvider;
import com.app.spring_restaurant_jwt_application.exception.UserException;
import com.app.spring_restaurant_jwt_application.model.Cart;
import com.app.spring_restaurant_jwt_application.model.PasswordResetToken;
import com.app.spring_restaurant_jwt_application.model.USER_ROLE;
import com.app.spring_restaurant_jwt_application.model.User;
import com.app.spring_restaurant_jwt_application.repository.CartRepository;
import com.app.spring_restaurant_jwt_application.repository.UserRepository;
import com.app.spring_restaurant_jwt_application.request.LoginRequest;
import com.app.spring_restaurant_jwt_application.request.ResetPasswordRequest;
import com.app.spring_restaurant_jwt_application.response.ApiResponse;
import com.app.spring_restaurant_jwt_application.response.AuthResponse;
import com.app.spring_restaurant_jwt_application.serviceImpl.CustomeUserServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomeUserServiceImplementation customUserDetails;
    private final CartRepository cartRepository;
//    private final PasswordResetTokenService passwordResetTokenService;
//    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@Valid @RequestBody User user) throws UserException {

        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        USER_ROLE role = user.getRole();

        User isEmailExist = userRepository.findByEmail(email);

        if (isEmailExist != null) {

            throw new UserException("Email Is Already Used With Another Account");
        }

        // Create new user
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setFullName(fullName);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setRole(role);

        User savedUser = userRepository.save(createdUser);

        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        Cart savedCart = cartRepository.save(cart);
//		savedUser.setCart(savedCart);

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(role.toString()));


        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Register Success");
        authResponse.setRole(savedUser.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {

        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        System.out.println(username + " ----- " + password);

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setMessage("Login Success");
        authResponse.setJwt(token);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();


        String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();
        authResponse.setRole(USER_ROLE.valueOf(roleName));

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);

        System.out.println("sign in userDetails - " + userDetails);

        if (userDetails == null) {
            System.out.println("sign in userDetails - null " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            System.out.println("sign in userDetails - password not match " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

//    @PostMapping("/reset-password")
//    public ResponseEntity<ApiResponse> resetPassword(
//
//            @RequestBody ResetPasswordRequest req) throws UserException {
//
//        PasswordResetToken resetToken = passwordResetTokenService.findByToken(req.getToken());
//
//        if (resetToken == null) {
//            throw new UserException("token is required...");
//        }
//        if (resetToken.isExpired()) {
//            passwordResetTokenService.delete(resetToken);
//            throw new UserException("token get expired...");
//
//        }
//
//        // Update user's password
//        User user = resetToken.getUser();
//        userService.updatePassword(user, req.getPassword());
//
//        // Delete the token
//        passwordResetTokenService.delete(resetToken);
//
//        ApiResponse res = new ApiResponse();
//        res.setMessage("Password updated successfully.");
//        res.setStatus(true);
//
//        return ResponseEntity.ok(res);
//    }

//    @PostMapping("/reset-password-request")
//    public ResponseEntity<ApiResponse> resetPassword(@RequestParam("email") String email) throws UserException {
//        User user = userService.findUserByEmail(email);
//        System.out.println("ResetPasswordController.resetPassword()");
//
//        if (user == null) {
//            throw new UserException("user not found");
//        }
//
//        userService.sendPasswordResetEmail(user);
//
//        ApiResponse res = new ApiResponse();
//        res.setMessage("Password reset email sent successfully.");
//        res.setStatus(true);
//
//        return ResponseEntity.ok(res);
//    }

}
