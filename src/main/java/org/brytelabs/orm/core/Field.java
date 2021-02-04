package org.brytelabs.orm.core;

import lombok.Value;

@Value(staticConstructor = "with")
public class Field {
    String name;
    String alias;

    private Field(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public static Field with(String name) {
        return new Field(name, null);
    }

    @Override
    public String toString() {
        if (alias != null) {
            return String.join(" as ", name, alias);
        }
        return name;
    }
}
