package ordertests;

import assertions.OrderAssertions;
import clients.OrderClient;
import data.orderData.HashIngredients;
import data.orderData.Order;
import helpers.BaseTest;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderTests extends BaseTest {

    HashIngredients hashIngredients = new HashIngredients();
    OrderClient orderClient = new OrderClient();
    OrderAssertions orderAssertions = new OrderAssertions();

    Order order;

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