package net.tvslc.projectconnect.service;

import net.tvslc.projectconnect.model.GetUserResponse;
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


    @Test// the below function is for testing different function for a specific class<unit test>
    public void shouldCovertValidRequestToEntity(){// create function to test 1 activity, for automation
        UserEntity entity = new UserEntity();
        entity.setBio("This is a Bio");
        entity.setPassword("password");
        entity.setUsername("username");
        entity.setEmail("email");

        Mockito.when(userRepository.findByUsername(any())).thenReturn(Optional.of(entity));

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

}
