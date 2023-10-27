package Clients;

import Data.OrderData.Order;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class OrderClient {

    static private String ORDER_API = "api/orders";

    public ValidatableResponse createOrderAuth(Order order){
        return given().log().all()
                .contentType(JSON)
                .and()
                .body(order)
                .when()
                .post(ORDER_API)
                .then().log().all();
    }

    public ValidatableResponse getUserOrdersAuth(String token){
        return given().log().all()
                .auth().oauth2(token)
                .get(ORDER_API)
                .then().log().all();
    }

    public ValidatableResponse getUserOrdersUnAuth(){
        return given().log().all()
                .get(ORDER_API)
                .then().log().all();
    }

    public ValidatableResponse createOrderUnauth(){
        return given().log().all()
                .get(ORDER_API)
                .then().log().all();
    }
}
