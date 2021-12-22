package day5;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AssertCollection {
    @DisplayName("Spartan Setup Method")
    @BeforeAll
    public static void setUp(){
        baseURI="http://18.212.61.8:8000";
        basePath="/api";
    }


    @DisplayName("Spartan Teardown Method")
    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Testing GET/api/spartans/search with basic Auth")
    @Test
    public void testSearchExtractData(){
        // search for nameContains : a, gender Female
        // Verify statusCode 200 and extract jsonPath object after validation
        // Check the size of result is hardcoded number
        // Verify all names contain String "a"
        // Verify that all gender is Female only
        given()
                .log().all()
                .auth().basic("admin","admin")
                .queryParam("nameContains","a")
                .queryParam("gender","Female").
        when()
                .get("/spartans/search").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(200))
                .body("totalElement",equalTo(44))
                .body("content",hasSize(44))
                .body("content.name",everyItem(containsStringIgnoringCase("a")))

        ;




    }

}
