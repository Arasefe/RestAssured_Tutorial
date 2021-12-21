package day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class OpenMovieDB {
    @DisplayName("Open Movie Setup Method")
    @BeforeAll
    public static void setUp() {
        baseURI = "http://www.omdbapi.com";

    }
    @DisplayName("Teardown Method")
    @AfterAll
    public static void teardown() {
        reset();

    }

    @DisplayName("Open Movie GET  endpoint")
    @Test
    public void getJohnWickExtractData() {

        given()
                .queryParam("t", "John Wick")
                .queryParam("apiKey","5b5d0fe8").
        when()
                .get()
                .prettyPeek().

        then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("Plot",containsString("ex-hit-man"))
                .body("Ratings[1].Source",is("Rotten Tomatoes"));

    }

    @DisplayName("Open Movie GET  endpoint")
    @Test
    public void getOrvilleExtractData() {

        given()
                .queryParam("t", "The Orville")
                .queryParam("apiKey","5b5d0fe8").
                when()
                .get()
                .prettyPeek().

                then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("Title",is("The Orville"))
                .body("Ratings[0].Source",is("Internet Movie Database"));

    }

}
