package net.tvslc.projectconnect.service;

import lombok.AllArgsConstructor;
import net.tvslc.projectconnect.model.UserEntity;
import net.tvslc.projectconnect.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    UserRepository userRepository;

    public UserEntity register(UserEntity userEntity){
        return userRepository.save(userEntity);
    }
}