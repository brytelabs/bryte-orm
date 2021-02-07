package org.brytelabs.orm.api;

import org.brytelabs.orm.core.domain.JoinCondition;

public interface JoinBuilder {
    OnBuilder on(JoinCondition field);

    default OnBuilder on(String referenceField, String referencedField) {
        return on(JoinCondition.equal(referenceField, referencedField));
    }
}
