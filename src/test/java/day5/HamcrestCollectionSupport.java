package day5;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestCollectionSupport {


    @DisplayName("Hamcrest Matchers Assertion")
    @Test
    public void testSearchExtractData(){

        List<Integer>numList = Arrays.asList(4,5,6,7,8,9,12,45);
        // Use hamcrest to test the size of this list
        assertThat(numList,hasSize(numList.size()));
        // assert that this list contains 7
        assertThat(numList,hasItem(7));
        // assert that this list contains 4, 7
        assertThat(numList,hasItems(4,7));
        // assert that this list contains 7, 9
        assertThat(numList,hasItems(7,9));
        // assert that each item in the list is greater than 3
        assertThat(numList,everyItem(greaterThan(3)));

        List<String>names=Arrays.asList("Aras","Tulpar","Kayhan","Hakan","Banu","Ismail","Sam");
        assertThat(names,hasSize(7));
        // assert that everyItem has String a
        assertThat(names,everyItem(containsString("a")));
        // assert that String matches all criteria
        assertThat("Aras Efe",allOf(startsWith("A"),containsString("e")));

        // assert that String matches any criteria
        assertThat("Tulpar Ege",anyOf(startsWith("A"),containsString("e")));

    }
}
