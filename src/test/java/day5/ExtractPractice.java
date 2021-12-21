package day5;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ExtractPractice {
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
        // Use jsonPath object to get the list of all results
        // Get the numberOfElements field value and compare those 2
        JsonPath jsonPath = given()
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
                .extract()
                .jsonPath();

        // Get the list of all names in String
        List<String>names=jsonPath.getList("content.name");
        System.out.println("allNames "+names);

        int numOfElements = jsonPath.getInt("totalElement");
        System.out.println(numOfElements);

        assertThat(numOfElements,equalTo(names.size()));


    }

}
