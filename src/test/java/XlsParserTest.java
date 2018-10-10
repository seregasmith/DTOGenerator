import org.junit.Test;
import parser.XlsParser;

import static org.junit.Assert.fail;

public class XlsParserTest {
    XlsParser parser = new XlsParser();


    @Test
    public void testOnExample() {
        String exampleFilePath = "/tmp/example.xls";

        try {
            parser.parse(exampleFilePath);
        } catch (RuntimeException e) {
            fail(e.getLocalizedMessage());
        }
    }
}
