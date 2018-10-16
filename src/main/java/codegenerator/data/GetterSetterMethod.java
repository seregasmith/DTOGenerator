package codegenerator.data;

import lombok.EqualsAndHashCode;
import lombok.Setter;

@lombok.Getter
@Setter
@EqualsAndHashCode
public abstract class GetterSetterMethod extends Method {
    protected Field field;

    public GetterSetterMethod(Field field) {
        this(AccessModificator.PUBLIC, field);
    }

    public GetterSetterMethod(AccessModificator accessModificator, Field field) {
        super(accessModificator);
        this.field = field;
        setName(getInitName());
    }

    protected abstract String getInitName();
}
