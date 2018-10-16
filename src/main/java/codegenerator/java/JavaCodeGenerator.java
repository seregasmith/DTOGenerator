package codegenerator.java;

import codegenerator.CodeGenerationException;
import codegenerator.CodeGenerator;
import codegenerator.data.*;
import com.sun.codemodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaCodeGenerator implements CodeGenerator {
    private List<Field> fields = new ArrayList<>();
    private List<Getter> getters = new ArrayList<>();
    private List<Setter> setters = new ArrayList<>();
    private String className;
    private String packageName;
    private String outPath;

    @Override
    public JavaCodeGenerator buildWith(Field field) {
        fields.add(field);
        return this;
    }

    @Override
    public JavaCodeGenerator buildWith(Getter getter) {
        getters.add(getter);
        return this;
    }

    @Override
    public JavaCodeGenerator buildWith(Setter setter) {
        setters.add(setter);
        return this;
    }

    @Override
    public JavaCodeGenerator buildWithClassName(String className) {
        this.className = className;
        return this;
    }

    @Override
    public CodeGenerator buildWithOutPath(String outPath) {
        this.outPath = outPath;
        return this;
    }

    public JavaCodeGenerator buildWithPackage(String packageName) {
        this.packageName = packageName;
        return this;
    }


    @Override
    public void build() throws CodeGenerationException {
        try {
            JCodeModel codeModel = new JCodeModel();

            JPackage jp = codeModel._package(packageName);

            JDefinedClass jc = jp._class(className);

            for (Getter getter : this.getters) {
                addGetter(jc, getter);
            }

            for (Setter setter : this.setters) {
                addSetter(codeModel, jc, setter);
            }
            codeModel.build(new File(outPath));
        } catch (JClassAlreadyExistsException | IOException e) {
            throw new CodeGenerationException(e);
        }
    }

    private void addSetter(JCodeModel codeModel, JDefinedClass jc, Setter setter) {
        JFieldVar jFieldVar = jc.fields().get(setter.getField().getName());
        jFieldVar = jFieldVar != null ? jFieldVar : fieldToJField(jc, setter.getField());
        JMethod setterM = jc.method(toJMod(setter.getAccessModificator()), codeModel.VOID, setter.getName());
        setterM.param(jFieldVar.type(), jFieldVar.name());
        setterM.body().assign(JExpr._this().ref(jFieldVar.name()), JExpr.ref(jFieldVar.name()));
    }

    private void addGetter(JDefinedClass jc, Getter getter) {
        JFieldVar jFieldVar = jc.fields().get(getter.getField().getName());
        jFieldVar = jFieldVar != null ? jFieldVar : fieldToJField(jc, getter.getField());
        JMethod method = getGetterSetterJMethod(jc, getter, jFieldVar);
        method.body()._return(jFieldVar);
    }

    private JMethod getGetterSetterJMethod(JDefinedClass jc, GetterSetterMethod getter, JFieldVar jFieldVar) {
        return jc.method(toJMod(getter.getAccessModificator()),
                jFieldVar.type(),
                getter.getName());
    }

    private int toJMod(AccessModificator accessModificator) {
        if (accessModificator == AccessModificator.PUBLIC)
            return JMod.PUBLIC;
        if (accessModificator == AccessModificator.PRIVATE)
            return JMod.PRIVATE;
        if (accessModificator == AccessModificator.PROTECTED)
            return JMod.PROTECTED;
        if (accessModificator == AccessModificator.DEFAULT)
            return JMod.NONE;
        return -1;
    }

    private JFieldVar fieldToJField(JDefinedClass jc, Field field) {
        int mods = toJMod(field.getAccessModificator());
        mods = field.getIsStatic() ? mods | JMod.STATIC : mods;
        mods = field.getIsFinal() ? mods | JMod.FINAL : mods;
        return jc.field(mods, field.getVClass(), field.getName());
    }
}
