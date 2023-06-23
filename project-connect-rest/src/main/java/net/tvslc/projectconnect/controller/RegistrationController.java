package net.tvslc.projectconnect.controller;

import lombok.AllArgsConstructor;
import net.tvslc.projectconnect.mapper.RegistrationRequestMapper;
import net.tvslc.projectconnect.model.RegistrationRequest;
import net.tvslc.projectconnect.model.UserEntity;
import net.tvslc.projectconnect.service.RegistrationService;
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
}