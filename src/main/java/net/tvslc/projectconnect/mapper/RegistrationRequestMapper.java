package net.tvslc.projectconnect.mapper;

import net.tvslc.projectconnect.model.RegistrationRequest;
import net.tvslc.projectconnect.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class RegistrationRequestMapper {

    public UserEntity convertToEntity(RegistrationRequest registrationRequest){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationRequest.getUsername());
        userEntity.setPassword(registrationRequest.getPassword());
        userEntity.setEmail(registrationRequest.getEmail());
        userEntity.setBio(registrationRequest.getBio());
        return userEntity;
    }
}
