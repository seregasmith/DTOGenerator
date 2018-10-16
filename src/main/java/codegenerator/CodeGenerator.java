package codegenerator;

import codegenerator.data.Field;
import codegenerator.data.Getter;
import codegenerator.data.Setter;

public interface CodeGenerator {
    CodeGenerator buildWith(Field field);

    CodeGenerator buildWith(Getter getter);

    CodeGenerator buildWith(Setter setter);

    CodeGenerator buildWithClassName(String classname);

    CodeGenerator buildWithOutPath(String outPath);

    void build() throws CodeGenerationException;
}
