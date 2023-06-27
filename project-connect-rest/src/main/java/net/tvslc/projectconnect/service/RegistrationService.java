package net.tvslc.projectconnect.service;

import lombok.AllArgsConstructor;
import net.tvslc.projectconnect.model.GetUserResponse;
import net.tvslc.projectconnect.model.UserEntity;
import net.tvslc.projectconnect.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RegistrationService {

    UserRepository userRepository;
    public Optional<GetUserResponse> getUserByUsername(String username) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        GetUserResponse getUserResponse = new GetUserResponse();
        if (userEntity.isPresent()){// to avoid error from userEntity
            UserEntity user = userEntity.get();
            getUserResponse.setUsername(user.getUsername());
            getUserResponse.setEmail(user.getEmail());
            getUserResponse.setBio(user.getBio());
        }else{
            return Optional.empty();

        }
        return Optional.of(getUserResponse);

    }

    public UserEntity register(UserEntity userEntity){
        return userRepository.save(userEntity);
    }
}