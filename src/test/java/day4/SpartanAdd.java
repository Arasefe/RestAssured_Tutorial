package day4;

import dataModel.Spartan;
import groovy.util.ConfigObject;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class SpartanAdd {
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

    @DisplayName("Spartan GET api/spartans with basic auth")
    @Test
    public void addSpartan(){
        given()
                .log().all()
                .auth().basic("admin","admin").
        when()
                .get("/spartans").
        then()
                .log().all()
                .statusCode(200);
    }


    @DisplayName("Spartan Add Spartan with String")
    @Test
    public void addSpartanWithString(){
        String newSpartan = "{\n" +
                "    \"name\": \"Aras\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 7735103092\n" +
                "}";

        given()
                .log().all()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(newSpartan).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success",is("A Spartan is Born!"))
                .body("data.name",is("Aras"))
                .body("data.gender",is("Male"))
                .body("data.phone",is(7735103092L));
    }

    @DisplayName("Spartan Add Spartan with Map")
    @Test
    public void addSpartanWithMap(){
        Map<String, Object> spartan = new LinkedHashMap<>();
        spartan.put("name","Tulpar");
        spartan.put("gender","Male");
        spartan.put("phone","2244151388");

        given()
                .log().all()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(spartan).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success",is("A Spartan is Born!"))
                .body("data.name",is("Tulpar"))
                .body("data.gender",is("Male"))
                .body("data.phone",is(2244151388L));
    }


    @DisplayName("Spartan Add Spartan with ExternalFile")
    @Test
    public void addSpartanWithExternalFile(){
        File externalFile = new File("singleSpartan.json");

        given()
                .log().all()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(externalFile).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success",is("A Spartan is Born!"))
                .body("data.name",is("Egem"))
                .body("data.gender",is("Male"))
                .body("data.phone",is(1234334459));
    }
}
