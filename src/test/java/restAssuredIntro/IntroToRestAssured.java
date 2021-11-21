package restAssuredIntro;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntroToRestAssured {

    @DisplayName("Spartan GET api/hello endpoint")
    @Test
    public void TestHello(){
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .when()
                .get("http://18.212.61.8:8000/api/hello");
        // assert that response is 200
        assertThat(response.statusCode(),is(200));
        // How to pretty print the json response
        // prettyPrint()--print and return the body as String
        String body = response.prettyPrint();
        assertThat(body,is("Hello from Sparta"));
        // Get the header called ContentType from response
        // There are multiple ways to get ContentType
        System.out.println(response.getHeader("Content-Type"));
        System.out.println(response.getContentType());
        System.out.println(response.contentType());
        // assert that Content-Type is text/plain;charset=UTF-8
        assertThat(response.getContentType(),is("text/plain;charset=UTF-8"));
        // Easy way to work with Content-Type is text/plain;charset=UTF-8
        assertThat(response.getContentType(),startsWith("text/plain"));
        assertThat(response.getContentType(),is(not(ContentType.JSON)));

    }


}
