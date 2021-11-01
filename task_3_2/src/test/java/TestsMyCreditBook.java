import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestsMyCreditBook {
    @Test
    public void simpleTest() throws Exception {
        MyCreditBook creditBook = new MyCreditBook();
        creditBook.addMark(1, "Math", 5, false);
        creditBook.addMark(1, "History", 5, true);
        creditBook.addMark(1, "Discrete math", 5, false);
        creditBook.addMark(1, "Declarative programming", 5, false);
        creditBook.addMark(1, "OKR", 5, true);
        creditBook.addMark(1, "Discrete math", 5, false);
        creditBook.addMark(1, "Declarative programming", 5, false);
        creditBook.addMark(1, "Imperative programming", 5, false);
        creditBook.addMark(2, "Math", 5, true);
        creditBook.addMark(2, "Discrete Math", 5, true);
        creditBook.addMark(2, "Declarative programming", 5, true);
        creditBook.addMark(2, "Imperative programming", 5, true);
        creditBook.addMark(2, "Computing platforms", 5, true);
        Assertions.assertTrue(creditBook.checkIncreasedScholarship());
        Assertions.assertEquals(creditBook.averageMark(), 5.0);
        Assertions.assertTrue(creditBook.checkRedDiploma());
    }

    @Test
    public void testWithAverageMark() throws Exception {
        MyCreditBook creditBook = new MyCreditBook();
        creditBook.addMark(1, "Math", 4, false);
        creditBook.addMark(1, "History", 4, true);
        creditBook.addMark(1, "OKR", 4, true);
        creditBook.addMark(1, "Discrete math", 4, false);
        creditBook.addMark(1, "Declarative programming", 4, false);
        creditBook.addMark(1, "Imperative programming", 4, false);
        creditBook.addMark(2, "Math", 5, true);
        creditBook.addMark(2, "Discrete Math", 5, true);
        creditBook.addMark(2, "Declarative programming", 5, true);
        creditBook.addMark(2, "Imperative programming", 5, true);
        creditBook.changeMark(1, "Math", 5);
        creditBook.changeMark(1, "OKR", 5);
        Assertions.assertTrue(creditBook.checkIncreasedScholarship());
        Assertions.assertEquals(creditBook.averageMark(), 4.6 , 0.001);
        Assertions.assertTrue(creditBook.checkRedDiploma());
    }

    @Test
    public void testOnChangeMark() throws Exception {
        MyCreditBook creditBook = new MyCreditBook();
        creditBook.addMark(1,"Math", 5, false);
        creditBook.addMark(2, "Math", 5, true);
        creditBook.addMark(2, "Discrete Math", 5, true);
        creditBook.addMark(2, "Imperative programming", 4, true);
        creditBook.addMark(2, "Computing platforms", 4, true);
        Assertions.assertFalse(creditBook.checkIncreasedScholarship());
        Assertions.assertEquals(4.6, creditBook.averageMark(), 0.001);
        creditBook.changeMark(2, "Imperative programming", 5);
        Assertions.assertEquals(4.8, creditBook.averageMark(), 0.001);
    }

    @Test
    public void testOnCheckRedDiploma() throws Exception {
        MyCreditBook creditBook = new MyCreditBook();
        creditBook.addMark(1,"Math", 5, false);
        creditBook.addMark(2, "Math", 5, true);
        creditBook.addMark(3, "Discrete Math", 5, true);
        creditBook.addMark(4, "Declarative programming", 5, true);
        creditBook.addMark(5, "Imperative programming", 5, true);
        creditBook.addMark(6, "Computing platforms", 5, false);
        creditBook.addMark(7, "Computing platforms", 5, true);
        creditBook.addMark(8, "Diploma", 5, true);
        Assertions.assertTrue(creditBook.checkRedDiploma());
    }

    @Test
    public void testOnCheckRedDiploma2() throws Exception {
        MyCreditBook creditBook = new MyCreditBook();
        creditBook.addMark(1,"Math", 5, false);
        creditBook.addMark(2, "Math", 5, true);
        creditBook.addMark(3, "Discrete Math", 5, true);
        creditBook.addMark(4, "Declarative programming", 5, true);
        creditBook.addMark(5, "Imperative programming", 5, true);
        creditBook.addMark(6, "Computing platforms", 5, false);
        creditBook.addMark(7, "Computing platforms", 5, true);
        creditBook.addMark(8, "Diploma", 4, true);
        Assertions.assertFalse(creditBook.checkRedDiploma());
    }

    @Test
    public void testOnExceptionChangeMark() throws Exception{
        MyCreditBook creditBook = new MyCreditBook();
        creditBook.addMark(1, "Declarative programming", 4, false);
        creditBook.addMark(1, "Imperative programming", 4, false);
        creditBook.addMark(2, "Imperative programming", 5, true);
        Throwable thrown = assertThrows(Exception.class, () -> {
            creditBook.changeMark(1, "OKR", 5);;
        });
        String exc = "Not Exist such subject in credit book";
        assertEquals( exc, thrown.getMessage());
    }

    @Test
    public void testOnExceptionChangeMark2() throws Exception{
        MyCreditBook creditBook = new MyCreditBook();
        creditBook.addMark(1, "Declarative programming", 4, false);
        creditBook.addMark(1, "Imperative programming", 4, false);
        creditBook.addMark(2, "Imperative programming", 5, true);
        Throwable thrown = assertThrows(Exception.class, () -> {
            creditBook.changeMark(6, "OKR", 5);;
        });
        String exc = "Not Exist such semester in credit book";
        assertEquals( exc, thrown.getMessage());
    }

    @Test
    public void testOnExceptionAddMark() throws Exception{
        MyCreditBook creditBook = new MyCreditBook();
        creditBook.addMark(1, "Declarative programming", 4, false);
        creditBook.addMark(1, "Imperative programming", 4, false);
        creditBook.addMark(2, "Imperative programming", 5, true);
        Throwable thrown = assertThrows(Exception.class, () -> {
            creditBook.addMark(2, "OKR", 2, true);
        });
        String exc = "Incorrect mark";
        assertEquals( exc, thrown.getMessage());
    }

}
