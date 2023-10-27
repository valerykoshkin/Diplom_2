package UserTests;

import Assertions.UserAssertions;
import Clients.UserClient;
import Data.UserData.Credentials;
import Data.UserData.User;
import Data.UserData.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class UpdateUserTests {
    User user;
    String token;
    String NEW_NAME = randomAlphabetic(10);
    String NEW_EMAIL = randomAlphabetic(10);

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        user = generator.random();
        token = check.createdSuccessfully(client.createUser(user));
    }

    private final UserGenerator generator = new UserGenerator();
    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();

    @Test
    @DisplayName("Успешное обновление почты для авторизированного пользователя")
    public void updateUserEmailSuccessfullyTest() {
        Credentials creds = Credentials.from(user);
        creds.setLogin(NEW_EMAIL);
        check.updateUserSuccessfully(client.updateUser(creds, token));
    }

    @Test
    @DisplayName("Успешное обновление имени для авторизированного пользователя")
    public void updateUserNameSuccessfullyTest() {
        Credentials creds = Credentials.from(user);
        creds.setName(NEW_NAME);
        check.updateUserSuccessfully(client.updateUser(creds, token));
    }

    @Test
    @DisplayName("Попытка обновления почты для неавторизированного пользователя")
    public void updateUserEmailUnsuccessfullyTest() {
        Credentials creds = Credentials.from(user);
        creds.setLogin(NEW_EMAIL);
        check.updateUserUnsuccessfully(client.updateUserWithNoAuth(creds));
    }

    @Test
    @DisplayName("Попытка обновления имени для неавторизированного пользователя")
    public void updateUserNameUnsuccessfullyTest() {
        Credentials creds = Credentials.from(user);
        creds.setName(NEW_NAME);
        check.updateUserUnsuccessfully(client.updateUserWithNoAuth(creds));
    }

    @After
    public void deleteUser() {
        if (token != null) {
            client.deleteUser(token);
        }
    }
}