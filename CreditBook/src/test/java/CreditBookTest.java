import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditBookTest {
    CreditBook book = new CreditBook();


    @Test
    public void testAddMark() throws Exception {
        Semester one = new Semester();
        one.addSubject(new Course("Введение в алгебру и анализ", 5, true));
        one.addSubject(new Course("Императивное программирование", -1, false));
        one.addSubject(new Course("Цифровые платформы", 4, false));
        book.setSem(one);

        assertEquals(4.5, book.allTimeAverageMark());

        one.addSubject(new Course("Императивное программирование", 5, false));

        assertEquals(4.666666666666667, book.allTimeAverageMark());
    }


    @Test
    public void testIncreasedScholarship() throws Exception{
        Semester one = new Semester();
        one.addSubject(new Course("Дискретная математика", 5, false));
        one.addSubject(new Course("История", 5, true));
        one.addSubject(new Course("ООП", 5, false));
        book.setSem(one);

        assertEquals(true, one.getIncreasedScholarship());
    }


    @Test
    public void testRedDiploma() throws Exception{
        Semester one = new Semester();
        one.addSubject(new Course("Введение в алгебру и анализ", 5, true));
        one.addSubject(new Course("Императивное программирование", -1, false));
        one.addSubject(new Course("Цифровые платформы", 4, false));
        book.setSem(one);

        Semester two = new Semester();
        two.addSubject(new Course("Дискретная математика", 5, false));
        two.addSubject(new Course("История", 5, true));
        two.addSubject(new Course("ООП", 5, false));
        book.setSem(two);

        Semester three = new Semester();
        three.addSubject(new Course("Дифференциальные уравнения", 5, true));
        three.addSubject(new Course("Операционные системы", 4, true));
        three.addSubject(new Course("Философия", 5, false));
        book.setSem(three);

        assertEquals(true, book.appliesForRedDiploma());

        book.setGraduationWorkMark(4);

        assertEquals(false, book.appliesForRedDiploma());

        book.setGraduationWorkMark(5);

        assertEquals(true, book.appliesForRedDiploma());
    }


    @Test
    public void exeptionMark() {
        Semester one = new Semester();
        Exception e = assertThrows(Exception.class, () ->
                one.addSubject(new Course("Введение в алгебру и анализ", 9, true)));
        assertEquals("Write wrong mark", e.getMessage());
    }
}