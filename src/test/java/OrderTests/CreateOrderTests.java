package OrderTests;

import Assertions.OrderAssertions;
import Clients.OrderClient;
import Data.OrderData.HashIngredients;
import Data.OrderData.Order;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderTests {

    HashIngredients hashIngredients = new HashIngredients();
    OrderClient orderClient = new OrderClient();
    OrderAssertions orderAssertions = new OrderAssertions();

    Order order;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    @DisplayName("Успешное создание заказа с ингредиентами")
    public void createOrderWithIngredientsSuccessfullyTest() {

        List<String> ingredients = hashIngredients.fillOrder();
        order = new Order(ingredients);
        orderAssertions.createdOrderSuccessfully(orderClient.createOrderAuth(order));
    }

    @Test
    @DisplayName("Успешное создание заказа без ингредиентов")
    public void createOrderWithoutIngredientsUnsccessfullyTest() {

        List<String> ingredients = new ArrayList<>();
        order = new Order(ingredients);
        orderAssertions.createdOrderUnuccessfully(orderClient.createOrderAuth(order));
    }

    @Test
    @DisplayName("Успешное создание заказа с неверным хешем")
    public void createOrderWithIncorrectHashTest() {
        List<String> ingredients = hashIngredients.fillOrderWrong();
        order = new Order(ingredients);
        orderAssertions.createdIncorrectHash(orderClient.createOrderAuth(order));
    }
}