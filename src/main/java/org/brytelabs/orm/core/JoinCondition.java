package org.brytelabs.orm.core;

import lombok.Value;

@Value(staticConstructor = "equal")
public class JoinCondition {
    String referenceField;
    String referencedField;
}
