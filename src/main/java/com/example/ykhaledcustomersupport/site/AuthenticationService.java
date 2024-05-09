package com.example.ykhaledcustomersupport.site;

import com.example.ykhaledcustomersupport.entities.UserPrincipal;

public interface AuthenticationService {
    UserPrincipal authenticate (String username, String password);
    void saveUser(UserPrincipal principal, String newPassword);
}
