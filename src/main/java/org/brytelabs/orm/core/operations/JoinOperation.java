package org.brytelabs.orm.core.operations;

public enum JoinOperation {
    LEFT("left join"),
    RIGHT("right join"),
    INNER("inner join"),
    OUTER("outer join");

    private final String value;

    JoinOperation(String value) {
        this.value = value;
    }
}
