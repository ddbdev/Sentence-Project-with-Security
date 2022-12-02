package it.cgmconsulting.msuser.payload.request;

import lombok.Getter;

@Getter
public class SigninRequest {
    private String username;
    private String password;
}
