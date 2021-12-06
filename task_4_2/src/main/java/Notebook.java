import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class Notebook {
    private List<Note> notebook;
    private final ObjectMapper mapper;
    DateTimeFormatter dtf;

    public List<Note> getNotebook() {
        return notebook;
    }


    Notebook() {
        notebook = new ArrayList<>();
        mapper = new ObjectMapper();
        dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    }

    Notebook(ArrayList<Note> nb) {
        notebook = nb;
        mapper = new ObjectMapper();
        dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    }

    public List<Note> take(){
        try (Reader reader = new FileReader("Notes.json")){
            Note[] notes = mapper.readValue(reader, Note[].class);
            return new ArrayList<>(Arrays.asList(notes));
        }
        catch (Exception e){
            return new ArrayList<>();
        }
    }

    private void save() {
        try {
            Writer writer = new FileWriter("Notes.json");
            mapper.writeValue(Paths.get("Notes.json").toFile(), notebook);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(String header, String text) {
        notebook = new ArrayList<>(take());
        LocalDateTime now = LocalDateTime.now();
        Note newNote = new Note(dtf.format(now), header, text);
        notebook.add(newNote);
        save();
    }

    public void remove(String header) {
        notebook = new ArrayList<>(take());
        notebook.removeIf(note -> note.getHeader().equals(header));
        save();
    }

    public String[] show() throws IOException {
        notebook = new ArrayList<>(take());
        String[] output = new String[notebook.size()];
        int cnt = 0;
        for (Note note : notebook) {
            output[cnt++] = "Time:" + note.getTime() + " Header:" + note.getHeader() + " Text:" + note.getText() + "\n";
        }
        return output;
    }

    public String[] show(LocalDateTime after, LocalDateTime before, String[] keyWords) throws IOException {
        notebook = new ArrayList<>(take());
        LocalDateTime date;
        List<Note> tmp;
        List<Note> searchRes = notebook;
        if (keyWords.length == 0) {
            searchRes = new ArrayList<>();
            for (Note note : notebook) {
                date = LocalDateTime.parse(note.getTime(), dtf);
                if ((date.isAfter(after)) && (date.isBefore(before))) {
                    searchRes.add(note);
                }
            }
        } else {
            for (String ptr : keyWords) {
                tmp = searchRes;
                searchRes = new ArrayList<>();
                for (Note note : tmp) {
                    date = LocalDateTime.parse(note.getTime(), dtf);
                    String head = note.getHeader();
                    if ((date.isAfter(after)) && (date.isBefore(before)) && (head.contains(ptr))) {
                        searchRes.add(note);
                    }
                }
            }
        }
        String[] output = new String[searchRes.size()];
        int cnt = 0;
        for (Note note : searchRes) {
            output[cnt++] = "Time:" + note.getTime() + " Header:" + note.getHeader() + " Text:" + note.getText() + "\n";
        }
        return output;
    }
}
