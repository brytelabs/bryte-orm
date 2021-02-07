package org.brytelabs.orm.api;

public interface OffsetBuilder extends Terminable {
    LimitBuilder limit(int limit);
}
