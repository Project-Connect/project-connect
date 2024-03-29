package net.tvslc.projectconnect.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.tvslc.projectconnect.mapper.RegistrationRequestMapper;
import net.tvslc.projectconnect.model.GetUserResponse;
import net.tvslc.projectconnect.model.RegistrationRequest;
import net.tvslc.projectconnect.model.UserEntity;
import net.tvslc.projectconnect.repository.UserRepository;
import net.tvslc.projectconnect.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = "api/v1/")
public class RegistrationController {

    private RegistrationService registrationService;

    private RegistrationRequestMapper userMapper;

    private UserRepository userRepository;// we have all args, here when define userRepository, dependency injection, create copy automatically

    @PostMapping(path = "registration")
    public UserEntity register(@RequestBody RegistrationRequest request) {
        return registrationService.register(userMapper.convertToEntity(request));
    }

    @GetMapping(path = "user/{username}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable("username") String username) { // responseEntity help format the content into json
        //TODO: return ONLY the username, email and bio of the user;
        log.info(username);
        return registrationService.getUserByUsername(username);
    }

    @PutMapping(path = "user")
    public ResponseEntity<String> updateUser(@RequestBody RegistrationRequest request) {
        //TODO: allow update to password, bio and email, NOT username

        return registrationService.updateUser(request);
    }

    @DeleteMapping(path = "user/{username}")
    public ResponseEntity<String> delete(@PathVariable("username") String username) {
        //TODO: return a message when the user is deleted successfully
        //
        return registrationService.deleteUserByUsername(username);
    }
}