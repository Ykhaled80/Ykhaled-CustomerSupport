package com.example.ykhaledcustomersupport.site;

import com.example.ykhaledcustomersupport.entities.UserPrincipal;

public interface UserPrincipalRepository extends GenericRepository<Long, UserPrincipal> {
    UserPrincipal getByUsername(String username);
}
