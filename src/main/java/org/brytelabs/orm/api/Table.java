package org.brytelabs.orm.api;

import lombok.Value;
import org.brytelabs.orm.utils.ExceptionUtils;

@Value
public class Table {
    String name;
    String alias;

    public static Table with(String name) {
        return with(name, name);
    }

    public static Table with(String name, String alias) {
        ExceptionUtils.passOrThrowIfNullOrEmpty(name, () -> "Table name should not be null or empty");
        ExceptionUtils.passOrThrowIfNullOrEmpty(alias, () -> String.format("Alias of %s table should not be null or empty", name));
        return new Table(name, alias);
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, alias);
    }
}
