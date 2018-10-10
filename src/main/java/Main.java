import parser.Parser;
import parser.ParserFactory;
import settings.Settings;

import java.util.Arrays;

public class Main {
    private static Settings settings = new Settings();

    public static void main(String[] args) {
        args = (String[]) Arrays.asList("/tmp/example.xls").toArray(); // TODO example
        setSettings(args, settings);
        // TODO get settings from args
        // TODO parse file to some table structure
        parseFile(settings);
        // TODO generify java-class file with filename and fields, like in the file content
    }

    private static void setSettings(String[] args, Settings settings) {
        // TODO verify extension
        settings.setFilename(args[0]);
    }

    private static void parseFile(Settings settings) {
        Parser parser = new ParserFactory().createParser();
        parser.parse(settings.getFilename());
    }
}
