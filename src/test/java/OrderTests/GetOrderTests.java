package OrderTests;

import Assertions.OrderAssertions;
import Assertions.UserAssertions;
import Clients.OrderClient;
import Clients.UserClient;
import Data.OrderData.HashIngredients;
import Data.OrderData.Order;
import Data.UserData.User;
import Data.UserData.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GetOrderTests {

    HashIngredients hashIngredients = new HashIngredients();
    OrderClient orderClient = new OrderClient();
    OrderAssertions orderAssertions = new OrderAssertions();
    private final UserGenerator generator = new UserGenerator();
    private final UserClient client = new UserClient();
    private final UserAssertions check = new UserAssertions();

    Order order;
    User user;
    String token;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        user = generator.random();
        token = check.createdSuccessfully(client.createUser(user));
    }

    @Test
    @DisplayName("Успешное получение заказа пользователя")
    public void getOrderWithIngredientsSuccessfullyTest() {

        List<String> ingredients = hashIngredients.fillOrder();
        order = new Order(ingredients);
        orderAssertions.createdOrderSuccessfully(orderClient.createOrderAuth(order));
        orderAssertions.getUserOrdersSuccessfully(orderClient.getUserOrdersAuth(token));
    }

    @Test
    @DisplayName("Неуспешное получение заказа пользователя")
    public void getOrderWithIngredientsUnsuccessfullyTest() {

        List<String> ingredients = hashIngredients.fillOrder();
        order = new Order(ingredients);
        orderAssertions.createdOrderSuccessfully(orderClient.createOrderAuth(order));
        orderAssertions.getUserOrdersUnSuccessfully(orderClient.getUserOrdersUnAuth());
    }

    @After
    public void deleteUser() {
        if (token != null) {
            client.deleteUser(token);
        }
    }
}