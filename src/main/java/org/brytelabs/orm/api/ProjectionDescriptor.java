package org.brytelabs.orm.api;

import java.util.List;

public interface ProjectionDescriptor {
  default boolean isDistinct() {
    return false;
  }

  String operation();

  List<Field> fields();
}
