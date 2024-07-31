package com.example.barberia.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequest {
   
    @NotBlank
    private String username;
 
    @NotBlank
    @Email
    private String email;
    
    private Set<String> rol;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRol() {
      return this.rol;
    }
    
    public void setRol(Set<String> rol) {
      this.rol = rol;
    }
}