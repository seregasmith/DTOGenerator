package codegenerator.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@lombok.Setter
@EqualsAndHashCode
public class Setter extends GetterSetterMethod {
    public Setter(Field field) {
        super(field);
    }

    public Setter(AccessModificator accessModificator, Field field) {
        super(accessModificator, field);
    }

    protected String getInitName() {
        return StringUtils.toCamelCase("set", field.getName());
    }
}
