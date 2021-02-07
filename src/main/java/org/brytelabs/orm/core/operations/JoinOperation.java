package org.brytelabs.orm.core.operations;

import lombok.Getter;

public enum JoinOperation {
    LEFT("left join"),
    RIGHT("right join"),
    INNER("inner join"),
    OUTER("outer join");

    @Getter
    private final String value;

    JoinOperation(String value) {
        this.value = value;
    }
}
