package day4;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanUpdate {
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

    @DisplayName("Update Spartan with put api/spartans/{id} with basic auth")
    @Test
    public void updateSpartanWithPut(){
        String updateSpartan = " {\n" +
                "        \"name\": \"Maide\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"phone\": 3584128232\n" +
                "    }";

        given()
                .log().all()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .pathParam("id",1)
                .body(updateSpartan).
        when()
                .put("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(204)
                .header("Date",is(notNullValue()))
                .body(emptyString());
    }


    @DisplayName("Update Spartan with patch api/spartans/{id} with basic auth")
    @Test
    public void updateSpartanWithPatch(){
        String patchBody = " {\"name\": \"Meade\"}";

        given()
                .log().all()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .pathParam("id",1)
                .body(patchBody).
        when()
                .patch("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(204)
                .body(emptyString());
    }


    @DisplayName("Delete Spartan with patch api/spartans/{id} with basic auth")
    @Test
    public void deleteSpartan(){

        given()
                .log().all()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .pathParam("id",122).

        when()
                .delete("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(204)
                .body(emptyString());
    }


}
