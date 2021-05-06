package org.brytelabs.orm.api;

public interface FromBuilder extends Terminable {
    SelectBuilder from(Table table);

    default SelectBuilder from(String table) {
        return from(Table.with(table));
    }

    default SelectBuilder from(String table, String alias) {
        return from(Table.with(table, alias));
    }
}
