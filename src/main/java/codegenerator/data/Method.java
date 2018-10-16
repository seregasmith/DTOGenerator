package codegenerator.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Method {
    private AccessModificator accessModificator;
    protected String name;

    public Method(AccessModificator accessModificator) {
        this.accessModificator = accessModificator;
    }

    public Method(AccessModificator accessModificator, String name) {
        this.accessModificator = accessModificator;
        this.name = name;
    }
}
