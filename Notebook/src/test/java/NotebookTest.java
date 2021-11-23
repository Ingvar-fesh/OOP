import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class NotebookTest {

    @Test
    public void addRemoveTest() throws Exception {
        Notebook notes = new Notebook();
        notes.load("src/test/resources/test.json");
        notes.add(new Note("Third notes", "Text", new Date()));
        List<Note> answer = notes.showAll();
        Assertions.assertEquals(3, answer.size());

        Note compare = notes.showAll().get(answer.size() - 1);

        Assertions.assertEquals("Third notes", compare.getName());
        Assertions.assertEquals("Text", compare.getText());

        notes.remove("Third notes");
        answer = notes.showAll();
        Assertions.assertEquals(2, answer.size());
    }

    @Test
    public void emptyTest() {
        Notebook notes = new Notebook();
        notes.load("src/test/resources/empty.json");
        List<Note> answer = notes.showAll();
        Assertions.assertEquals(0, answer.size());
    }
}