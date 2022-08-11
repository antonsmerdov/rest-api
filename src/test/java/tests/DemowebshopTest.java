package tests;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class DemowebshopTest {
    @Test
    void addToCardAsNewTest() {


        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53" +
                        "&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57" +
                        "&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("updatetopcartsectionhtml", is("(1)"))
                .body("message", is("The product has been added to your " +
                        "<a href=\"/cart\">shopping cart</a>"));


    }

    @Test
    void addToCardWitchCookedTest() {
        Integer cartSize = 0;
        ValidatableResponse response =

                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .cookie("Nop.customer=a3afa6dc-ec7f-45ba-92a2-b1657bff60a8; " +
                                "ARRAffinity=92eb765899e80d8de4d490df907547e5cb10de899e8b754a4d5fa1a7122fad69; __utmc=78382081; __utmz=78382081.1660162685.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ")
                        .body("product_attribute_72_5_18=53" +
                                "&product_attribute_72_6_19=54" +
                                "&product_attribute_72_3_20=57" +
                                "&addtocart_72.EnteredQuantity=1")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("success", is(true))

                        .body("message", is("The product has been added to your " +
                                "<a href=\"/cart\">shopping cart</a>"));

//        assertThat(response.extract().path("updatetopcartsectionhtml"))
//                .body("updatetopcartsectionhtml", is("(40)"))
    }
}
