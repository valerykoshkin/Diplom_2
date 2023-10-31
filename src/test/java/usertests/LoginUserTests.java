package usertests;

import assertions.UserAssertions;
import clients.UserClient;
import data.userData.Credentials;
import data.userData.User;
import data.userData.UserGenerator;
import helpers.BaseTest;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginUserTests extends BaseTest {
    private final static String INCORRECT_PASSWORD = "incorrectPassword";
    private final static String INCORRECT_EMAIL = "incorrectEmail";

    String token;
    User user;
    Credentials creds;

    @Before
    public void setUp() {
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