package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.LimitBuilder;
import org.brytelabs.orm.api.OffsetBuilder;

public class OffsetBuilderImpl implements OffsetBuilder {
    private final int offset;
    private final QueryImpl query;

    public OffsetBuilderImpl(int offset, QueryImpl query) {
        this.offset = offset;
        this.query = query;
        this.query.setOffsetBuilder(this);
    }

    @Override
    public LimitBuilder limit(int limit) {
        return new LimitBuilderImpl(limit, query);
    }

    @Override
    public QueryImpl build() {
        return query;
    }

    public int getOffset() {
        return offset;
    }
}
