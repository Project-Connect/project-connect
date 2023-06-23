package net.tvslc.projectconnect.controller;

import lombok.AllArgsConstructor;
import net.tvslc.projectconnect.mapper.RegistrationRequestMapper;
import net.tvslc.projectconnect.model.RegistrationRequest;
import net.tvslc.projectconnect.model.UserEntity;
import net.tvslc.projectconnect.service.RegistrationService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/")
public class RegistrationController {

    private RegistrationService registrationService;

    private RegistrationRequestMapper userMapper;

    @PostMapping(path = "registration")
    public UserEntity register(@RequestBody RegistrationRequest request) {
        return registrationService.register(userMapper.convertToEntity(request));
    }

    @GetMapping(path = "user/{username}")
    public UserEntity getUser(String userId) {
        //TODO: return ONLY the username, email and bio of the user
        return null;
    }

    @PutMapping(path = "user")
    public UserEntity updateUser(@RequestBody RegistrationRequest request) {
        //TODO: allow update to password, bio and email, NOT username
        return null;
    }

    @DeleteMapping(path = "user/{username}")
    public UserEntity delete(String userId) {
        //TODO: return a message when the user is deleted succefully
        return null;
    }
}