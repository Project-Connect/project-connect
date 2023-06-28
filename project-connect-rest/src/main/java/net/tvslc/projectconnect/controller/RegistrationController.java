package net.tvslc.projectconnect.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.tvslc.projectconnect.mapper.RegistrationRequestMapper;
import net.tvslc.projectconnect.model.GetUserResponse;
import net.tvslc.projectconnect.model.RegistrationRequest;
import net.tvslc.projectconnect.model.UserEntity;
import net.tvslc.projectconnect.repository.UserRepository;
import net.tvslc.projectconnect.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
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
    public ResponseEntity<GetUserResponse> getUser(String username) { // responseEntity help format the content into json
        //TODO: return ONLY the username, email and bio of the user;
        Optional<GetUserResponse> getUserResponse = registrationService.getUserByUsername(username);

        if (getUserResponse.isPresent()){
            return new ResponseEntity<GetUserResponse>(getUserResponse.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @PutMapping(path = "user")
    public UserEntity updateUser(@RequestBody RegistrationRequest request) {
        //TODO: allow update to password, bio and email, NOT username
        return null;
    }

    @DeleteMapping(path = "user/{username}")
    public UserEntity delete(String userId) {
        //TODO: return a message when the user is deleted successfully
        return null;
    }
}