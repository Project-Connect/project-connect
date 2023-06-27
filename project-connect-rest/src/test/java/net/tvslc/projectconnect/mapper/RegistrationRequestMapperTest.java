package net.tvslc.projectconnect.mapper;

import net.tvslc.projectconnect.model.RegistrationRequest;
import net.tvslc.projectconnect.model.UserEntity;
import org.junit.jupiter.api.Test;// junit: java build in testing library
import static org.junit.Assert.assertEquals;

public class RegistrationRequestMapperTest {


    @Test// the below function is for testing different function for a specific class<unit test>
    public void shouldCovertValidRequestToEntity(){// create function to test 1 activity, for automation
        RegistrationRequestMapper mapper = new RegistrationRequestMapper();// create an instance for mapper_function

        // Create a sample request, <username, password,email,bio>
        RegistrationRequest testRequest = new RegistrationRequest("Mom321@//","123abc@","hello$@gmail.com","hello, Mom!");

        // Pass sample request into mapper.convertToEntity function and store result in a variable
        UserEntity testEntity = mapper.convertToEntity(testRequest);

        // Validate Result using assert
        // first test content and type
        assertEquals(null,testEntity.getId()); // in convert function no id get insert, should be null
        assertEquals("Mom321@//",testEntity.getUsername());
        assertEquals("123abc@",testEntity.getPassword());
        assertEquals("hello$@gmail.com",testEntity.getEmail());
        assertEquals("hello, Mom!",testEntity.getBio());
    }

}
