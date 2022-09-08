import com.wisebits.testapi.enums.ResponseMessages;
import com.wisebits.testapi.models.BadRequestDto;
import com.wisebits.testapi.models.CreateUserDto;
import com.wisebits.testapi.models.UserCreatedDto;
import com.wisebits.testapi.models.UserDetailsDto;
import com.wisebits.testapi.util.TestDataProvider;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.List;

import static org.testng.Assert.*;
import static com.wisebits.testapi.util.TestDataFactory.*;
import static com.wisebits.testapi.enums.ResponseMessages.*;
import static com.wisebits.testapi.requests.Requests.*;

public class UserTests extends AbstractIntegrationTest{
    private CreateUserDto userWithUniqueData;

    @BeforeClass
    public void prepareTestDate() {
        userWithUniqueData = getUserWithUniqueData();
    }

    @Test
    public void shouldGetAllUsers() {
        Response response = getAllUsersRequest();
        List<UserDetailsDto> users = response.jsonPath().getList("$",UserDetailsDto.class);
        assertEquals(response.getStatusCode(), 200);
        assertNotNull(users);
    }

    @Test
    public void shouldCreateNewUser() {
        Response response = createUserRequest(userWithUniqueData);

        assertEquals(response.getStatusCode(), 200);
        UserCreatedDto createdUser = response.jsonPath().getObject("$", UserCreatedDto.class);

        assertTrue(createdUser.success());
        assertEquals(createdUser.userDetails().username(),
                userWithUniqueData.username());
        assertEquals(createdUser.userDetails().email(),
                userWithUniqueData.email());
        assertEquals(createdUser.message(), SUCCESSFULLY_CREATED.getMessageText());
    }

    @Test (dependsOnMethods = { "shouldCreateNewUser" })
    public void createdUserShouldExistInTheDatabase() {
        Response response = getAllUsersRequest();
        assertEquals(response.statusCode(), 200);
        List<UserDetailsDto> users = response.jsonPath().getList("$",UserDetailsDto.class);
        boolean userExistsInTheList = users.stream().anyMatch(
                user -> user.username().equals(userWithUniqueData.username()) && user.email().equals(userWithUniqueData.email()));
        assertTrue(userExistsInTheList);
    }


    @Test (dependsOnMethods = { "shouldCreateNewUser" })
    public void userShouldNotBeCreatedWithTakenUsername() {
        Response response = createUserRequest(getUserWithTakenUsername(userWithUniqueData.username()));
        assertEquals(response.statusCode(), 400);

        BadRequestDto responseBody = response.jsonPath().getObject("$", BadRequestDto.class);
        assertFalse(responseBody.success());
        assertTrue(responseBody.message().contains(USERNAME_TAKEN.getMessageText()));
    }

    @Test (dependsOnMethods = { "shouldCreateNewUser" })
    public void userShouldNotBeCreatedWithTakenEmail() {
        Response response = createUserRequest(getUserWithTakenEmail(userWithUniqueData.email()));
        assertEquals(response.statusCode(), 400);

        BadRequestDto responseBody = response.jsonPath().getObject("$", BadRequestDto.class);
        assertFalse(responseBody.success());
        assertTrue(responseBody.message().contains(EMAIL_ALREADY_EXISTS.getMessageText()));
    }

    @Test(dataProvider = "emptyFieldsData", dataProviderClass = TestDataProvider.class)
    public void userWithEmptyFieldShouldNotBeCreated(CreateUserDto user, ResponseMessages message) {
        Response response = createUserRequest(user);
        assertEquals(response.statusCode(), 400);

        BadRequestDto responseBody = response.jsonPath().getObject("$", BadRequestDto.class);
        assertFalse(responseBody.success());
        assertTrue(responseBody.message().contains(message.getMessageText()));
    }
}
