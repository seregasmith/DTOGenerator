import codegenerator.CodeGenerationException;
import codegenerator.data.AccessModificator;
import codegenerator.data.Field;
import codegenerator.data.Getter;
import codegenerator.data.Setter;
import codegenerator.java.JavaCodeGenerator;
import com.sun.codemodel.*;
import data.Record;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaCodeGeneratorTest {

    public static final String TMP_PATH = "tmp/";

    @Test
    public void generationTest() throws JClassAlreadyExistsException, IOException {
        JCodeModel codeModel = new JCodeModel();

        JPackage jp = codeModel._package("com.sookocheff.example");
        JDefinedClass jc = jp._class("Generated");

        jc.javadoc().add("Generated class.");

        JFieldVar constantField = jc.field(JMod.PUBLIC | JMod.FINAL | JMod.STATIC, String.class, "CONSTANT", JExpr.lit("VALUE"));

        JFieldVar varField = jc.field(JMod.PRIVATE, Integer.class, "var");

        JMethod getVar = jc.method(JMod.PUBLIC, varField.type(), "getVar");
        getVar.body()._return(varField);

        codeModel.build(new File(TMP_PATH));
    }

    @Test
    public void generation1Test() throws JClassAlreadyExistsException, IOException, CodeGenerationException {
        JavaCodeGenerator test = new JavaCodeGenerator()
                .buildWithClassName("Test")
                .buildWithPackage("ru.smith");
        List<Record> records = new ArrayList<>();
        records.add(new Record("name", "String", ""));
        records.add(new Record("surname", "String", ""));
        records.add(new Record("age", "Integer", ""));

        for (Record record : records) {
            test = withRecord(test, record);
        }
        test.buildWithOutPath(TMP_PATH);
        test.build();

    }

    private JavaCodeGenerator withRecord(JavaCodeGenerator codeGenerator, Record record) {
        Field field = new Field<>(AccessModificator.PRIVATE,
                record.getField(),
                getClass(record.getType()),
                false,
                false);
        return codeGenerator.buildWith(new Getter(field)).buildWith(new Setter(field));
    }

    private Class getClass(String clazzStr) {
        if (clazzStr.toLowerCase().equals(String.class.getSimpleName().toLowerCase())) {
            return String.class;
        }
        if (clazzStr.toLowerCase().equals(Integer.class.getSimpleName().toLowerCase())) {
            return Integer.class;
        }
        throw new RuntimeException("Not Parsed: " + clazzStr);
    }
}
