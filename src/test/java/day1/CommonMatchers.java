package day1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CommonMatchers {

    @DisplayName("Common Matchers in RestAssured")
    @Test
    public void testString(){
        String str = "Rest Assured is cool";
        assertThat(str,is("Rest Assured is cool"));
        assertThat(str,equalToIgnoringCase("Rest Assured IS cool"));
        assertThat(str,startsWith("Rest"));
        assertThat(str,endsWith("cool"));
        assertThat(str,containsString("is cool"));




    }
}
