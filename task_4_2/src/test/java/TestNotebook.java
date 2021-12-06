import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class TestNotebook {

    @Test
    public void addTest() throws IOException {
        Notebook notebook = new Notebook();
        notebook.add("My first note", "f");
        notebook.add("My second note", "a");
        System.out.println(Arrays.toString(notebook.show()));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Note exp = new Note(dtf.format(now), "My first note", "f");
        Assertions.assertEquals(exp.getHeader(), notebook.getNotebook().get(0).getHeader());
        Assertions.assertEquals(exp.getText(), notebook.getNotebook().get(0).getText());
        Note exp1 = new Note(dtf.format(now), "My second note", "a");
        Assertions.assertEquals(exp1.getHeader(), notebook.getNotebook().get(1).getHeader());
        Assertions.assertEquals(exp1.getText(), notebook.getNotebook().get(1).getText());
    }

    @Test
    public void removeTest() throws IOException {
        Notebook notebook = new Notebook();
        notebook.add("My first note", "f");
        notebook.add("My second note", "a");
        notebook.remove("My first note");
        System.out.println(Arrays.toString(notebook.show()));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Note exp = new Note(dtf.format(now), "My second note", "a");
        Assertions.assertEquals(exp.getHeader(), notebook.getNotebook().get(0).getHeader());
        Assertions.assertEquals(exp.getText(), notebook.getNotebook().get(0).getText());
    }

    @Test
    public void searchTest() throws IOException {
        Notebook notebook = new Notebook();
        notebook.add("My first note", "f");
        notebook.add("My second note", "a");
        notebook.add("My third note", "q");
        notebook.add("My file format", "qq");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime first = LocalDateTime.parse("2021/10/12 00:00:00", dtf);
        LocalDateTime second = LocalDateTime.parse("2021/12/30 12:35:15", dtf);
        String[] t = new String[3];
        t[0] = "My";
        t[1] = "format";
        t[2] = "file";
        System.out.println(Arrays.toString(notebook.show(first, second, t)));
        Assertions.assertEquals(Arrays.toString(notebook.show(first,second,t)).substring(26,55), "Header:My file format Text:qq");
    }

    @Test
    public void searchTestOnEmptyWords() throws IOException {
        Notebook notebook = new Notebook();
        notebook.add("My first note", "f");
        notebook.add("My second note", "a");
        notebook.add("My third note", "q");
        notebook.add("My file format", "qq");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime first = LocalDateTime.parse("2021/10/12 00:00:00", dtf);
        LocalDateTime second = LocalDateTime.parse("2021/12/30 12:35:15", dtf);
        String[] t = new String[0];
        System.out.println(Arrays.toString(notebook.show(first, second, t)));
    }
}
