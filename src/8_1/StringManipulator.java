import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class StringManipulator {

    public String concatenate(String str1, String str2){
        return str1+str2;
    }

    public int findLength(String str){
        return str.length();
    }

    public String convertToUpperCase(String str){
        return str.toUpperCase();
    }

    public String convertToLowerCase(String str){
        return str.toLowerCase();
    }

    public boolean containsSubstring(String str, String subStr){
        return str.contains(subStr);
    }
}

class StringManipulatorTest {

    @Test
    void canConcatenate(){

        StringManipulator s = new StringManipulator();
        assertEquals("ABC",s.concatenate("A","BC"));
    }

    @Test
    void canFindLength(){

        StringManipulator s = new StringManipulator();
        assertEquals(3,s.findLength("ABC"));
    }

    @Test
    void canConvertToUpperCase(){

        StringManipulator s = new StringManipulator();
        assertEquals("ABC",s.convertToUpperCase("abc"));
    }

    @Test
    void canConvertToLowerCase(){

        StringManipulator s = new StringManipulator();
        assertEquals("abc",s.convertToLowerCase("ABC"));
    }

    @Test
    void canContainsSubstring(){

        StringManipulator s = new StringManipulator();
        assertEquals(true,s.containsSubstring("abc","b"));
    }
}