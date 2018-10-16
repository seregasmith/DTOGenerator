package codegenerator.data;

import lombok.EqualsAndHashCode;
import lombok.Setter;

@lombok.Getter
@Setter
@EqualsAndHashCode
public class Getter extends GetterSetterMethod {
    public Getter(Field field) {
        super(field);
    }

    public Getter(AccessModificator accessModificator, Field field) {
        super(accessModificator, field);
    }

    protected String getInitName() {
        return StringUtils.toCamelCase("get", field.getName());
    }
}
