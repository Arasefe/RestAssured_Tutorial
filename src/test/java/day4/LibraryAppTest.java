package day4;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class LibraryAppTest {
    private static String myToken;
    @DisplayName("Library Setup Method")
    @BeforeAll
    public static void setUp(){
        baseURI="http://www.library1.cybertekschool.com";
        basePath="/rest/v1";
    }


    @DisplayName("Library Teardown Method")
    @BeforeAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Library login with basic auth")
    @Test
    public void addSpartan(){
        myToken=
        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email","librarian69@library")
                .formParam("password","KNPXrm3S").
        when()
                .post("/login").
        then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath()
                .getString("token");
        System.out.println(myToken);
    }

    @DisplayName("Testing GET /dashboard_stats endpoint")
    @Test
    public void testDashboard_stats(){
                given()
                        .log().all()
                        .header("x-library-token",myToken).
                when()
                        .get("/dashboard_stats").
                then()
                        .log().all()
                        .statusCode(200)
                        .contentType(ContentType.JSON);
    }
}
