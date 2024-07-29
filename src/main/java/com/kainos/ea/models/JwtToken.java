package com.kainos.ea.models;

import javax.security.auth.Subject;
import java.security.Principal;

public class JwtToken implements Principal {

    UserRole userRole;

    public JwtToken(UserRole userRole){
        setUserRole(userRole);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }
    public UserRole getUserRole(){
        return userRole;
    }

    public void setUserRole(UserRole userRole){
        this.userRole = userRole;
    }


}
