package com.shamildev.retro.data;

import com.shamildev.retro.data.entity.Contributor;
import com.shamildev.retro.data.utils.JsonFileResource;
import com.shamildev.retro.data.utils.JsonParsingRule;
import com.shamildev.retro.domain.util.Constants;

import org.junit.Rule;
import org.junit.Test;




/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Rule
    public JsonParsingRule jsonParsingRule = new JsonParsingRule(Constants.GSON);

    @Test
    @JsonFileResource(fileName = "ConfigurationResponse.json", clazz = Contributor[].class)
    public void testGetContributors() throws Exception {


        Contributor[] contributors = jsonParsingRule.getValue();


        System.out.println(">>>>>Received: " +contributors);

       // assertThat(contributors).hasSize(2);
       // assertThat(contributors[0].stillSizes().isEqualTo("koral--");
    }
    @Test
    public void testSomethingElse() {


    }

}