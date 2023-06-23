package net.tvslc.projectconnect.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationRequest {

    private String username;
    private String password;
    private String email;
    private String bio;

}
