package com.khairul.dictionary;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class MapStringTest {

    @Test
    public void Store_Given_EmptyArray_Return_Null() {
        //Arrange
        Map[] empty = {};
        Map[] emptyNull = null;
        MapService service = new MapString();

        //Act
        String result1 = service.store(empty);
        String result2 = service.store(emptyNull);

        //Assert
        assertNull(result1);
        assertNull(result2);
    }

    @Test
    public void Store_Given_AvailableArray_Return_FormattedString() {
        //Arrange
        Map[] dictionary = dictionary();

        String expected = dictionaryInText();
        MapService service = new MapString();

        //Act
        String result = service.store(dictionary);

        //Assert
        assertNotNull(result);
        assertTrue(result.contains("="));
        assertTrue(result.contains(";"));
        assertTrue(result.contains("\n"));
        assertEquals(expected, result);
    }

    @Test
    public void Load_Given_EmptyString_Return_Null() {
        //Arrange
        String empty = "";
        String emptyNull = null;

        MapService service = new MapString();

        //Act
        Map[] result1 = service.load(empty);
        Map[] result2 = service.load(emptyNull);

        //Assert
        assertNull(result1);
        assertNull(result2);
    }

    @Test
    public void Load_Given_AvailableString_Return_ArrayOfMap() {
        //Arrange
        String text = dictionaryInText();
        Map[] expected = dictionary();
        MapService service = new MapString();

        //Act
        Map[] result = service.load(text);

        //Assert
        assertNotNull(result);
        assertArrayEquals(expected, result);
    }

    private String dictionaryInText() {
        return "key1=value1;key2=value2\nkeyA=valueA;keyB=valueB";
    }

    private Map[] dictionary() {
        Map item1 = new HashMap();
        item1.put("key1","value1");
        item1.put("key2","value2");

        Map item2 = new HashMap();
        item2.put("keyA","valueA");
        item2.put("keyB","valueB");

        Map[] dictionary = new Map[2];
        dictionary[0] = item1;
        dictionary[1] = item2;

        return dictionary;
    }
}