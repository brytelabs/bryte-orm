package org.brytelabs.orm.core.generators;

import org.brytelabs.orm.api.Field;
import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.Table;
import org.brytelabs.orm.core.builders.GroupByBuilderImpl;
import org.brytelabs.orm.core.builders.JoinBuilderImpl;
import org.brytelabs.orm.core.builders.SelectBuilderImpl;
import org.brytelabs.orm.exceptions.SqlQueryException;
import org.brytelabs.orm.utils.ExceptionUtils;
import org.brytelabs.orm.utils.SqlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QueryGenerator implements Generator {
    private final Query query;

    public QueryGenerator(Query query) {
        this.query = query;
    }

    @Override
    public void validate() throws SqlQueryException {
        ExceptionUtils.passOrThrowIfNull(query.getSelectBuilder(), () -> "Query is empty");
        SelectBuilderImpl selectBuilder = (SelectBuilderImpl) query.getSelectBuilder();
        JoinBuilderImpl joinBuilder = (JoinBuilderImpl) query.getJoinBuilder();

        List<Field> fields = selectBuilder.getFields().stream()
                .filter(f -> SqlUtils.isAliased(f.getName()))
                .filter(f -> !f.getName().startsWith(selectBuilder.getTable().getAlias()) &&
                        (joinBuilder != null && !f.getName().startsWith(joinBuilder.getJoinedTable().getAlias())))
                .collect(Collectors.toList());

        if (!fields.isEmpty()) {
            String incorrectFields = fields.stream()
                    .map(Field::getName)
                    .collect(Collectors.joining(", "));
            throw new SqlQueryException("Fields " + incorrectFields
                    + " are incorrectly aliased. Cannot be found on any of the tables");
        }
    }

    @Override
    public String generate() {
        Table table = ((SelectBuilderImpl) query.getSelectBuilder()).getTable();
        List<Generator> generators = new ArrayList<>();
        generators.add(new SelectGenerator(query.getSelectBuilder()));

        if (query.getJoinBuilder() != null) {
            generators.add(new JoinGenerator(query.getJoinBuilder(), query.getOnBuilder(), table));
        }

        if (query.getWhereBuilder() != null) {
            generators.add(new WhereGenerator(query.getWhereBuilder(), table));
        }

        if (query.getGroupByBuilder() != null) {
            generators.add(new GroupByGenerator(query.getGroupByBuilder(), table));
        }

        if (query.getOrderByBuilder() != null) {
            generators.add(new OrderByGenerator(query.getOrderByBuilder(), table));
        }

        return generators.stream()
                .peek(Generator::validate)
                .map(Generator::generate)
                .collect(Collectors.joining(" "));
    }
}
