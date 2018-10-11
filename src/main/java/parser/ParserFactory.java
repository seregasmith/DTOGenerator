package parser;

import java.util.EnumMap;
import java.util.Map;

public class ParserFactory {
    private static final Map<ParserType, Parser> parsers = new EnumMap<>(ParserType.class);

    {
        parsers.put(ParserType.XLS, new XlsParser());
    }

    public Parser createParser() {
        return parsers.get(ParserType.XLS);
    }
}
