package parser;

import data.Record;

import java.util.LinkedHashSet;

public interface Parser {
    LinkedHashSet<Record> parse(String filepath) throws ParserException;
}
