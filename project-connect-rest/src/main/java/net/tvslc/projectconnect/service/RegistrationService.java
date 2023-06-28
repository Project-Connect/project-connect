package net.tvslc.projectconnect.service;

import lombok.AllArgsConstructor;
import net.tvslc.projectconnect.model.GetUserResponse;
import net.tvslc.projectconnect.model.RegistrationRequest;
import net.tvslc.projectconnect.model.UserEntity;
import net.tvslc.projectconnect.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RegistrationService {

    UserRepository userRepository;
    public ResponseEntity<GetUserResponse> getUserByUsername(String username) {
        // first get the entity by username;
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        //create sub model instance:
        GetUserResponse getUserResponse = new GetUserResponse();
        // store information from user entity to userResponse
        if (userEntity.isPresent()){// to avoid error from userEntity
            UserEntity user = userEntity.get();
            getUserResponse.setUsername(user.getUsername());
            getUserResponse.setEmail(user.getEmail());
            getUserResponse.setBio(user.getBio());
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(getUserResponse,HttpStatus.OK);

    }

    public ResponseEntity<UserEntity> updateUser(RegistrationRequest request){
    // find specific user's entity
    Optional<UserEntity> singleUser = userRepository.findByUsername(request.getUsername());

    //update the rest parameters in singleUser entity
        if (singleUser.isPresent()){// to avoid error from userEntity
        //update variable in java
        UserEntity updateUser = singleUser.get();
        updateUser.setPassword(request.getPassword());
        updateUser.setEmail(request.getEmail());
        updateUser.setBio(request.getBio());
        // update variables to database
        return new ResponseEntity<>(userRepository.save(updateUser), HttpStatus.OK);

    }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    public UserEntity register(UserEntity userEntity){
        return userRepository.save(userEntity);
    }


}

