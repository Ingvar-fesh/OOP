import java.util.ArrayList;

public class CreditBook {
    private ArrayList<Semester> sem = new ArrayList<>();
    private int graduationWorkMark = -1;

    /**
     * Add semesters in credit book
     * @param s
     */
    public void setSem(Semester s) {
        sem.add(s);
    }

    /**
     * Add mark for final work (necessary for red diploma)
     * @param mark
     */
    public void setGraduationWorkMark(int mark) {
        this.graduationWorkMark = mark;
    }

    /**
     * Get average mark in all time education
     * @return
     */
    public double allTimeAverageMark() {
        int count = 0;
        int sum = 0;
        for (Semester s: sem) {
            sum += s.SumMarkCourse();
            count += s.CourseCount();
        }
        return (double) sum / count;
    }

    /**
     * Count percent excellent marks in all education
     * @return
     */
    private double excMarksPercent() {
        int excMarks = 0;
        int totalMarks = 0;
        for (Semester s: sem) {
            totalMarks += s.lastSubjectCount();
            excMarks += s.excLastSubjectCount();
        }
        return (double) excMarks / totalMarks;
    }

    /**
     * Find smallest, last mark in credit book (necessary for red diploma)
     * @return
     */
    public int minLastMark() {
        int min = 6;
        for (Semester s: sem) {
            for (Course c: s.getCourse()) {
                if (min > c.getMark() && c.getLast() == true)
                    min = c.getMark();
            }
        }
        if (min == 6)
            return 5;
        else return min;
    }

    /**
     * Check on red diploma
     * @return
     */
    public boolean appliesForRedDiploma() {
        return (excMarksPercent() >= 0.75) && (minLastMark() > 3) && (
                graduationWorkMark == 5 || graduationWorkMark == -1);
    }
}
