import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.lang.*;

class SearchSubstringTest {
    @Test
    void classicTest() throws Exception {
        String substr = "melon";
        String name_file = "input.txt";
        String path = SearchSubstring.class.getResource(name_file).getPath();
        SearchSubstring p = new SearchSubstring();
        int[] ans = p.findIndexes(path, substr);
        int[] compare = {14, 29};
        assertArrayEquals(compare, ans);
    }

    @Test
    void emptyFile() throws Exception {
        String substr = "melon";
        String name_file = "empty_file.txt";
        String path = SearchSubstring.class.getResource(name_file).getPath();
        SearchSubstring p = new SearchSubstring();
        int[] ans = (p.findIndexes(path, substr));
        int[] compare = {};
        assertArrayEquals(compare, ans);
    }

    @Test
    void difficultText() throws Exception {
        String substr = "aabaab";
        String name_file = "input_2.txt";
        String path = SearchSubstring.class.getResource(name_file).getPath();
        SearchSubstring p = new SearchSubstring();
        int[] ans = (p.findIndexes(path, substr));
        int[] compare = {0, 10, 13, 16};
        assertArrayEquals(compare, ans);
    }

    @Test
    void notExistFile() {
        String substr = "aabaab";
        String path = "/Users/igorfesenko/Desktop/OOP/substring_search/src/test/resources/input_123.txt";
        SearchSubstring p = new SearchSubstring();
        Exception e = Assertions.assertThrows(FileNotFoundException.class, ()-> p.findIndexes(path, substr));
        assertEquals(e.getMessage(),
                "/Users/igorfesenko/Desktop/OOP/substring_search/src/test/resources/input_123.txt (No such file or directory)");
    }

    @Test
    void emptySubstring(){
        String substr = "";
        String path = "/Users/igorfesenko/Desktop/OOP/substring_search/src/test/resources/lorem.txt";
        SearchSubstring p = new SearchSubstring();
        Assertions.assertThrows(FileNotFoundException.class, ()-> p.findIndexes(path, substr));
        Exception e = Assertions.assertThrows(FileNotFoundException.class, ()-> p.findIndexes(path, substr));
        assertEquals(e.getMessage(), "empty substring");
    }

}