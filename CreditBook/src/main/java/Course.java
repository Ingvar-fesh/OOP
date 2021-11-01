public class Course {
    private String name;
    private int mark;
    private boolean last;

    public Course(String name, int mark, boolean last) {
        this.name = name;
        this.mark = mark;
        this.last = last;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public boolean getLast() {
        return last;
    }
}
