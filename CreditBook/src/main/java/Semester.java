import java.util.ArrayList;

public class Semester {
    private final ArrayList<Course> courses = new ArrayList<>();

    /**
     * Add new subject in course. If course already existed, this method can change mark
     * -1 - subject didn't mark
     * 2..5 - subject already marked
     * @param subject - name, mark and position subject in this semester
     * @throws Exception - check for mark
     */
    public void addSubject(Course subject) throws Exception{
        if (subject.getMark() != -1 && subject.getMark() != 2 && subject.getMark() != 3 &&
                subject.getMark() != 4 && subject.getMark() != 5)
            throw new Exception("Write wrong mark");
        if (!courses.stream().filter(courses -> courses.getName() == subject.getName()).findFirst().isEmpty()) {
            Course s = courses.stream().filter(courses -> courses.getName() == subject.getName()).findFirst().get();
            s.setMark(subject.getMark());
        }
        else courses.add(subject);
    }

    /**
     * Get all courses in semester
     * @return
     */
    public ArrayList<Course> getCourse() {
        return courses;
    }

    /**
     * Get number of coursers which already marked
     * @return
     */
    public int CourseCount() {
        return (int) courses.stream().filter(courses -> courses.getMark() != -1).count();
    }

    /**
     * Get sum of coursers which already marked
     * @return
     */
    public int SumMarkCourse() {
        return courses.stream().filter(courses -> courses.getMark() != -1).mapToInt(Course::getMark).sum();
    }

    /**
     * Get number of coursers which was last time in education
     * @return
     */
    public int lastSubjectCount() {
        return (int) courses.stream().filter(courses -> courses.getLast() == true).count();
    }

    /**
     * Get number of coursers which was last time in education and having mark 5
     * @return
     */
    public int excLastSubjectCount() {
        return (int) courses.stream().filter(courses -> courses.getLast() == true && courses.getMark() == 5).count();
    }

    /**
     * Check on increased scholarship
     * @return
     */
    public boolean getIncreasedScholarship() {
        return SumMarkCourse() / CourseCount() == 5;
    }
}