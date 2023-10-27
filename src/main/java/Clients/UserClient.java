package Clients;

import Data.UserData.Credentials;
import Data.UserData.User;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class UserClient {
    private final String USER_API = "api/auth";

    public ValidatableResponse createUser(User user){
        return given().log().all()
                .contentType(JSON)
                .and()
                .body(user)
                .when()
                .post(USER_API + "/register")
                .then().log().all();
    }

    public ValidatableResponse logIn(Credentials creds) {
        return given().log().all()
                .contentType(JSON)
                .body(creds)
                .when()
                .post(USER_API + "/login")
                .then().log().all();
    }

    public ValidatableResponse updateUser(Credentials creds, String token){
        return given().log().all()
                .contentType(JSON)
                .auth().oauth2(token)
                .and()
                .body(creds)
                .when()
                .patch(USER_API + "/user")
                .then().log().all();
    }

    public ValidatableResponse updateUserWithNoAuth(Credentials creds){
        return given().log().all()
                .contentType(JSON)
                .and()
                .body(creds)
                .when()
                .patch(USER_API + "/user")
                .then().log().all();
    }

    public ValidatableResponse deleteUser(String token){
        return given().log().all()
                .auth().oauth2(token)
                .when()
                .delete(USER_API + "/user")
                .then().log().all();
    }
}
