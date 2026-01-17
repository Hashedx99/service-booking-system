package com.ga.project2.service;

import com.ga.project2.model.SecureToken;

public interface SecureTokenService {

    SecureToken createToken();
    void saveSecureToken(SecureToken secureToken);
    SecureToken findByToken(String token);
    void removeToken(SecureToken token);

}
