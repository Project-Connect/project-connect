package net.tvslc.projectconnect.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor// saving the code of self.username
public class GetUserResponse {

    private String username;

    private String email;

    private String bio;
}