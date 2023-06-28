package net.tvslc.projectconnect.service;

import net.tvslc.projectconnect.mapper.RegistrationRequestMapper;
import net.tvslc.projectconnect.model.RegistrationRequest;
import net.tvslc.projectconnect.model.UserEntity;
import net.tvslc.projectconnect.repository.UserRepository;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mock;

import org.junit.Before;
import org.mockito.Mockito;

public class RegistrationServiceTest {

    private UserRepository userRepository;

    @Before
    public void setupMock() {
        userRepository = mock(UserRepository.class);
    }


    @Test// the below function is for testing different function for a specific class<unit test>
    public void shouldCovertValidRequestToEntity(){// create function to test 1 activity, for automation
        Mockito.when(userRepository.findByUsername(any())).thenReturn(any());
    }

}
