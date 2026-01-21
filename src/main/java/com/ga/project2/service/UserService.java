package com.ga.project2.service;

import com.ga.project2.exception.InformationExistException;
import com.ga.project2.exception.InformationNotFoundException;
import com.ga.project2.exception.MissingFieldException;
import com.ga.project2.exception.UserNotAuthorizedException;
import com.ga.project2.mailing.AccountPasswordResetEmailContext;
import com.ga.project2.mailing.AccountVerificationEmailContext;
import com.ga.project2.mailing.EmailService;
import com.ga.project2.model.*;
import com.ga.project2.model.request.ImageModel;
import com.ga.project2.model.request.LoginRequest;
import com.ga.project2.model.response.LoginResponse;
import com.ga.project2.repository.ImageRepository;
import com.ga.project2.repository.UserRepository;
import com.ga.project2.security.JWTUtils;
import com.ga.project2.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Value("${site.base.url.https}")
    private String baseurl;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final SecureTokenService secureTokenService;
    final EmailService emailService;
    final ImageServiceImpl imageService;
    final ImageRepository imageRepository;


    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, JWTUtils jwtUtils,
                       @Lazy AuthenticationManager authenticationManager, EmailService emailService,
                       ImageServiceImpl imageService, ImageRepository imageRepository,
                       SecureTokenService secureTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.emailService = emailService;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
        this.secureTokenService = secureTokenService;
    }

    public User createUser(User userObj) {
        if (userObj.getEmailAddress() == null || userObj.getUserName() == null || userObj.getPassword() == null
                || userObj.getEmailAddress().isEmpty() || userObj.getUserName().isEmpty() || userObj.getPassword().isEmpty()) {
            throw new MissingFieldException("Email, username and password must not be null");
        }
        System.out.println("service calling create user =======>");
        User existingUser = userRepository.findUserByEmailAddress(userObj.getEmailAddress());
        if (existingUser != null) {
            if (!existingUser.isActivated()) {
                existingUser.setActivated(true);
                existingUser.setPassword(passwordEncoder.encode(userObj.getPassword()));
                User result = userRepository.save(existingUser);
                sendConfirmationEmail(existingUser);
                return result;
            } else {
                throw new InformationExistException("a user with this email already exists " + userObj.getEmailAddress());
            }
        } else {
            userObj.setPassword(passwordEncoder.encode(userObj.getPassword()));
            userObj.setActivated(true);
            User result = userRepository.save(userObj);
            sendConfirmationEmail(userObj);
            return result;
        }

    }

    public User setUserImage(ImageModel image) {
        User user = getUser();
        System.out.println("fouuuuuuuund===" + user);
        String imgUrl = imageService.uploadImage(image, "usersImages");
        Image savedImage = imageRepository.findByUrl(imgUrl);
        user.getUserProfile().setImage(savedImage);


        return user;


    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmailAddress(email);
    }


    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest
                            .getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
            final String JWT = jwtUtils.generateJwtToken(myUserDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));
        } catch (Exception e) {
            return ResponseEntity.ok(new LoginResponse("username or password are incorrect"));
        }
    }

    public void sendConfirmationEmail(User user) {
        SecureToken secureToken = secureTokenService.createToken();
        secureToken.setUser(user);
        secureTokenService.saveSecureToken(secureToken);
        AccountVerificationEmailContext context = new AccountVerificationEmailContext();
        context.init(user);
        context.setToken(secureToken.getToken());
        context.buildVerificationUrl(baseurl, secureToken.getToken());

        System.out.println("sending email to " + user.getEmailAddress());
        emailService.sendMail(context);
    }

    public void resetPassword(String email) {
        SecureToken secureToken = secureTokenService.createToken();
        User user = userRepository.findUserByEmailAddress(email);
        System.out.println("service found user ====> " + user.getUserName());
        secureToken.setUser(user);
        secureTokenService.saveSecureToken(secureToken);
        AccountPasswordResetEmailContext context = new AccountPasswordResetEmailContext();
        context.init(user);
        context.setToken(secureToken.getToken());
        context.buildResetUrl(baseurl, secureToken.getToken());

        System.out.println("sending email to " + user.getEmailAddress());
        emailService.sendMail(context);
    }

    public void resetPasswordActivator(String token, User userObj) {
        SecureToken secureToken = secureTokenService.findByToken(token);
        User user = secureToken.getUser();
        user.setPassword(passwordEncoder.encode(userObj.getPassword()));
        userRepository.save(user);

    }

    public void changePassword(String oldPass, String newPass) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        System.out.println("the useeeer");
        User user = myUserDetails.getUser();
        if (newPass == null) {
            throw new MissingFieldException("New password must not be null");
        }
        try {
            if (passwordEncoder.matches(oldPass, user.getPassword())) {

                user.setPassword(passwordEncoder.encode(newPass));
                userRepository.save(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void validate(String token) {
        SecureToken secureToken = secureTokenService.findByToken(token);
        User user = secureToken.getUser();
        user.setAccountVerified(true);
        userRepository.save(user);
    }


    public void softDelete() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        User user = myUserDetails.getUser();
        user.setActivated(false);
        user.setAccountVerified(false);
        userRepository.save(user);
    }

    public void deleteUserById(Long id) throws UserNotAuthorizedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        User user = myUserDetails.getUser();
        if (UserRoles.ADMIN != user.getRole()) {
            throw new UserNotAuthorizedException("Only admin can delete users");
        }
        User userToBeDeleted = getUserById(id);
        if (userToBeDeleted == null) {
            throw new InformationNotFoundException("User not found with id: " + id);
        }
        userToBeDeleted.setActivated(false);
        userToBeDeleted.setAccountVerified(false);
        userRepository.save(userToBeDeleted);
    }

    // function to get the user by the email from the security context holder
    public User getUser() {
        // return the user object from the user details object from the security context holder
        return ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

    }

    // function to get a user by id

    public User getUserById(Long id) {
        // return the user or throw not found exceptions
        return userRepository.getUserById(id).orElseThrow(() -> new InformationNotFoundException("user not found"));
    }

    public User updateUserDetails(UserProfile userProfile) {
        User user = getUser();
        UserProfile existingProfile = user.getUserProfile();

        existingProfile.setFirstName(userProfile.getFirstName());
        existingProfile.setLastName(userProfile.getLastName());
        existingProfile.setProfileDescription(userProfile.getProfileDescription());

        user.setUserProfile(existingProfile);
        return userRepository.save(user);
    }
}

