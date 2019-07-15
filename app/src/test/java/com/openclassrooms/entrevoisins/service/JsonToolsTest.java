package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.utils.JsonTools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit test on Json Tools
 */
@RunWith(JUnit4.class)
public class JsonToolsTest {

    // METHODS -------------------------------------------------------------------------------------

    @Test
    public void JsonTools_convertObjectToJson() {
        String expectedJson = "{"                                        +
                              "\"identifier\":1,"                        +
                              "\"full_name\":\"Caroline\","              +
                              "\"avatar_url\":\"http://www.xxxxx.com\""  +
                              "}";

        Neighbour neighbour = new Neighbour(1, "Caroline", "http://www.xxxxx.com");
        String actualJson = JsonTools.convertJavaToJson(neighbour);

        assertEquals("Object to Json", expectedJson, actualJson);
    }

    @Test
    public void JsonTools_convertListToJson() {
        String expectedJson = "["                                        +
                              "{"                                        +
                              "\"identifier\":1,"                        +
                              "\"full_name\":\"Caroline\","              +
                              "\"avatar_url\":\"http://www.xxxxx.com\""  +
                              "}"                                        +
                              "]";

        Neighbour neighbour = new Neighbour(1, "Caroline", "http://www.xxxxx.com");
        List<Neighbour> list = Collections.singletonList(neighbour);
        String actualJson = JsonTools.convertJavaToJson(list);

        assertEquals("List to Json", expectedJson, actualJson);
    }

    @Test
    public void JsonTools_convertObjectToJsonToObject() {
        Neighbour expectedNeighbour = new Neighbour(1, "Caroline", "http://www.xxxxx.com");

        String json = JsonTools.convertJavaToJson(expectedNeighbour);
        Neighbour actualNeighbour = JsonTools.convertJsonToJava(json, Neighbour.class);

        assertEquals("Object to Json to Object", expectedNeighbour, actualNeighbour);
    }

    @Test
    public void JsonTools_convertListToJsonToList() {
        Neighbour neighbour = new Neighbour(1, "Caroline", "http://www.xxxxx.com");
        List<Neighbour> expectedList = Collections.singletonList(neighbour);

        String json = JsonTools.convertJavaToJson(expectedList);
        List<Neighbour> actualList = JsonTools.convertJsonToJavaList(json, Neighbour.class);

        assertEquals("List to Json to List", expectedList, actualList);
    }
}
