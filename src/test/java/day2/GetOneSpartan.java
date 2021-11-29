package day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GetOneSpartan {

    /**
     * BeforeAll and AfterAll methods are static
     */
    @DisplayName("Spartan Setup Method")
    @BeforeAll
    public static void setUp(){
        baseURI="http://18.212.61.8:8000";
        basePath="/api";
    }

    @DisplayName("Spartan GET api/spartans/{id} endpoint-1")
    @Test
    public void getOneSpartan1(){

        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .pathParam("id",3)
                .get("/spartans/{id}")
                .then()
                .statusCode(200);
    }

    @DisplayName("Spartan GET api/spartans/{id} endpoint-2")
    @Test
    public void getOneSpartan2(){
        RestAssured.given()
                .accept(ContentType.JSON)
                .pathParam("id",100)
                .when()
                .get("/spartans/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType("Application/JSON");
    }

    @DisplayName("Spartan GET api/spartans/{id} endpoint-3")
    @Test
    public void test_getOneSpartan3(){
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get("/spartans/{id}",102)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType("Application/JSON");
    }

    @DisplayName("Spartan GET api/spartans/{id} endpoint-4")
    @Test
    public void test_getOneSpartan4(){
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get("/spartans/{id}",14)
                .then()
                .assertThat()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("id",is(14))
                .body("gender",is(equalTo("Male")))
                .body("name",is(equalTo("Grenville")))
                .body("phone",is(equalTo(1065669615)));
    }

    @DisplayName("Spartan Teardown Method")
    @BeforeAll
    public static void tearDown(){
        reset();
    }
}
