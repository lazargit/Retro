package com.shamildev.retro;



import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

        String str = "de_DE";
        str.replace('_','-');

        System.out.println(str);

        //Pattern sPattern = Pattern.compile("^([a-z]{2})-([A-Z]{2})");
        boolean matches = str.matches("^([a-z]{2})-([A-Z]{2})");
        System.out.println(matches);


    }
}