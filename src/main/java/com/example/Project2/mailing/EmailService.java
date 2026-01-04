package com.example.Project2.mailing;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface EmailService {
    void sendMail(final AbstractEmailContext email) throws UsernameNotFoundException;

}

