package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;


public class ReqresInTests {

    @Test
    void successFullLogin() {


    /*
    request https://reqres.in/api/login
    data :
    {
        "email": "eve.holt@reqres.in",
        "password": "cityslicka"
    }
    response
     {
        "token": "QpwL5tke4Pnpja7X4"
    }
     */
        String authorizedData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
        given()
                .when()
                .contentType(JSON)
                .body(authorizedData)
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }

    @Test
    void negativLogin() {
    String authorizedData1 = "{\"email\": \"eve.holt@reqres.in\"}";
    given()
                .when()
                .contentType(JSON)
                .body(authorizedData1)
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));

}


}
