import parser.Parser;
import parser.ParserException;
import parser.ParserFactory;
import settings.Settings;

import java.io.IOException;

public class Main {
    private static Settings settings = new Settings();

    public static void main(String[] args) {
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
        try {
            parser.parse(settings.getFilename());
        } catch (ParserException e) {
            // TODO catch
        }
    }
}
