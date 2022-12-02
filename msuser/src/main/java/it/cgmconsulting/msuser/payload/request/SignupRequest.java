package it.cgmconsulting.msuser.payload.request;

import lombok.Getter;

@Getter
public class SignupRequest {

    private String username;
    private String password;
    private String role;
}
