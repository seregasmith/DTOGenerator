import data.Record;
import org.junit.Assert;
import org.junit.Test;
import parser.ParserException;
import parser.XlsParser;

import java.util.LinkedHashSet;

import static org.junit.Assert.fail;

public class XlsParserTest {
    XlsParser parser = new XlsParser();


    @Test
    public void testOnExample() {
        String exampleFilePath = "tmp/example.xlsx";

        LinkedHashSet<Record> records = null;
        try {
            records = parser.parse(exampleFilePath);
            Assert.assertTrue(records.contains(new Record("name", "string", "Имя персонажа")));
        } catch (ParserException e) {
            fail(e.getLocalizedMessage());
        }
    }
}
