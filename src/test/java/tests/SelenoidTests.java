package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
// RestAssured.

public class SelenoidTests {
    //make request to https://selenoid.autotests.cloud/status
    // total is 20
    @Test
    void checkTotal() {
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkTotal2() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkTotalBadPractice() {
        Integer response = get("https://selenoid.autotests.cloud/status")
                .then()
                .extract()
                .path("total");
        System.out.println("Response : " + response);

        Integer expectedResponse = 20;
        assertEquals(expectedResponse, response);

    }

    @Test
    void responseExample() {
        Response response = get("https://selenoid.autotests.cloud/status")
                .then()
                .extract().response();
        System.out.println("response .toString():" + response);
        System.out.println("response .toString():" + response.toString());
        System.out.println("response .asString():" + response.asString());
        System.out.println("response .path(\"total\"):" + response.path("total"));
        System.out.println("response .path(\"browsers.chrome\"):" + response.path("browsers.chrome"));
    }

    @Test
    void checkStatus200WithAuth() {
        given()
                .auth().basic("user1", "1234")
                .get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                .statusCode(200);
    }
}
