import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Options options = new Options();

        Option add = new Option("a", "add", true, "Notes you want to add");
        add.setArgs(2);
        options.addOption(add);

        Option rm = new Option("r", "rm", true, "Notes you want to remove");
        rm.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(rm);

        Option show = new Option("s", "show", true, "Notes you want to get");
        show.setOptionalArg(true);
        show.setArgs(Option.UNLIMITED_VALUES);
        options.addOption(show);

        GnuParser parser = new GnuParser();
        CommandLine cl = null;

        try {
            cl = parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        Option[] opts = cl.getOptions();
        Notebook noteBook = null;
        if (new File("Notes.json").exists()) {
            List<Note> nb = loadNotebook(Path.of("Notes.json"));
            noteBook = new Notebook((ArrayList<Note>) nb);
        } else {
            noteBook = new Notebook();
        }

        for (Option opt : opts) {
            switch (opt.getLongOpt()) {
                case "add" -> {
                    if (args.length == 3) {
                        noteBook.add(args[1], args[2]);
                    }
                }
                case "rm" -> {
                    if (args.length == 2) {
                        noteBook.remove(args[1]);
                    }
                }
                case "show" -> {
                    if (args.length == 1) {
                        System.out.println(Arrays.toString(noteBook.show()));
                    } else if (args.length >= 3) {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        String[] words = Arrays.copyOfRange(args, 3, args.length);
                        LocalDateTime first = LocalDateTime.parse(args[1], dtf);
                        LocalDateTime second = LocalDateTime.parse(args[2], dtf);
                        System.out.println(Arrays.toString(noteBook.show(first, second, words)));
                    }
                }
                default -> {
                    System.err.println("incorrect expression");
                }

            }
        }

    }

    public static List<Note> loadNotebook(Path path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(Paths.get("Notes.json").toFile(), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("there is no file named 'Notes.json' ");
        }
    }

}


