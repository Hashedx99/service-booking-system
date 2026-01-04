package com.example.Project2.service;

import com.example.Project2.model.SecureToken;
import org.springframework.stereotype.Service;

public interface SecureTokenService {

    SecureToken createToken();
    void saveSecureToken(SecureToken secureToken);
    SecureToken findByToken(String token);
    void removeToken(SecureToken token);

}
