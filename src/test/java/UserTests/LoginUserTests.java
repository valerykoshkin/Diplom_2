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


public class LoginUserTests {
    private final static String INCORRECT_PASSWORD = "incorrectPassword";
    private final static String INCORRECT_EMAIL = "incorrectEmail";

    String token;
    User user;
    Credentials creds;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        user = generator.random();
        token = check.createdSuccessfully(client.createUser(user));
        creds = Credentials.from(user);
    }

    private final UserGenerator generator = new UserGenerator();
    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();

    @Test
    @DisplayName("Успешная авторизация пользователя")
    public void authorizationCourierSuccessfullyTest() {
        check.loggedInSuccessfully(client.logIn(creds));
    }

    @Test
    @DisplayName("Попытка авторизации курьера с некорректным email")
    public void authorizationCourierWithIncorrectCredsTest() {
        user.setEmail(INCORRECT_EMAIL);
        Credentials creds = Credentials.from(user);
        check.loggedInUnsuccesfullyUserNotExist(client.logIn(creds));
    }

    @Test
    @DisplayName("Попытка авторизации курьера с некорректным паролем")
    public void authorizationCourieraWithIncorrectPasswordTest() {
        user.setPassword(INCORRECT_PASSWORD);
        Credentials creds = Credentials.from(user);
        check.loggedInUnsuccesfullyUserNotExist(client.logIn(creds));
    }

    @After
    public void deleteUser() {
        if (token != null) {
            client.deleteUser(token);
        }
    }
}