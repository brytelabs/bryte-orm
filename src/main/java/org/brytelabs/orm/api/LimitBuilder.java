package org.brytelabs.orm.api;

public interface LimitBuilder extends Terminable {
    OffsetBuilder offset(int offset);
}
