package org.brytelabs.orm.api;

import java.util.Objects;
import java.util.stream.Stream;

public interface ProjectionBuilder {
  default FromBuilder field(String field) {
    return field(Field.with(field));
  }

  FromBuilder field(Field field);

  FromBuilder fields(Field... fields);

  default FromBuilder fields(String... fields) {
    Objects.requireNonNull(fields);
    return fields.length == 1
        ? field(Field.with(fields[0]))
        : fields(Stream.of(fields).map(Field::with).toArray(Field[]::new));
  }

  FromBuilder allFields();

  default FromBuilder distinct(String field) {
    return distinct(Field.with(field));
  }

  FromBuilder distinct(Field field);

  FromBuilder count();

  default FromBuilder count(String field) {
    return count(Field.with(field));
  }

  FromBuilder count(Field field);

  default FromBuilder countDistinct(String field) {
    return countDistinct(Field.with(field));
  }

  FromBuilder countDistinct(Field field);

  FromBuilder sum(Field field);

  default FromBuilder sum(String field) {
    return sum(Field.with(field));
  }

  FromBuilder max(Field field);

  default FromBuilder max(String field) {
    return max(Field.with(field));
  }

  FromBuilder min(Field field);

  default FromBuilder min(String field) {
    return min(Field.with(field));
  }

  FromBuilder avg(Field field);

  default FromBuilder avg(String field) {
    return avg(Field.with(field));
  }
}
