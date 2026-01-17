package com.ga.project2.config;

import com.ga.project2.model.Image;
import com.ga.project2.model.User;
import com.ga.project2.model.UserProfile;
import com.ga.project2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
   private final UserRepository userRepository;
   public DataSeeder(UserRepository userRepository){
       this.userRepository=userRepository;
   }
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.existsByEmailAddressAndIsActivatedTrue("test@gmail.com")) {
            return;
        }
            userSeed();
    }
    private void userSeed(){
       User user = new User();
        Image image = new Image();
       user.setPassword("$2a$10$/8Yh51sFJaukKOWbFES0IuwNB8q0M0XihNvR/1097DmJfb/kE9S0O");
       user.setActivated(true);
       user.setAccountVerified(true);
       user.setUserName("meemo");
       user.setEmailAddress("test@gmail.com");
        UserProfile userProfile = new UserProfile();
        userProfile.setImage(image);
        userProfile.setFirstName("ali");
        userProfile.setLastName("isa");
       user.setUserProfile(userProfile);
       userRepository.save(user);
    }
}
