package net.tvslc.projectconnect.service;

import net.tvslc.projectconnect.model.GetUserResponse;
import net.tvslc.projectconnect.model.RegistrationRequest;
import net.tvslc.projectconnect.model.UserEntity;
import net.tvslc.projectconnect.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import static org.junit.Assert.assertEquals;

public class RegistrationServiceTest {

    private UserRepository userRepository;

    private RegistrationService registrationService;

    @BeforeEach
    public void setupMock() {
        userRepository = mock(UserRepository.class);
        registrationService = new RegistrationService(userRepository);
    }

    public  UserEntity createBasicEntity(){
        UserEntity entity = new UserEntity();
        entity.setBio("This is a Bio");
        entity.setPassword("password");
        entity.setUsername("username");
        entity.setEmail("email");
        return entity;
    }


    @Test// the below function is for testing different function for a specific class<unit test>
    public void shouldCovertValidRequestToEntity(){// create function to test 1 activity, for automation

        Mockito.when(userRepository.findByUsername(any())).thenReturn(Optional.of(createBasicEntity()));

        ResponseEntity<GetUserResponse> userResponseEntity = registrationService.getUserByUsername("username");

        GetUserResponse getUserResponse = userResponseEntity.getBody();

        assertEquals(HttpStatus.OK, userResponseEntity.getStatusCode());
        assertEquals("This is a Bio", getUserResponse.getBio());
        assertEquals("username", getUserResponse.getUsername());
        assertEquals("email", getUserResponse.getEmail());
    }

    @Test// the below function is for testing different function for a specific class<unit test>
    public void shouldReturn404WhenUserEntityIsNull(){// create function to test 1 activity, for automation
        Mockito.when(userRepository.findByUsername(any())).thenReturn(Optional.empty());

        ResponseEntity<GetUserResponse> userResponseEntity = registrationService.getUserByUsername("username");
        GetUserResponse getUserResponse = userResponseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, userResponseEntity.getStatusCode());
        assertEquals(null, getUserResponse);
    }

    @Test// the below function is for testing if user get updated and username exist
    public void shouldUserUpdate (){
        // we need a registration request(create a constant)
        // Create a sample request, <username, password,email,bio>
        RegistrationRequest testRequest = new RegistrationRequest("Mom321@//","123abc@","hello$@gmail.com","hello, Mom!");

        // start with repository, since we are testing update function, so assuming function in service work well(use directly)
        // mock its return value: return an Entity
        Mockito.when(userRepository.findByUsername(testRequest.getUsername())).thenReturn(Optional.of(createBasicEntity()));

        // create an object that use the update user function and test its content(call the update function in its class)
        //test the function
        ResponseEntity<String> saveUserResponse = registrationService.updateUser(testRequest);
        // to test its content, need to first call its body, since it return user entity, we need to create user entity


       //update function will return "response entity",we mock it as saveUserResponse before
        assertEquals(HttpStatus.OK, saveUserResponse.getStatusCode());// only need to test it update function work
        // since cant test save function, it is a build in function, we could only test function created by us
    }

    @Test// the below function is for testing if user get updated and use not found
    public void shouldReturn404WhenUpdateUsernameIsNull (){
        // we need a registration request(create a constant)
        // Create a sample request, <username, password,email,bio>
        RegistrationRequest testRequest = new RegistrationRequest("Mom321@//","123abc@","hello$@gmail.com","hello, Mom!");

        // start with repository, since we are testing update function, so assuming function in service work well(use directly)
        // mock its return value: return an Entity
        Mockito.when(userRepository.findByUsername(testRequest.getUsername())).thenReturn(Optional.empty());

        // create an object that use the update user function and test its content(call the update function in its class)
        ResponseEntity<String> saveUserResponse = registrationService.updateUser(testRequest);
        // to test its content, need to first call its body, since it return user entity, we need to create user entity

        //update function will return "response entity",we mock it as saveUserResponse before
        assertEquals(HttpStatus.NOT_FOUND, saveUserResponse.getStatusCode());// only need to test it update function work
        // since cant test save function, it is a build in function, we could only test function created by us
    }


    @Test// test delete if user exist
    public void shouldUserEntityDeleted(){
        // create an entity and call it by username
        Mockito.when(userRepository.findByUsername(any())).thenReturn(Optional.of(createBasicEntity()));
        ResponseEntity<String> deleteResponseEntity = registrationService.deleteUserByUsername("username");
        String userEntity = deleteResponseEntity.getBody();
        assertEquals(HttpStatus.OK, deleteResponseEntity.getStatusCode());// only need to test it update function work
        assertEquals("Your Account Has Been Successfully Deleted", userEntity);// only need to test it update function work
    }
    @Test//test if user entity get deleted if user not exist
    public void shouldReturn404WhenDeleteUsernameIsNull(){
        // create an entity and call it by username
        Mockito.when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
        ResponseEntity<String> deleteResponseEntity = registrationService.deleteUserByUsername("username");
        String userEntity = deleteResponseEntity.getBody();
        assertEquals(HttpStatus.NOT_FOUND, deleteResponseEntity.getStatusCode());// only need to test it update function work

    }
}
