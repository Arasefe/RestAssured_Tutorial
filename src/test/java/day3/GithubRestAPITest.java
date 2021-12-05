package day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GithubRestAPITest {
    
    @DisplayName("Test Github GET /users/{username}")
    @Test
    public void getUserFromGitHub(){

        given()
                .accept(ContentType.JSON)
        .when()
                .pathParam("username","Arasefe")
                .get("http://api.github.com/users/{username}")
        .then()
                .statusCode(200)
                .assertThat()
                .body("login",is("Arasefe"))
                .body("id",is(equalTo(56569651)));
    }


}
