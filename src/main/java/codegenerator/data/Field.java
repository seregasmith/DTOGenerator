package codegenerator.data;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Field<T> {
    private AccessModificator accessModificator;
    private String name;
    private Class<T> vClass;
    private Boolean isStatic;
    private Boolean isFinal;
}
