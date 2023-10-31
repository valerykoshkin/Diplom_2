package usertests;

import assertions.UserAssertions;
import clients.UserClient;
import data.userData.UserGenerator;
import helpers.BaseTest;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;

public class CreatedUserTests extends BaseTest {
    String token;

    private final UserGenerator generator = new UserGenerator();
    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();

    @Test
    @DisplayName("Успешное создание пользователя")
    public void createCourierSuccessfulTest() {
        var user = generator.random();
        token = check.createdSuccessfully(client.createUser(user));
    }

    @Test
    @DisplayName("Попытка создания существующего пользователя")
    public void createDoubleCouriersTest() {
        var user = generator.random();
        token = check.createdSuccessfully(client.createUser(user));
        check.createdUnsuccesfullyDouble(client.createUser(user));
    }

    @Test
    @DisplayName("Попытка создания пользователя без пароля")
    public void createCourierWithoutPasswordTest() {
        var user = generator.random();
        user.setPassword("");
        check.createdUnsuccesfullyPassword(client.createUser(user));
    }

    @After
    public void deleteUser() {
        if (token != null) {
            client.deleteUser(token);
        }
    }
}