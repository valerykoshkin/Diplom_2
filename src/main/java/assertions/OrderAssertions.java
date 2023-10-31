package assertions;

import io.restassured.response.ValidatableResponse;

import static java.lang.String.valueOf;
import static org.hamcrest.CoreMatchers.equalTo;

public class OrderAssertions {
    public void createdOrderSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    public void createdOrderUnuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    public void createdIncorrectHash(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(500);
    }

    public void getUserOrdersSuccessfully(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(200)
                .extract()
                .path("orders.status", valueOf(equalTo("done")));
    }

    public void getUserOrdersUnSuccessfully(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(401)
                .body("message", equalTo("You should be authorised"));
    }
}
