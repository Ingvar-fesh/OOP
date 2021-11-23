import org.apache.commons.cli.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        DefaultParser parser = new DefaultParser();
        Notebook notebook = new Notebook();

        OptionGroup group = new OptionGroup();
        group.addOption(new Option("add", false, "add a new note"));
        group.addOption(new Option("rm", false, "remove all notes with the specified name"));
        group.addOption(new Option("show", false, "print a list of notes"));

        Options options = new Options();
        options.addOptionGroup(group);

        try {
            CommandLine line = parser.parse(options, args);
            String[] arg = line.getArgs();

            switch (Arrays.stream(line.getOptions()).findFirst().get().getOpt()) {

                case "add":
                    if (arg.length != 2) {
                        throw new RuntimeException("The 'add' option must be used with 2 arguments");
                    }
                    notebook.add(new Note(arg[0], arg[1], new Date()));
                    break;


                case "rm":
                    if (arg.length == 1)
                        notebook.remove(arg[0]);
                    else throw new Exception("The 'rm' option must be used with 1 argument");
                    break;


                case "show":
                    if (arg.length == 0) {
                        for (Note note: notebook.showAll()) {
                            System.out.println(note.toString());
                        }

                    } else if (arg.length >= 3) {
                        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                        String[] keywords = Arrays.copyOfRange(arg, 2, arg.length);

                        Date start, end;
                        try {
                            start = format.parse(arg[0]);
                            end = format.parse(arg[1]);
                        }
                        catch (Exception e) {
                            throw new Exception("Problem with parse");
                        }

                        for (Note note: notebook.show(start, end, keywords))
                            System.out.println(note.toString());

                    } else {
                        throw new RuntimeException("The 'show' option must be used with 0 or 2+ arguments");
                    }
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
