import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeTest {

    static Tree<String> tree;
    static Node<String> nodeA, nodeB;

    @BeforeEach
    void initTree() {
        tree = new Tree<>();
        tree.add("R");
        nodeA = tree.add("A"); //              R
        nodeB = tree.add("B"); //             / \
        tree.add(nodeA, "AA"); //    /   \
        tree.add(nodeA, "AB"); //   A     B
        tree.add(nodeB, "BA"); //  / \   / \
        tree.add(nodeB, "BB"); // AA AB BA BB
    }

    @Test
    void sizeTest() {
        assertEquals(7, tree.size());
    }

    @Test
    void removeTest() {
        tree.remove("BB");
        assertEquals(6, tree.size());
        tree.remove("A");
        assertEquals(3, tree.size());
    }

    @Test
    void clearTest() {
        tree.clear();
        assertEquals(0, tree.size());
    }

    @Test
    void isEmptyTest() {
        assertFalse(tree.isEmpty());
        tree.clear();
        assertTrue(tree.isEmpty());
    }

    @Test
    void containsTest() {
        assertTrue(tree.contains("R"));
        assertTrue(tree.contains("A"));
        assertTrue(tree.contains("AB"));
        assertFalse(tree.contains("AC"));
    }

    @Test
    void iteratorTest() {
        ArrayList<String> et = new ArrayList<>(List.of("R", "A", "AA", "AB", "B", "BA", "BB"));
        int i = 0;
        for (String t : tree) {
            Assertions.assertEquals(t, et.get(i));
            ++i;
        }
    }


    @Test
    void concurrentModificationExceptionTest() {
        assertThrows(ConcurrentModificationException.class, () -> {
            for (String s : tree) {
                tree.add("ABC");
            }
        });
    }

}