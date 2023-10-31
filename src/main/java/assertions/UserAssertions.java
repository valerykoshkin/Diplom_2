package assertions;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class UserAssertions {

    public String createdSuccessfully(ValidatableResponse response) {
        return response
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .extract()
                .path("accessToken").toString().substring(7);
    }

    public void createdUnsuccesfullyDouble(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(403)
                .body("message", equalTo("User already exists"))
                .extract()
                .path("message");
    }

    public void createdUnsuccesfullyPassword(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(403)
                .body("message", equalTo("Email, password and name are required fields"))
                .extract()
                .path("message");
    }

    public void loggedInSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    public void loggedInUnsuccesfullyUserNotExist(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(401)
                .body("message", equalTo("email or password are incorrect"));
    }

    public void updateUserSuccessfully(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    public void updateUserUnsuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(401)
                .body("message", equalTo("You should be authorised"));
    }
}