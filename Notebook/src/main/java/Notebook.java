import com.google.gson.Gson;
import java.io.*;
import java.text.ParseException;
import java.util.*;


public class Notebook {
    private String file;
    private Gson gson = new Gson();


    Notebook() {
        file = "notebook.json";
    }

    /**
     * Save all notes in json file
     *
     * @param notes
     * @throws Exception
     */
    private void save(ArrayList<Note> notes) throws Exception {
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(notes, writer);
        } catch (IOException exception) {
            throw new Exception("Don't save notes");
        }
    }

    /**
     * Get all notes from json file
     *
     * @param name - name's file
     * @return
     */
    public ArrayList<Note> load(String name) {
        if (name != null && !name.equals(""))
            file = name;
        try (Reader reader = new FileReader(file)) {
            Note[] notes = gson.fromJson(reader, Note[].class);
            return new ArrayList<>(Arrays.asList(notes));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    /**
     * Add note in json file
     *
     * @param note
     * @throws Exception
     */
    public void add(Note note) throws Exception {
        ArrayList<Note> notes = load(file);
        notes.add(note);
        save(notes);
    }


    /**
     * Remove note use name
     *
     * @param name
     * @throws Exception
     */
    public void remove(String name) throws Exception {
        ArrayList<Note> notes = load(file);
        boolean removed = notes.removeIf(note -> note.getName().equals(name));
        if (removed) {
            save(notes);
        } else {
            throw new Exception("Failed to find the note to remove");
        }
    }

    /**
     * Show all notes sorted by time
     *
     * @return
     */
    public List<Note> showAll() {
        return load(file).stream().sorted(Comparator.comparing(Note::getTime)).toList();
    }

    /**
     * Show all notes sorted by time which are between two times (start and end)
     *
     * @param start
     * @param end
     * @param keywords
     * @return
     * @throws ParseException
     */
    public List<Note> show(Date start, Date end, String[] keywords) {

        return load(file).stream().filter(note -> Arrays.stream(keywords).anyMatch(note.getName()::contains)
                && note.getTime().after(start)
                && note.getTime().before(end)).sorted(Comparator.comparing(Note::getTime)).toList();
    }

}
