package day3;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class JsonPathIntro {
    @DisplayName("Spartan Setup Method")
    @BeforeAll
    public static void setUp() {
        baseURI = "http://18.212.61.8:8000";
        basePath = "/api";
    }

    @DisplayName("Spartan GET api/spartans/{id} endpoint")
    @Test
    public void getOneSpartanExtractData() {

        Response response = given()
                .pathParam("id", 3)
                .get("/spartans/{id}")
                .prettyPeek();


        JsonPath jsonPath = response.jsonPath();
        int responseId = jsonPath.getInt("id");
        String responseName = jsonPath.getString("name");
        String responseGender = jsonPath.getString("gender");
        long responsePhone = jsonPath.getLong("phone");

        System.out.println("responseId" + responseId);
        System.out.println("responseName" + responseName);
        System.out.println("responseGender" + responseGender);
        System.out.println("responsePhone" + responsePhone);

    }

    @DisplayName("Spartan GET api/spartans/{id} endpoint")
    @Test
    public void getAllSpartansExtractData() {
        // Response response =  get("/spartans");
        // JsonPath jp = response.jsonPath();
        Response response = given()
                .get("/spartans")
                .prettyPeek();

        JsonPath jp = get("spartans").jsonPath();

        // Get the first json object name field
        System.out.println("1st person name is="
                +jp.getString("name[0]"));
        System.out.println("1st person phone is"
                +jp.getLong("phone[0]"));

        // Get the 7th json object name field
        System.out.println("7th person name is"
                + jp.getString("name[6]"));
        System.out.println("7th person id is"
                + jp.getInt("id[6]"));

        // Get the last json object name field
        System.out.println("Last person name is "
                + jp.getString("name[-1]"));
        System.out.println("Last person id is "
                + jp.getInt("id[-1]"));

        // Getting all the name fields from jsonArray Response and storing as List
        List<String>names=jp.getList("name");
        for (String name : names) {
            System.out.println(name);
        }

        // Getting all the phone fields from jsonArray Response and storing as List
        List<Long>phones = jp.getList("phone");
        for (Long phone : phones) {
            System.out.println(phone);
        }

    }

    @DisplayName("Testing /spartans/search and extracting data")
    @Test
    public void testSearch() {
        JsonPath jp=given()
                .queryParam("nameContains","Ar")
                .queryParam("gender","Male")
                .when()
                .get("/spartans/search")
                .jsonPath();

        System.out.println("First guy name " + jp.getString("content[0].name"));
        System.out.println("Third guy phone number " + jp.getString("content[2].phone"));
        System.out.println("All names " + jp.getList("content.name"));

    }


}
