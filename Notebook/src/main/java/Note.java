import java.util.Date;

public class Note {
    private String name;
    private String text;
    private Date time;

    public Note(String name, String text, Date time) {
        this.name = name;
        this.text = text;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Date getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public String toString() {
        return ("Name: " + this.name + "\nText: " + this.text + "\n");
    }

}
