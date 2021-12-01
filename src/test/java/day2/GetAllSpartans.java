package day2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetAllSpartans {
    /**
     * BeforeAll and AfterAll methods are static
     */
    @DisplayName("Spartan Setup Method")
    @BeforeAll
    public static void setUp(){
        baseURI="http://18.212.61.8:8000";
        basePath="/api";
    }


    @DisplayName("Spartan Teardown Method")
    @BeforeAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Spartan GET api/spartans endpoint")
    @Test
    public void test_HelloSpartans(){
        /**
         * given()
         * -- Request Specification
         * -- Used to provide additional information about the request
         * -- Header, query params, path variable, authentication authorization
         * -- Logging
         * -- Cookie
         * when()
         * -- This is where you actually send the request with the HTTP method
         * -- Actually where you send the request
         * -- GET, POST, PUT, DELETE ... with the URL
         * then()
         * -- ValidatableResponse
         * -- Validate status code, header, payload, cookie
         * -- Response time, structure of payload, logging response
         */
        given()
                .header("accept","application/xml")
        .when()
                .get("/spartans")
        .then()
                .statusCode(200)
                .header("Content-Type","application/xml");
    }

    @DisplayName("Spartan GET api/spartans endpoint")
    @Test
    public void test_getAllSpartans(){
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/spartans")
        .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType("Application/JSON");
    }
}
