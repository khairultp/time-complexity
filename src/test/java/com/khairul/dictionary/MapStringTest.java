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
        Map item1 = new HashMap();
        item1.put("key1","value1");
        item1.put("key2","value2");

        Map item2 = new HashMap();
        item2.put("keyA","valueA");
        item2.put("keyB","valueB");

        Map[] dictionary = new Map[2];
        dictionary[0] = item1;
        dictionary[1] = item2;

        String expected = "key1=value1;key2=value2\nkeyA=valueA;keyB=valueB";
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
}