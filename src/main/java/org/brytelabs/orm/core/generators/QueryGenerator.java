package org.brytelabs.orm.core.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.brytelabs.orm.api.Field;
import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.Table;
import org.brytelabs.orm.core.builders.JoinBuilderImpl;
import org.brytelabs.orm.core.builders.SelectBuilderImpl;
import org.brytelabs.orm.exceptions.SqlQueryException;
import org.brytelabs.orm.utils.ExceptionUtils;
import org.brytelabs.orm.utils.SqlUtils;

public class QueryGenerator implements Generator {
  private final Query query;

  public QueryGenerator(Query query) {
    this.query = query;
  }

  @Override
  public void validate() throws SqlQueryException {
    ExceptionUtils.passOrThrowIfNull(query.selectBuilder(), () -> "Query is empty");
    var selectBuilder = (SelectBuilderImpl) query.selectBuilder();
    var joinBuilder = (JoinBuilderImpl) query.joinBuilder();

    List<Field> fields =
        selectBuilder.fields().stream()
            .filter(f -> SqlUtils.isAliased(f.name()))
            .filter(
                f ->
                    !f.name().startsWith(selectBuilder.table().alias())
                        && (joinBuilder != null
                            && !f.name().startsWith(joinBuilder.joinedTable().alias())))
            .toList();

    if (!fields.isEmpty()) {
      String incorrectFields =
          fields.stream().map(Field::name).collect(Collectors.joining(", "));
      throw new SqlQueryException(
          "Fields "
              + incorrectFields
              + " are incorrectly aliased. Cannot be found on any of the tables");
    }
  }

  @Override
  public String generate() {
    Table table = ((SelectBuilderImpl) query.selectBuilder()).table();
    List<Generator> generators = new ArrayList<>();
    generators.add(new SelectGenerator(query.selectBuilder()));

    if (query.joinBuilder() != null) {
      generators.add(new JoinGenerator(query.joinBuilder(), query.onBuilder(), table));
    }

    if (query.whereBuilder() != null) {
      generators.add(new WhereGenerator(query.whereBuilder(), table));
    }

    if (query.groupByBuilder() != null) {
      generators.add(new GroupByGenerator(query.groupByBuilder(), table));
    }

    if (query.orderByBuilder() != null) {
      generators.add(new OrderByGenerator(query.orderByBuilder(), table));
    }

    return generators.stream()
        .peek(Generator::validate)
        .map(Generator::generate)
        .collect(Collectors.joining(" "));
  }
}
